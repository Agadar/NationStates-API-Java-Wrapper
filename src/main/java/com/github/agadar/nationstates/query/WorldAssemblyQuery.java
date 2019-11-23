package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.domain.worldassembly.WorldAssembly;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.shard.WorldAssemblyShard;

import lombok.NonNull;

/**
 * A query to the NationStates API's World Assembly resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class WorldAssemblyQuery extends ShardQuery<WorldAssemblyQuery, WorldAssembly, WorldAssemblyShard> {

    /**
     * The id of the resolution to retrieve.
     */
    private int resolutionId;

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     * @param council           The council to query.
     */
    public WorldAssemblyQuery(@NonNull QueryDependencies queryDependencies, @NonNull Council council) {
        super(queryDependencies, String.valueOf(council.toInt()));
    }

    /**
     * Sets the id of the resolution to retrieve. Does nothing if the Resolution
     * shard is not selected.
     *
     * @param resolutionId the id of the resolution to retrieve
     * @return this
     */
    public WorldAssemblyQuery resolutionId(int resolutionId) {
        this.resolutionId = resolutionId;
        return this;
    }

    @Override
    protected String resourceString() {
        return "wa";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (resourceValue == null || resourceValue.isEmpty()) {
            throw new IllegalArgumentException("No council number supplied!");
        }
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        return resolutionId == 0 ? url : url + "&id=" + resolutionId;
    }
}
