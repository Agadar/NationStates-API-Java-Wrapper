package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.HapFilter;
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
    
    /** The nation or region to view happenings of. */
    private String happeningsView;
    
    /** The filters for happenings. */
    private HapFilter[] happeningsFilter;
    
    /** The maximum number of happenings to retrieve. */
    private int happeningsLimit;
    
    /** For restricting happenings to those with higher event id's than this. */
    private int happeningsSinceId;
    
    /** For restricting happenings to those with lower event id's than this. */
    private int happeningsBeforeId;
    
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
    public final WorldQuery dispatchAuthor(String nation)
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
    public final WorldQuery dispatchCategory(String category)
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
    public final WorldQuery dispatchSubcategory(String subcategory)
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
    public final WorldQuery dispatchGetBest()
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
    public final WorldQuery dispatchId(int dispatchId)
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
    public final WorldQuery regionsWithTags(String... tags)
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
    public final WorldQuery regionsWithoutTags(String... tags)
    {
        regionsWithoutTags = tags;
        return this;
    }
    
    /**
     * Sets the nation to view happenings of. Does nothing if the Happenings
     * shard is not selected.
     * 
     * @param nation the nation to view happenings of
     * @return this
     */
    public final WorldQuery happeningsOfNation(String nation)
    {
        this.happeningsView = "nation." + nation;
        return this;
    }
    
    /**
     * Sets the region to view happenings of. Does nothing if the Happenings
     * shard is not selected.
     * 
     * @param region the region to view happenings of
     * @return this
     */
    public final WorldQuery happeningsOfRegion(String region)
    {
        this.happeningsView = "region." + region;
        return this;
    }
    
    /**
     * Sets the filters for happenings. Does nothing if the Happenings shard
     * is not selected.
     * 
     * @param filters the filters for happenings
     * @return this
     */
    public final WorldQuery happeningsFilter(HapFilter... filters)
    {
        this.happeningsFilter = filters;
        return this;
    }
    
    /**
     * Sets the maximum number of happenings to retrieve. Does nothing if the 
     * Happenings shard is not selected.
     * 
     * @param limit the maximum number of happenings to retrieve
     * @return this
     */
    public final WorldQuery happeningsLimit(int limit)
    {
        this.happeningsLimit = limit;
        return this;
    }
    
    /**
     * If this is set, only happenings with a higher id than the one supplied
     * will be retrieved.
     * 
     * @param id the id
     * @return this
     */
    public final WorldQuery happeningsSinceId(int id)
    {
        this.happeningsSinceId = id;
        return this;
    }
    
    /**
     * If this is set, only happenings with a lower id than the one supplied
     * will be retrieved.
     * 
     * @param id the id
     * @return this
     */
    public final WorldQuery happeningsBeforeId(int id)
    {
        this.happeningsBeforeId = id;
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
        
        if (happeningsView != null && !happeningsView.isEmpty() && 
            !happeningsView.equals("nation.") && !happeningsView.equals("region."))
        {
            url += "&view=" + happeningsView;
        }
        
        if (happeningsFilter != null && happeningsFilter.length > 0)
        {
            url += "&filter=" + happeningsFilter[0].name();
            
            for (int i = 1; i < happeningsFilter.length; i++)
            {
                url += "+" + happeningsFilter[i];
            }
        }
        
        if (happeningsLimit != 0)
        {
            url += "&limit=" + happeningsLimit;
        }
        
        if (happeningsSinceId != 0)
        {
            url += "&sinceid=" + happeningsSinceId;
        }
        
        if (happeningsBeforeId != 0)
        {
            url += "&beforeid=" + happeningsBeforeId;
        }
        
        return url;
    }
}
