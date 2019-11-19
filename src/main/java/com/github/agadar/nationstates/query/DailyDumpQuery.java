package com.github.agadar.nationstates.query;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.exception.NationStatesAPIException;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Query for retrieving daily dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
@SuppressWarnings("rawtypes")
@Slf4j
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
     * @throws NationStatesAPIException If the query failed.
     */
    public Collection<R> execute() throws NationStatesAPIException {
        validateQueryParameters();
        boolean downloadAndRead = mode == DailyDumpMode.DOWNLOAD_THEN_READ_LOCAL;

        if (downloadAndRead || mode == DailyDumpMode.DOWNLOAD) {
            makeRequest(buildURL(), this::saveToFile);
        }

        if (downloadAndRead || mode == DailyDumpMode.READ_LOCAL) {
            return readLocal();

        } else if (mode == DailyDumpMode.READ_REMOTE) {
            return makeRequest(buildURL(), this::parseResponse);
        }
        return Collections.emptyList();
    }

    /**
     * Gives the file name to use. Daily regions dump is 'regions.xml.gz', daily
     * nations dump is 'nations.xml.gz'.
     *
     * @return the file name to use
     */
    protected abstract String getFileName();

    /**
     * Parses the stream response to the set this Query wishes to return via its
     * execute() function.
     *
     * @param stream The input stream.
     * @return The translated response.
     * @throws Exception If for any reason parsing failed.
     */
    protected abstract Collection<R> parseResponse(InputStream stream) throws Exception;

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

    private Collection<R> saveToFile(InputStream inputStream) throws IOException {
        String dir = decideDownloadDirectory();
        Path path = new File(dir + "\\" + getFileName()).toPath();
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        return Collections.emptyList();
    }

    /**
     * Reads the gzip file from the target directory, returning its parsed contents.
     *
     * @return the retrieved daily dump data
     * @throws NationStatesAPIException If an error occured while reading locally.
     */
    private Collection<R> readLocal() throws NationStatesAPIException {
        try {
            String dir = readFromDir != null && !readFromDir.isEmpty() ? readFromDir : defaultDirectory;
            var stream = new FileInputStream(dir + "\\" + getFileName());
            var obj = parseResponse(stream);
            closeInputStreamQuietly(stream);
            return obj;
        } catch (Exception ex) {
            log.error("An error occured while reading the dump file", ex);
            throw new NationStatesAPIException(ex);
        }
    }

    private String decideDownloadDirectory() {
        return downloadDir != null && !downloadDir.isEmpty() ? downloadDir : defaultDirectory;
    }
}
