package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.enums.shard.Shard;

/**
 * Intermediate parent class that adds support for shards.
 * 
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 * @param <S> the shard type the child class accepts
 */
public abstract class ShardQuery<Q extends ShardQuery, R, S extends Shard> extends NSQuery<Q, R>
{
    /** See shards(...). */
    protected S[] shards;
    
    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     * 
     * @param resourceValue the resource value
     */
    public ShardQuery(String resourceValue)
    {
        super(resourceValue);
    }
    
    /**
     * Sets the shards.
     * 
     * @param shards the shards to set
     * @return this
     */
    public final Q shards(S... shards)
    {        
        this.shards = shards;
        return (Q) this;
    }

    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        
        // If there are shards present, then append them to url
        if (shards != null && shards.length > 0)
        {
            url += "&q=" + shards[0].shardName();

            for (int i = 1; i < shards.length; i++)
            {
                url += "+" + shards[i].shardName();
            }
        }
        
        return url;
    }
}
