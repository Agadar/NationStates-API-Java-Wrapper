package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.enums.shard.RegionShard;

/**
 * A query to the NationStates API's region resource.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class RegionQuery extends CensusQuery<RegionQuery, RegionShard, Region>
{
    /**
     * Constructor. Sets the name of the region to query.
     * 
     * @param regionName name of the region to query
     */
    protected RegionQuery(String regionName)
    {
        super(regionName);
    }
    
    @Override
    protected String resourceString()
    {
        return "region";
    }
    
    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
        if (resourceValue == null || resourceValue.isEmpty())
        {
            throw new NationStatesAPIException("No or empty region name supplied!");
        }
    }
}
