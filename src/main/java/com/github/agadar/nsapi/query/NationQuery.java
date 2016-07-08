package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.enums.shard.NationShard;

/**
 * A query to the NationStates API's nation resource.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class NationQuery extends CensusQuery<NationQuery, Nation, NationShard>
{
    /**
     * Constructor. Sets the name of the nation to query.
     * 
     * @param nationName name of the nation to query
     */
    public NationQuery(String nationName)
    {
        super(nationName);
    }
    
    @Override
    protected String resourceString()
    {
        return "nation";
    }

    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
        if (resourceValue == null || resourceValue.isEmpty())
        {
            throw new NationStatesAPIException("No or empty nation name supplied!");
        }
    }
}
