package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;

/**
 * A query to the NationStates API's utility resource. Used for such things as
 * version checking, sending telegrams, and verifying user accounts. Only one
 * of these actions can be done per query at a time.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class MiscQuery extends NSQuery<MiscQuery, Void>
{
    // Hardcoded strings
    private final static String telegramString = "sendTG";
    private final static String verifyNationString = "verify";
    private final static String verifyVersionString = "version";
    
    /** Constructor. */
    protected MiscQuery()
    {
        super("");
    }
    
    /** The client key for sending telegrams. */
    private String clientKey;
    
    /** The telegram id. */
    private String telegramId;
    
    /** The telegram's secret key. */
    private String secretKey;
    
    /** The nation to send the telegram to, or the nation to verify. */
    private String nationName;
    
    /** The verification checksum. */
    private String checksum;
    
    /**
     * Prepares to send a telegram to a nation.
     * 
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param receiver the nation to send the telegram to
     * @return this
     */
    public MiscQuery telegram(String clientKey, String telegramId, String secretKey, String receiver)
    {
        resourceValue = telegramString;
        this.clientKey = clientKey;
        this.telegramId = telegramId;
        this.secretKey = secretKey;
        this.nationName = receiver;
        return this;
    }
    
    public MiscQuery verifyNation(String nationName, String checksum)
    {
        resourceValue = verifyNationString;
        this.nationName = nationName;
        this.checksum = checksum;
        return this;
    }
    
    public MiscQuery verifyVersion()
    {
        resourceValue = verifyVersionString;
        return this;
    }
    
    @Override
    protected String resourceString()
    {
        return "a";
    }

    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
        if (resourceValue == null || resourceValue.isEmpty())
        {
            throw new NationStatesAPIException("No action chosen!");
        }
    }

}
