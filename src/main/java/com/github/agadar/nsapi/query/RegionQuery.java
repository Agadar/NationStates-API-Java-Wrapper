package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.query.blueprint.CensusRankQuery;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.enums.shard.RegionShard;

/**
 * A query to the NationStates API's region resource.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public final class RegionQuery extends CensusRankQuery<RegionQuery, Region, RegionShard>
{
    /** Offset for the 10 most recent regional messages. */
    private int offset;
    
    /**
     * Constructor. Sets the name of the region to query.
     * 
     * @param regionName name of the region to query
     */
    public RegionQuery(String regionName)
    {
        super(regionName);
    }
    
    /**
     * Sets the offset for the 10 most recent regional messages. Does nothing if
     * the RegionalMessages shard is not selected.
     * 
     * @param offset the offset for the 10 most recent regional messages
     * @return this
     */
    public final RegionQuery messagesOffset(int offset)
    {
        this.offset = offset;
        return this;
    }
    
    @Override
    protected String resourceString()
    {
        return "region";
    }
    
    @Override
    protected void validateQueryParameters()
    {
        super.validateQueryParameters();
        
        if (resourceValue == null || resourceValue.isEmpty())
        {
            throw new NationStatesAPIException("No or empty region name supplied!");
        }
    }

    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        return offset == 0 ? url : url + "&offset=" + offset;
    }
}
