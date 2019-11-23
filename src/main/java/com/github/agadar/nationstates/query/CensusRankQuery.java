package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.shard.Shard;

import lombok.NonNull;

/**
 * Intermediate parent class that adds options for census ranks.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 * @param <S> the shard type the child class accepts
 */
@SuppressWarnings("rawtypes")
public abstract class CensusRankQuery<Q extends CensusRankQuery, R, S extends Shard> extends CensusQuery<Q, R, S> {

    /**
     * What rank to start the retrieved census rank scores list from.
     */
    private int censusRanksStart;

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     * @param resourceValue     The resource value, e.g. a nation or region name.
     */
    public CensusRankQuery(@NonNull QueryDependencies queryDependencies, @NonNull String resourceValue) {
        super(queryDependencies, resourceValue);
    }

    /**
     * Sets a rank to start the retrieved census rank scores list from. Does nothing
     * if the CensusRanks shard is not selected.
     *
     * @param rank the rank to start the retrieved census rank scores list from
     * @return this
     */
    @SuppressWarnings("unchecked")
    public Q censusRanksStart(int rank) {
        this.censusRanksStart = rank;
        return (Q) this;
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        return censusRanksStart == 0 ? url : url + "&start=" + censusRanksStart;
    }
}
