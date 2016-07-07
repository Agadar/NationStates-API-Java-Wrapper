package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.enums.CensusMode;
import com.github.agadar.nsapi.enums.shard.Shard;

/**
 * Intermediate parent class for NationQuery, RegionQuery and WorldQuery,
 * adding options for censusses.
 * 
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <S> the shard type the child class accepts
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class CensusQuery<Q extends NSQuery, S extends Shard, R> extends NSQuery<Q, S, R>
{
    /** See censusIds(...). */
    private int[] censusIds;
    
    /** See censusModes(...). */
    private CensusMode[] censusModes;
    
    /** See censusHistoryFrom(...). */
    private long censusHistoryFrom;
    
    /** See censusHistoryTo(...). */
    private long censusHistoryTo;
    
    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     * 
     * @param resourceValue the resource value
     */
    public CensusQuery(String resourceValue)
    {
        super(resourceValue);
    }
    
    /**
     * Sets the census id's. Does nothing if none of the following shards are
     * set: Census, CensusRanks, CensusName, CensusDescriptions, CensusScale, 
     * or CensusTitle.
     * 
     * @param censusIds the census id's to set
     * @return this
     */
    public final Q censusIds(int... censusIds)
    {
        this.censusIds = censusIds;
        return (Q) this;
    }   
    
    /**
     * Sets the census modes. Does nothing if the Census shard is not selected.
     * Note that the History census mode cannot be combined with any other modes.
     * 
     * @param censusModes the census modes to select
     * @return this
     */
    public final Q censusModes(CensusMode... censusModes)
    {
        this.censusModes = censusModes;
        return (Q) this;
    }
    
    /**
     * Sets the lower bound in UNIX timestamp for the History census mode.
     * Does nothing if the Census shard or the History census mode is not 
     * selected.
     * 
     * @param timestamp the lower bound in UNIX timestamp for the History census 
     * mode
     * @return this
     */
    public final Q censusHistoryFrom(long timestamp)   
    {
        this.censusHistoryFrom = timestamp;
        return (Q) this;
    }
    
    /**
     * Sets the upper bound in UNIX timestamp for the History census mode.
     * Does nothing if the Census shard or the History census mode is not 
     * selected.
     * 
     * @param timestamp the upper bound in UNIX timestamp for the History census 
     * mode
     * @return this
     */
    public final Q censusHistoryTo(long timestamp)   
    {
        this.censusHistoryTo = timestamp;
        return (Q) this;
    }

    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        
        // If census id's were supplied, append them to url
        if (censusIds != null && censusIds.length > 0)
        {
            url += "&scale=" + censusIds[0];
            
            for (int i = 1; i < censusIds.length; i++)
            {
                url += "+" + censusIds[i];
            }
        }
        
        // If modes were supplied, append them to url
        if (censusModes != null && censusModes.length > 0)
        {
            url += "&mode=" + censusModes[0].toString();
            
            for (int i = 1; i < censusModes.length; i++)
            {
                url += "+" + censusModes[i].toString();
            }
        }
        
        // If census history from and to were supplied, append them to url
        if (censusHistoryFrom != 0)
        {
            url += "&from=" + censusHistoryFrom;
        }
        
        if (censusHistoryTo != 0)
        {
            url += "&to=" + censusHistoryTo;
        }

        return url;
    }
}
