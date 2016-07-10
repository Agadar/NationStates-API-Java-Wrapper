package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.query.blueprint.APIQuery;
import java.io.InputStream;
import java.util.Scanner;

/**
 * A query to the NationStates API's utility resource, retrieving the version
 * number of the latest live API.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class VersionQuery extends APIQuery<VersionQuery, Integer>
{
    /** Constructor. */
    public VersionQuery()
    {
        super("version");
    }
    
    @Override
    protected String resourceString()
    {
        return "a";
    }

    @Override
    protected Integer translateResponse(InputStream response)
    {
        Scanner s = new Scanner(response).useDelimiter("\\A");
        String body = s.hasNext() ? s.next().trim() : "";
        return Integer.valueOf(body);
    }
}
