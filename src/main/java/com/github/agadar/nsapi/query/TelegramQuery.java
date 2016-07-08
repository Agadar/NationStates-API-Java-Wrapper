package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;

/**
 * A query to the NationStates API's utility resource, sending a telegram.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class TelegramQuery extends NSQuery<TelegramQuery, Boolean>
{
    /** The client key for sending telegrams. */
    private final String clientKey;
    
    /** The telegram id. */
    private final String telegramId;
    
    /** The telegram's secret key. */
    private final String secretKey;
    
    /** The nation to send the telegram to. */
    private final String nation;
    
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
    
    @Override
    protected String resourceString()
    {
        return "a";
    }

    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
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
    protected Boolean translateResponse(String response)
    {
        return response.equals("1");
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
}
