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
    /** Nation to list dispatches of. */
    private String dispatchAuthor;
    
    /** Category to list dispatches of. */
    private String dispatchCategory;
    
    /** Subcategory to list dispatches of. */
    private String dispatchSubcategory;
    
    /** Whether to get the best dispatches (as opposed to newest). */
    private boolean dispatchGetBest = false;
    
    /** Id of the dispatch to select. */
    private int dispatchId;
    
    /** Regions WITH these tags are retrieved. */
    private String[] regionsWithTags;
    
    /** Regions WITHOUT these tags are retrieved. */
    private String[] regionsWithoutTags;
    
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
    
    /**
     * Sets the author to list dispatches of. Does nothing if Dispatches shard 
     * is not selected.
     * 
     * @param nation the author to list dispatches of
     * @return this
     */
    public WorldQuery dispatchAuthor(String nation)
    {
        this.dispatchAuthor = nation;
        return this;
    }
    
    /**
     * Sets the category to list dispatches of. Does nothing if Dispatches shard 
     * is not selected.
     * 
     * @param category the category to list dispatches of
     * @return this
     */
    public WorldQuery dispatchCategory(String category)
    {
        this.dispatchCategory = category;
        return this;
    }
    
    /**
     * Sets the subcategory to list dispatches of. Does nothing if Dispatches 
     * shard is not selected or category is not set.
     * 
     * @param subcategory the subcategory to list dispatches of
     * @return this
     */
    public WorldQuery dispatchSubcategory(String subcategory)
    {
        this.dispatchSubcategory = subcategory;
        return this;
    }
    
     /**
     * Sets the type to list dispatches of to 'best', as opposed to the default
     * 'new'. Does nothing if Dispatches shard is not selected.
     * 
     * @return this
     */
    public WorldQuery dispatchGetBest()
    {
        this.dispatchGetBest = true;
        return this;
    }
    
    /**
     * Sets the id of the dispatch to select. Does nothing if SelectedDispatch
     * shard is not selected.
     * 
     * @param dispatchId id of the dispatch to select.
     * @return this
     */
    public WorldQuery dispatchId(int dispatchId)
    {
        this.dispatchId = dispatchId;
        return this;
    }
    
    /**
     * Sets the tags, used for retrieving regions that have those tags. Does
     * nothing if the RegionsByTag shard is not selected.
     * 
     * @param tags the tags
     * @return this 
     */
    public WorldQuery regionsWithTags(String... tags)
    {
        regionsWithTags = tags;
        return this;
    }
    
    /**
     * Sets the tags, used for retrieving regions that do NOT have those tags.
     * Does nothing if the RegionsByTag shard is not selected.
     * 
     * @param tags the tags
     * @return this 
     */
    public WorldQuery regionsWithoutTags(String... tags)
    {
        regionsWithoutTags = tags;
        return this;
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

    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        
        if (dispatchAuthor != null && !dispatchAuthor.isEmpty())
        {
            url += "&dispatchauthor=" + dispatchAuthor;
        }
        
        if (dispatchCategory != null && !dispatchCategory.isEmpty())
        {
            url += "&dispatchcategory=" + dispatchCategory;
            
            if (dispatchSubcategory != null && !dispatchSubcategory.isEmpty())
            {
                url += ":" + dispatchSubcategory;
            }
        }
        
        if (dispatchGetBest)
        {
            url += "&dispatchsort=best";
        }
        
        if (dispatchId != 0)
        {
            url += "&dispatchid=" + dispatchId;
        }
        
        final boolean hasWithTags = regionsWithTags != null && regionsWithTags.length > 0; 
        
        if (hasWithTags)
        {
            url += "&tags=" + regionsWithTags[0];
            
            for (int i = 1; i < regionsWithTags.length; i++)
            {
                url += "," + regionsWithTags[i];
            }
        }
        
        if (regionsWithoutTags != null && regionsWithoutTags.length > 0)
        {
            int start = 0;
            
            if (!hasWithTags)
            {
                url += "&tags=" + regionsWithoutTags[start++];
            }
            
            for (int i = start; i < regionsWithoutTags.length; i++)
            {
                url += ",-" + regionsWithoutTags[i];
            }
        }
        
        return url;
    }
}
