package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;

/**
 * A query to the NationStates API's utility resource, verifying a user.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class VerifyQuery extends NSQuery<VerifyQuery, Boolean>
{
    /** The nation to verify. */
    private final String nation;
    
    /** The verification checksum. */
    private final String checksum;
    
    /** 
     * Constructor.
     * 
     * @param nation the nation to verify
     * @param checksum the verification checksum
     */
    public VerifyQuery(String nation, String checksum)
    {
        super("verify");
        this.nation = nation;
        this.checksum = checksum;
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
        return super.buildURL() + String.format("&nation=%s&checksum=%s", nation, checksum);
    }
}
