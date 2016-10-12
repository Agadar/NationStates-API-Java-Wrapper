package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.event.TelegramSentEvent;
import com.github.agadar.nsapi.event.TelegramSentListener;
import com.github.agadar.nsapi.query.blueprint.APIQuery;
import com.github.agadar.nsapi.ratelimiter.DependantRateLimiter;
import com.github.agadar.nsapi.ratelimiter.RateLimiter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A query to the NationStates API's utility resource, sending (a) telegram(s).
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public final class TelegramQuery extends APIQuery<TelegramQuery, Void>
{
    /** The mandated time between normal and campaign telegrams. */
    public static final int timeBetweenTGs = 30050;
    
    /** The mandated time between recruitment telegrams. */
    public static final int timeBetweenRecruitTGs = 180050;
    
    /**
     * The rate limiter for normal telegrams. The mandated rate limit is 1
     * telegram per 30 seconds. To make sure we're on the safe side, we reduce
     * this to 1 telegram per 30.05 seconds.
     */
    private static final DependantRateLimiter TGrateLimiter = 
        new DependantRateLimiter(1, timeBetweenTGs, rateLimiter);
    
    /**
     * The rate limiter for recruitment telegrams. The mandated rate limit is 1
     * telegram per 180 seconds. To make sure we're on the safe side, we reduce
     * this to 1 telegram per 180.05 seconds.
     */
    private static final DependantRateLimiter RecruitTGrateLimiter = 
        new DependantRateLimiter(1, timeBetweenRecruitTGs, TGrateLimiter);
    
    /** List of listeners. */
    private final List<TelegramSentListener> listeners = new ArrayList<>();
    
    /** The client key for sending telegrams. */
    private final String clientKey;
    
    /** The telegram id. */
    private final String telegramId;
    
    /** The telegram's secret key. */
    private final String secretKey;
    
    /** The nations to send the telegram to. */
    private final String[] nations;
    
    /** Whether the telegram to send is a recruitment telegram. */
    private boolean isRecruitment = false;
    
    /**
     * Constructor.
     * 
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param nations the nation(s) to send the telegram to
     */
    public TelegramQuery(String clientKey, String telegramId, String secretKey, String... nations)
    {
        super("sendTG");
        this.clientKey = clientKey;
        this.telegramId = telegramId;
        this.secretKey = secretKey;
        this.nations = nations;
    }
    
    /**
     * Ensures the telegram will be send as if it is a recruitment telegram. 
     * If you're sending a recruitment telegram, then this should be set, or the 
     * mandated rate limit might be violated and your telegrams won't be send.
     * 
     * @return this
     */
    public TelegramQuery isRecruitment()
    {
        isRecruitment = true;
        return this;
    }
    
    /**
     * Registers new telegram event listeners.
     * 
     * @param newListeners the listeners to register
     * @return this
     */
    public TelegramQuery addListeners(TelegramSentListener... newListeners)
    {
        synchronized (listeners)
        {
            for (TelegramSentListener listener : newListeners)
            {
                if (!listeners.contains(listener))
                {
                    listeners.add(listener);
                }
            }
        }
        return this;
    }
    
    @Override
    protected String resourceString()
    {
        return "a";
    }

    @Override
    protected void validateQueryParameters()
    {
        super.validateQueryParameters();
        
        if (clientKey == null || clientKey.isEmpty())
        {
            throw new NationStatesAPIException("No or empty client key supplied!");
        }
        
        if (telegramId == null || telegramId.isEmpty())
        {
            throw new NationStatesAPIException("No or empty telegram id supplied!");
        }
        
        if (secretKey == null || secretKey.isEmpty())
        {
            throw new NationStatesAPIException("No or empty secret key supplied!");
        }
        
        if (nations == null || nations.length < 1)
        {
            throw new NationStatesAPIException("No addressees supplied!");
        }
    }
    
    @Override
    protected <T extends Void> T translateResponse(InputStream response, Class<T> type)
    {
        return null;
    }
    
    /**
     * The estimated time it will take to send all of this query's telegrams, in
     * milliseconds. Assumes no interference from other TelegramQueries being
     * executed simultaneously.
     * 
     * @return the estimated time in milliseconds
     */
    public long estimatedDuration()
    {
        // Null-check on nations
        if (nations == null || nations.length < 1)
        {
            return 0;
        }
        
        // Calculate and return estimated time
        int individual = isRecruitment ? timeBetweenRecruitTGs : timeBetweenTGs;
        return nations.length * individual;
    }

    @Override
    public <T extends Void> T execute(Class<T> type)
    {
        // Validate parameters and build base url.
        validateQueryParameters();
        String baseUrl = buildURL();
        
        // For each addressee, call makeRequest(...).
        for (int i = 0; i < nations.length; i++)
        {
            // Build final url and wait for the rate limiter to go.
            String nation = nations[i];
            String url = baseUrl + nation.replace(' ', '_');
            boolean queued = true;
            String message = "";
            final RateLimiter limiter = getRateLimiter();
            limiter.Lock();
            
            try
            {
                makeRequest(url, type);
            }
            catch (Exception ex)
            {
                queued = false;
                message = ex.getMessage();
            }
            finally
            {
                limiter.Unlock();
            }
            
            // Fire a new telegram sent event.
            final TelegramSentEvent event = new TelegramSentEvent(this, nation, queued, message, i);
            synchronized(listeners)
            {
                listeners.stream().forEach((tsl) ->
                {
                    tsl.handleTelegramSent(event);
                });      
            }
        }
        
        return null;
    }
    
    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        
        // Append telegram fields.
        url += String.format("&client=%s&tgid=%s&key=%s&to=", clientKey,
            telegramId, secretKey);
        
        return url;
    }

    @Override
    protected RateLimiter getRateLimiter()
    {
        if (isRecruitment)
            return RecruitTGrateLimiter;
        
        return TGrateLimiter;
    }
}
