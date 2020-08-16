package com.github.agadar.nationstates.query;

import java.util.Collection;
import java.util.HashSet;

import com.github.agadar.nationstates.event.TelegramSentEvent;
import com.github.agadar.nationstates.event.TelegramSentListener;
import com.github.agadar.nationstates.exception.NationStatesResourceNotFoundException;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * A query to the NationStates API's utility resource, sending (a) telegram(s).
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Slf4j
public class TelegramQuery extends APIQuery<TelegramQuery, Void> {

    /**
     * The rate limiter for normal telegrams.
     */
    private final RateLimiter telegramRateLimiter;

    /**
     * The rate limiter for recruitment telegrams.
     */
    private final RateLimiter recruitmentTelegramRateLimiter;

    /**
     * List of listeners.
     */
    private final Collection<TelegramSentListener> listeners = new HashSet<>();

    /**
     * The client key for sending telegrams.
     */
    private final String clientKey;

    /**
     * The telegram id.
     */
    private final String telegramId;

    /**
     * The telegram's secret key.
     */
    private final String secretKey;

    /**
     * The nations to send the telegram to.
     */
    private final String[] nations;

    /**
     * Whether the telegram to send is a recruitment telegram.
     */
    private boolean sendAsRecruitmentTelegram = false;

    public TelegramQuery(@NonNull QueryDependencies queryDependencies, @NonNull RateLimiter telegramRateLimiter,
            @NonNull RateLimiter recruitmentTelegramRateLimiter, @NonNull String clientKey, @NonNull String telegramId,
            @NonNull String secretKey, @NonNull String... nations) {
        super(queryDependencies, "sendTG");
        this.telegramRateLimiter = telegramRateLimiter;
        this.recruitmentTelegramRateLimiter = recruitmentTelegramRateLimiter;
        this.clientKey = clientKey;
        this.telegramId = telegramId;
        this.secretKey = secretKey;
        this.nations = nations;
    }

    /**
     * Ensures the telegram will be send as if it is a recruitment telegram. If
     * you're sending a recruitment telegram, then this should be set, or the
     * mandated rate limit might be violated and your telegrams won't be send.
     *
     * @return this
     */
    public TelegramQuery sendAsRecruitmentTelegram() {
        sendAsRecruitmentTelegram = true;
        return this;
    }

    /**
     * Registers new telegram event listeners.
     *
     * @param newListeners the listeners to register
     * @return this
     */
    public TelegramQuery addListeners(@NonNull TelegramSentListener... newListeners) {
        synchronized (listeners) {
            for (TelegramSentListener listener : newListeners) {
                listeners.add(listener);
            }
        }
        return this;
    }

    /**
     * The estimated time it will take to send all of this query's telegrams, in
     * milliseconds. Assumes no interference from other TelegramQueries being
     * executed simultaneously.
     *
     * @return the estimated time in milliseconds
     */
    public long estimatedDuration() {
        // Null-check on nations
        if (nations == null || nations.length < 1) {
            return 0;
        }

        // Calculate and return estimated time
        final int individual = sendAsRecruitmentTelegram
                ? this.recruitmentTelegramRateLimiter.getMillisecondsBetweenLocks()
                : this.telegramRateLimiter.getMillisecondsBetweenLocks();
        return (nations.length - 1) * individual;
    }

    @Override
    public <T> T execute(Class<T> type) {

        validateQueryParameters();
        String baseUrl = buildURL();

        for (int i = 0; i < nations.length && getRateLimiter().lock(); i++) {

            String nation = nations[i];
            String url = baseUrl + nation.replace(' ', '_');
            Exception exception = null;

            try {
                makeRequest(url, input -> null);
                log.info("Queued a telegram to nation '{}'", nation);

            } catch (NationStatesResourceNotFoundException ex) {
                log.info("Nation '{}' was not found or doesn't exist", nation);
                exception = ex;
                
            } catch (Exception ex) {
                log.error(String.format("An error occured while queueing a telegram to nation '%s'", nation), ex);
                exception = ex;

            } finally {
                getRateLimiter().unlock();
            }

            // Fire a new telegram sent event.
            var event = new TelegramSentEvent(this, nation, exception, i);
            synchronized (listeners) {
                listeners.stream().forEach((tsl) -> {
                    tsl.handleTelegramSent(event);
                });
            }
        }
        return null;
    }

    @Override
    protected String resourceString() {
        return "a";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (clientKey == null || clientKey.isEmpty()) {
            throw new IllegalArgumentException("No or empty client key supplied!");
        }

        if (telegramId == null || telegramId.isEmpty()) {
            throw new IllegalArgumentException("No or empty telegram id supplied!");
        }

        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("No or empty secret key supplied!");
        }

        if (nations == null || nations.length < 1) {
            throw new IllegalArgumentException("No addressees supplied!");
        }
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        url += String.format("&client=%s&tgid=%s&key=%s&to=", clientKey, telegramId, secretKey);
        return url;
    }

    @Override
    protected RateLimiter getRateLimiter() {
        if (sendAsRecruitmentTelegram) {
            return recruitmentTelegramRateLimiter;
        }
        return telegramRateLimiter;
    }
}
