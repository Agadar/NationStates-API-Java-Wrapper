package com.github.agadar.nsapi.query.blueprint;

import com.github.agadar.nsapi.enums.shard.Shard;

/**
 * Intermediate parent class that adds options for census ranks.
 * 
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 * @param <S> the shard type the child class accepts
 */
public abstract class CensusRankQuery<Q extends CensusRankQuery, R, S extends Shard> extends CensusQuery<Q, R, S>
{
    /** What rank to start the retrieved census rank scores list from. */
    private int censusRanksStart;

    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     * 
     * @param resourceValue the resource value
     */
    public CensusRankQuery(String resourceValue)
    {
        super(resourceValue);
    }
    
    /**
     * Sets a rank to start the retrieved census rank scores list from. Does
     * nothing if the CensusRanks shard is not selected.
     * 
     * @param rank the rank to start the retrieved census rank scores list from
     * @return this
     */
    public final Q censusRanksStart(int rank)
    {
        this.censusRanksStart = rank;
        return (Q) this;
    }

    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        return censusRanksStart == 0 ? url : url + "&start=" + censusRanksStart;
    }
}
