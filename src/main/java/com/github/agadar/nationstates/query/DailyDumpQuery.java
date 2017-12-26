package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.IXmlConverter;
import com.github.agadar.nationstates.NationStatesAPIException;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPInputStream;

/**
 * Query for retrieving daily dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
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
     * Constructor, accepting a mode.
     *
     * @param xmlConverter
     * @param baseUrl
     * @param userAgent
     * @param defaultDirectory
     * @param mode the daily dump mode to use
     */
    public DailyDumpQuery(IXmlConverter xmlConverter, String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode) {
        super(xmlConverter, baseUrl, userAgent);
        this.mode = mode;
        this.defaultDirectory = defaultDirectory;
    }

    /**
     * Sets a specific directory to download the gzip file in, which will be
     * used instead of the default directory.
     *
     * @param dir a specific directory to download the gzip in
     * @return this
     */
    public final Q downloadDir(String dir) {
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
    public final Q readFromDir(String dir) {
        this.readFromDir = dir;
        return (Q) this;
    }

    /**
     * Gives the file name to use. Daily regions dump is 'regions.xml.gz', daily
     * nations dump is 'nations.xml.gz'.
     *
     * @return the file name to use
     */
    protected abstract String getFileName();

    @Override
    protected String buildURL() {
        return super.buildURL() + "pages/" + getFileName();
    }

    @Override
    protected <T> T translateResponse(InputStream response, Class<T> type) {
        try {
            response = new GZIPInputStream(response);
            return super.translateResponse(response, type);
        } catch (IOException ex) {
            throw new NationStatesAPIException(ex);
        }
    }

    @Override
    public <T> T execute(Class<T> type) {
        validateQueryParameters();
        final boolean downloadAndRead = mode == DailyDumpMode.DOWNLOAD_THEN_READ_LOCAL;

        if (downloadAndRead || mode == DailyDumpMode.DOWNLOAD) {
            // Download.
            final String dir = downloadDir != null && !downloadDir.isEmpty()
                    ? downloadDir : defaultDirectory;
            download(dir);
        }

        if (downloadAndRead || mode == DailyDumpMode.READ_LOCAL) {
            // Read locally.
            final String dir = readFromDir != null && !readFromDir.isEmpty()
                    ? readFromDir : defaultDirectory;
            return readLocal(dir, type);
        } else if (mode == DailyDumpMode.READ_REMOTE) {
            // Read remotely.
            return makeRequest(buildURL(), type);
        }
        return null;
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (mode == null) {
            throw new IllegalArgumentException("'mode' may not be null!");
        }
    }

    /**
     * Downloads the gzip file. This code is almost identical to
     * makeRequest(...), only it doesn't return anything, 404-codes throw
     * exceptions, and instead of sending the retrieved InputStream to
     * translateResponse(...), it is saved to the file system.
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
     * Reads the gzip file from the target directory, returning its parsed
     * contents.
     *
     * @param directory the target directory
     * @return the retrieved daily dump data
     */
    private <T> T readLocal(String directory, Class<T> type) {
        try {
            final InputStream stream = new FileInputStream(directory + "\\" + getFileName());
            final T obj = translateResponse(stream, type);
            closeInputStreamQuietly(stream);
            return obj;
        } catch (IOException ex) {
            throw new NationStatesAPIException(ex);
        }
    }
}
