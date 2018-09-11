package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.IXmlConverter;
import com.github.agadar.nationstates.event.TelegramSentEvent;
import com.github.agadar.nationstates.event.TelegramSentListener;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A query to the NationStates API's utility resource, sending (a) telegram(s).
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class TelegramQuery extends APIQuery<TelegramQuery, Void> {

    /**
     * The rate limiter for normal telegrams.
     */
    private final IRateLimiter telegramRateLimiter;

    /**
     * The rate limiter for recruitment telegrams.
     */
    private final IRateLimiter recruitmentTelegramRateLimiter;

    /**
     * List of listeners.
     */
    private final List<TelegramSentListener> listeners = new ArrayList<>();

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
    private boolean isRecruitment = false;

    /**
     * Constructor.
     *
     * @param xmlConverter
     * @param generalRateLimiter
     * @param scrapingRateLimiter
     * @param telegramRateLimiter
     * @param recruitmentTelegramRateLimiter
     * @param baseUrl
     * @param userAgent
     * @param apiVersion
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param nations the nation(s) to send the telegram to
     */
    public TelegramQuery(IXmlConverter xmlConverter, IRateLimiter generalRateLimiter, IRateLimiter scrapingRateLimiter,
            IRateLimiter telegramRateLimiter, IRateLimiter recruitmentTelegramRateLimiter,
            String baseUrl, String userAgent, int apiVersion,
            String clientKey, String telegramId, String secretKey, String... nations) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, "sendTG");
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
    public TelegramQuery isRecruitment() {
        isRecruitment = true;
        return this;
    }

    /**
     * Registers new telegram event listeners.
     *
     * @param newListeners the listeners to register
     * @return this
     */
    public TelegramQuery addListeners(TelegramSentListener... newListeners) {
        synchronized (listeners) {
            for (TelegramSentListener listener : newListeners) {
                if (!listeners.contains(listener)) {
                    listeners.add(listener);
                }
            }
        }
        return this;
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
    protected <T> T translateResponse(InputStream response, Class<T> type) {
        return null;
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
        final int individual = isRecruitment
                ? this.recruitmentTelegramRateLimiter.getMillisecondsBetweenLocks()
                : this.telegramRateLimiter.getMillisecondsBetweenLocks();
        return (nations.length - 1) * individual;
    }

    @Override
    public <T> Optional<T> execute(Class<T> type) {
        // Validate parameters and build base url.
        validateQueryParameters();
        String baseUrl = buildURL();

        // For each addressee, call makeRequest(...) if it isn't a dry run.
        for (int i = 0; i < nations.length && getRateLimiter().lock(); i++) {
            // Build final url and wait for the rate limiter to go.
            final String nation = nations[i];
            final String url = baseUrl + nation.replace(' ', '_');
            boolean queued = true;
            String message = "";

            try {
                makeRequest(url, type);
            } catch (Exception ex) {
                // If anything went wrong, make sure we log it in the event.
                queued = false;
                message = ex.getMessage();
            } finally {
                // Always unlock the rate limiter to prevent deadlock.
                getRateLimiter().unlock();
            }

            // Fire a new telegram sent event.
            final TelegramSentEvent event = new TelegramSentEvent(this, nation, queued, message, i);
            synchronized (listeners) {
                listeners.stream().forEach((tsl) -> {
                    tsl.handleTelegramSent(event);
                });
            }
        }
        return Optional.empty();
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        url += String.format("&client=%s&tgid=%s&key=%s&to=", clientKey, telegramId, secretKey); // Append telegram fields.
        return url;
    }

    @Override
    protected IRateLimiter getRateLimiter() {
        if (isRecruitment) {
            return recruitmentTelegramRateLimiter;
        }
        return telegramRateLimiter;
    }
}
