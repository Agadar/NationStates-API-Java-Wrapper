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
public class WAQuery extends NSQuery<WAQuery, WAShard, WorldAssembly>
{
    /**
     * Constructor. Sets the council type to query.
     * 
     * @param council the council type to query
     */
    protected WAQuery(Council council)
    {
        super(String.valueOf(council.getCouncilNumber()));
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
        return "https://www.nationstates.net/cgi-bin/api.cgi?wa=1&q=resolution"
                + "+votetrack+dellog+delvotes";
    }

}
