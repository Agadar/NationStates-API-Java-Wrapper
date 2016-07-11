package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.query.blueprint.APIQuery;
import com.github.agadar.nsapi.ratelimiter.DependantRateLimiter;
import com.github.agadar.nsapi.ratelimiter.RateLimiter;
import java.io.InputStream;

/**
 * A query to the NationStates API's utility resource, sending a telegram.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class TelegramQuery extends APIQuery<TelegramQuery, Void>
{
    /**
     * The rate limiter for normal telegrams. The mandated rate limit is 1
     * telegram per 30 seconds. To make sure we're on the safe side, we reduce
     * this to 1 telegram per 35 seconds.
     */
    protected static final DependantRateLimiter TGrateLimiter = 
        new DependantRateLimiter(1, 35000, rateLimiter);
    
    /**
     * The rate limiter for recruitment telegrams. The mandated rate limit is 1
     * telegram per 180 seconds. To make sure we're on the safe side, we reduce
     * this to 1 telegram per 185 seconds.
     */
    protected static final DependantRateLimiter RecruitTGrateLimiter = 
        new DependantRateLimiter(1, 185000, TGrateLimiter);
    
    /** The client key for sending telegrams. */
    private final String clientKey;
    
    /** The telegram id. */
    private final String telegramId;
    
    /** The telegram's secret key. */
    private final String secretKey;
    
    /** The nation to send the telegram to. */
    private final String nation;
    
    /** Whether the telegram to send is a recruitment telegram. */
    private boolean isRecruitment = false;
    
    /**
     * Constructor.
     * 
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param nation the nation to send the telegram to
     */
    public TelegramQuery(String clientKey, String telegramId, String secretKey, String nation)
    {
        super("sendTG");
        this.clientKey = clientKey;
        this.telegramId = telegramId;
        this.secretKey = secretKey;
        this.nation = nation;
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
        
        if (nation == null || nation.isEmpty())
        {
            throw new NationStatesAPIException("No or empty nation name supplied!");
        }
    }
    
    @Override
    protected <T extends Void> T translateResponse(InputStream response, Class<T> type)
    {
        return null;
    }
    
    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        
        // Append telegram fields.
        url += String.format("&client=%s&tgid=%s&key=%s&to=%s", clientKey,
            telegramId, secretKey, nation);
        
        return url;
    }

    @Override
    protected RateLimiter getRateLimiter()
    {
        if (isRecruitment)
        {
            return RecruitTGrateLimiter;
        }
        
        return TGrateLimiter;
    }
}
