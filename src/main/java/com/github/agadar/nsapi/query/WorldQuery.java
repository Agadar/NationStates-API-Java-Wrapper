package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.shard.WorldShard;

/**
 * A query to the NationStates API's world resource.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class WorldQuery extends CensusRankQuery<WorldQuery, World, WorldShard>
{
    /**
     * Constructor. Accepts one or more shards.
     * 
     * @param shards the shards to select
     */
    public WorldQuery(WorldShard... shards)
    {
        super("");
        this.shards = shards;
    }
    
    @Override
    protected String resourceString()
    {
        return "";
    }

    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
        if (shards == null || shards.length == 0)
        {
            throw new IllegalArgumentException("No shards selected!");
        }
    }

}
