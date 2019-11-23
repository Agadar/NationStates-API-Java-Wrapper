package com.github.agadar.nationstates.query;

import java.io.InputStream;
import java.util.Scanner;

import lombok.NonNull;

/**
 * A query to the NationStates API's utility resource, verifying a user. Users
 * can retrieve their code at https://www.nationstates.net/page=verify_login.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class VerifyQuery extends APIQuery<VerifyQuery, Boolean> {

    /**
     * The nation to verify.
     */
    private final String nation;

    /**
     * The verification checksum.
     */
    private final String checksum;

    /**
     * The application-specific token.
     */
    private String token;

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     * @param nation            The name of the nation to verify.
     * @param checksum          The verification checksum.
     **/
    public VerifyQuery(@NonNull QueryDependencies queryDependencies, @NonNull String nation, @NonNull String checksum) {
        super(queryDependencies, "verify");
        this.nation = nation;
        this.checksum = checksum;
    }

    /**
     * Use an application-specific token. If a token is set, then users can retrieve
     * their code at https://www.nationstates.net/page=verify_login?token=(Your
     * token) instead.
     *
     * @param token application-specific token
     * @return this
     */
    public VerifyQuery token(@NonNull String token) {
        this.token = token;
        return this;
    }

    @Override
    protected String resourceString() {
        return "a";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (nation == null || nation.isEmpty()) {
            throw new IllegalArgumentException("No or empty nation name supplied!");
        }

        if (checksum == null || checksum.isEmpty()) {
            throw new IllegalArgumentException("No or empty checksum supplied!");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T parseResponse(InputStream response, Class<T> type) {
        Scanner scanner = new Scanner(response);
        scanner.useDelimiter("\\A");
        String body = scanner.hasNext() ? scanner.next().trim() : "";
        scanner.close();
        return (T) Boolean.valueOf(body.equals("1"));

    }

    @Override
    protected String buildURL() {
        String url = super.buildURL() + String.format("&nation=%s&checksum=%s", nation, checksum);

        if (token != null && !token.isEmpty()) {
            url += "&token=" + token;
        }

        return url;
    }
}
