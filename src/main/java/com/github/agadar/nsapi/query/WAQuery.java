package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.enums.Council;
import com.github.agadar.nsapi.enums.shard.WAShard;

/**
 * A query to the NationStates API's World Assembly resource.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class WAQuery extends ShardQuery<WAQuery, WorldAssembly, WAShard>
{
    /** The id of the resolution to retrieve. */
    private int resolutionId;
    
    /**
     * Constructor. Sets the council type to query.
     * 
     * @param council the council type to query
     */
    public WAQuery(Council council)
    {
        super(String.valueOf(council.getCouncilNumber()));
    }
    
    /**
     * Sets the id of the resolution to retrieve. Does nothing if the 
     * Resolution shard is not selected.
     * 
     * @param resolutionId the id of the resolution to retrieve
     * @return this
     */
    public final WAQuery resolutionId(int resolutionId)
    {
        this.resolutionId = resolutionId;
        return this;
    }
    
    @Override
    protected String resourceString()
    {
        return "wa";
    }

    @Override
    protected void validateQueryParameters() throws NationStatesAPIException
    {
        if (resourceValue == null || resourceValue.isEmpty())
        {
            throw new NationStatesAPIException("No council number supplied!");
        }
    }

    @Override
    protected String buildURL()
    {
        String url = super.buildURL();
        return resolutionId == 0 ? url : url + "&id=" + resolutionId;
    }
}
