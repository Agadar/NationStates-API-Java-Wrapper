package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;

/**
 * A query to the NationStates API's utility resource, retrieving the version
 * number of the latest live API.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class VersionQuery extends NSQuery<VersionQuery, Integer>
{
    /** Constructor. */
    public VersionQuery()
    {
        super("version");
    }
    
    @Override
    protected String resourceString()
    {
        return "a";
    }

    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
        // Empty, as no parameters need be validated.
    }

    @Override
    protected Integer translateResponse(String response)
    {
        return Integer.valueOf(response);
    }
}
