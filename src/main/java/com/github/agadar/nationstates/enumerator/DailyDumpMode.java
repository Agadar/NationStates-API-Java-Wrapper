package com.github.agadar.nationstates.enumerator;

/**
 * The modes available for the daily dump Queries.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum DailyDumpMode {

    /**
     * Reads the gzip file from the file system.
     */
    READ_LOCAL,
    /**
     * Reads the gzip file directly from the server.
     */
    READ_REMOTE,
    /**
     * Downloads the gzip file from the server.
     */
    DOWNLOAD,
    /**
     * Downloads the gzip file from the server, then reads it from the file
     * system.
     */
    DOWNLOAD_THEN_READ_LOCAL;
}
