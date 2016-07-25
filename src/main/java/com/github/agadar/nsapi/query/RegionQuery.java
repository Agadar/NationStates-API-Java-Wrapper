package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.enums.shard.RegionShard;
import com.github.agadar.nsapi.query.blueprint.CensusRankQuery;

/**
 * A query to the NationStates API's region resource.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public final class RegionQuery extends CensusRankQuery<RegionQuery, Region, RegionShard>
{
    /** Offset for the 10 most recent regional messages. */
    private int offset;
    
    /** The id of the post to start from. */
    private long fromId;
    
    /** The maximum number of posts to retrieve. */
    private long limit;
    
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
    
    /**
     * Sets the id of the post to start from. Does nothing if the RegionalMessages
     * shard is not selected.
     * 
     * @param id the id of the post to start from
     * @return this
     */
    public final RegionQuery messagesFromId(long id)
    {
        this.fromId = id;
        return this;
    }
    
    /**
     * Sets the maximum number of posts to retrieve. Does nothing if the
     * RegionalMessages shard is not selected. Limit is bound between 0 (inclusive) 
     * and 100 (inclusive).
     * 
     * @param limit maximum number of posts to retrieve
     * @return this
     */
    public final RegionQuery messagesLimit(int limit)
    {
        this.limit = Math.min(Math.max(limit, 0), 100);
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
        url += offset == 0 ? "" : "&offset=" + offset;
        url += fromId == 0 ? "" : "&fromid=" + fromId;
        url += limit == 0 ? "" : "&limit=" + limit;
        return url;
    }
}
