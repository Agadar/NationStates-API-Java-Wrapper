package com.github.agadar.nationstates.query;

import java.io.InputStream;
import java.util.Scanner;

import lombok.NonNull;

/**
 * A query to the NationStates API's utility resource, retrieving the version
 * number of the latest live API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class VersionQuery extends APIQuery<VersionQuery, Integer> {

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     **/
    public VersionQuery(@NonNull QueryDependencies queryDependencies) {
        super(queryDependencies, "version");
    }

    @Override
    protected String resourceString() {
        return "a";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T parseResponse(InputStream response, Class<T> type) {
        Scanner scanner = new Scanner(response);
        scanner.useDelimiter("\\A");
        String body = scanner.hasNext() ? scanner.next().trim() : "";
        scanner.close();
        return (T) Integer.valueOf(body);
    }
}
