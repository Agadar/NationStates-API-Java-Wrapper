package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;

/**
 * A query to the NationStates API's utility resource, verifying a user. Users
 * can retrieve their code at https://www.nationstates.net/page=verify_login.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class VerifyQuery extends NSQuery<VerifyQuery, Boolean>
{
    /** The nation to verify. */
    private final String nation;
    
    /** The verification checksum. */
    private final String checksum;
    
    /** The application-specific token. */
    private String token;
    
    /** 
     * Constructor.
     * 
     * @param nation the nation to verify
     * @param code the verification code
     */
    public VerifyQuery(String nation, String code)
    {
        super("verify");
        this.nation = nation.replace(' ', '_');
        this.checksum = code;
    }
    
    /**
     * Use an application-specific token. If a token is set, then users can
     * retrieve their code at https://www.nationstates.net/page=verify_login?token=(Your token)
     * instead.
     * 
     * @param token application-specific token
     * @return this
     */
    public final VerifyQuery token(String token)
    {
        this.token = token;
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
        if (nation == null || nation.isEmpty())
        {
            throw new NationStatesAPIException("No or empty nation name supplied!");
        }
        
        if (checksum == null || checksum.isEmpty())
        {
            throw new NationStatesAPIException("No or empty checksum supplied!");
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
        String url = super.buildURL() + String.format("&nation=%s&checksum=%s", nation, checksum);
        
        if (token != null && !token.isEmpty())
        {
            url += "&token=" + token;
        }
        
        return url;
    }
}
