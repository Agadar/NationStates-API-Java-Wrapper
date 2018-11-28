package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.exception.NationStatesAPIException;
import com.github.agadar.nationstates.exception.NationStatesResourceNotFoundException;

import lombok.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.GZIPInputStream;

/**
 * Query for retrieving daily dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
@SuppressWarnings("rawtypes")
public abstract class DailyDumpQuery<Q extends DailyDumpQuery, R> extends AbstractQuery<Q, R> {

    /**
     * Path to the default download directory.
     */
    private final String defaultDirectory;

    /**
     * What mode to use.
     */
    private final DailyDumpMode mode;

    /**
     * The directory to download the gzip in, instead of the default directory.
     */
    private String downloadDir;

    /**
     * The directory to load the gzip from, instead of the default directory.
     */
    private String readFromDir;

    /**
     * Filter used for selecting a subset of the parsed daily dump file.
     */
    protected final Predicate<R> filter;

    public DailyDumpQuery(String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode,
            Predicate<R> filter) {
        super(baseUrl, userAgent);
        this.mode = mode;
        this.defaultDirectory = defaultDirectory;
        this.filter = filter;
    }

    /**
     * Sets a specific directory to download the gzip file in, which will be used
     * instead of the default directory.
     *
     * @param dir a specific directory to download the gzip in
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q downloadDir(@NonNull String dir) {
        this.downloadDir = dir;
        return (Q) this;
    }

    /**
     * Sets a specific directory to read the gzip file from, which will be used
     * instead of the default directory.
     *
     * @param dir a specific directory to download the gzip in
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q readFromDir(@NonNull String dir) {
        this.readFromDir = dir;
        return (Q) this;
    }

    /**
     * Executes this query, returning the result.
     *
     * @return the result
     */
    public Set<R> execute() {
        validateQueryParameters();
        final boolean downloadAndRead = mode == DailyDumpMode.DOWNLOAD_THEN_READ_LOCAL;

        if (downloadAndRead || mode == DailyDumpMode.DOWNLOAD) {
            // Download.
            final String dir = downloadDir != null && !downloadDir.isEmpty() ? downloadDir : defaultDirectory;
            download(dir);
        }

        if (downloadAndRead || mode == DailyDumpMode.READ_LOCAL) {
            // Read locally.
            final String dir = readFromDir != null && !readFromDir.isEmpty() ? readFromDir : defaultDirectory;
            return readLocal(dir);
        } else if (mode == DailyDumpMode.READ_REMOTE) {
            // Read remotely.
            return makeRequest(buildURL());
        }
        return new HashSet<>();
    }

    /**
     * Gives the file name to use. Daily regions dump is 'regions.xml.gz', daily
     * nations dump is 'nations.xml.gz'.
     *
     * @return the file name to use
     */
    protected abstract String getFileName();

    /**
     * Translates the stream response to the set this Query wishes to return via its
     * execute() function.
     *
     * @param stream the GZIP input stream, as all dump files are in GZIP format
     * @return the translated response
     */
    protected abstract Set<R> translateResponse(GZIPInputStream stream);

    @Override
    protected String buildURL() {
        return super.buildURL() + "pages/" + getFileName();
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (mode == null) {
            throw new IllegalArgumentException("'mode' may not be null!");
        }
    }

    /**
     * Makes a GET request to the NationStates API. Throws exceptions if the call
     * failed. If the requested nations/regions/etc. simply weren't found, it
     * returns an empty set.
     *
     * @param urlStr the url to make the request to
     * @return the retrieved data, or empty if the resource wasn't found
     */
    private Set<R> makeRequest(String urlStr) {
        // Prepare request, then make it
        HttpURLConnection conn = null;
        try {
            final URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", userAgent);
            final int responseCode = conn.getResponseCode();
            final String response = String.format("NationStates API returned: '%s' from URL: %s",
                    responseCode + " " + conn.getResponseMessage(), urlStr);

            // Depending on whether or not an error was returned, either throw
            // it or continue as planned.
            InputStream stream = conn.getErrorStream();
            if (stream == null) {
                stream = conn.getInputStream();
                final GZIPInputStream gzipStream = new GZIPInputStream(stream);
                final Set<R> result = translateResponse(gzipStream);
                closeInputStreamQuietly(gzipStream);
                return result;
            } else {
                closeInputStreamQuietly(stream);

                // If the resource wasn't found...
                if (responseCode == 404) {
                    throw new NationStatesResourceNotFoundException();
                }

                // Else, something worse is going on. Throw an exception.
                throw new NationStatesAPIException(response);
            }
        } catch (IOException ex) {
            throw new NationStatesAPIException(ex);
        } finally {
            // Always close the HttpURLConnection
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Downloads the gzip file. This code is almost identical to makeRequest(...),
     * only it doesn't return anything, 404-codes throw exceptions, and instead of
     * sending the retrieved InputStream to translateResponse(...), it is saved to
     * the file system.
     *
     * @param directory the directory to download it to
     */
    private void download(String directory) {
        // Prepare request, then make it
        HttpURLConnection conn = null;
        try {
            final String urlStr = buildURL();
            final URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", userAgent);
            final int responseCode = conn.getResponseCode();
            final String response = String.format("NationStates API returned: '%s' from URL: %s",
                    responseCode + " " + conn.getResponseMessage(), urlStr);

            // Depending on whether or not an error was returned, either throw
            // it or continue as planned.
            InputStream stream = conn.getErrorStream();
            if (stream == null) {
                final Path path = new File(directory + "\\" + getFileName()).toPath();
                stream = conn.getInputStream();
                Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
                closeInputStreamQuietly(stream);
            } else {
                // Else, throw an exception.
                closeInputStreamQuietly(stream);
                throw new NationStatesAPIException(response);
            }
        } catch (IOException ex) {
            throw new NationStatesAPIException(ex);
        } finally {
            // Always close the HttpURLConnection
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Reads the gzip file from the target directory, returning its parsed contents.
     *
     * @param directory the target directory
     * @return the retrieved daily dump data
     */
    private Set<R> readLocal(String directory) {
        try {
            final GZIPInputStream stream = new GZIPInputStream(new FileInputStream(directory + "\\" + getFileName()));
            final Set<R> obj = translateResponse(stream);
            closeInputStreamQuietly(stream);
            return obj;
        } catch (IOException ex) {
            throw new NationStatesAPIException(ex);
        }
    }
}
