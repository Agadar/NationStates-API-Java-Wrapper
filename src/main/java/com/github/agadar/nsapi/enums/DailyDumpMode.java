package com.github.agadar.nsapi.enums;

/**
 * The modes available for the daily dump Queries.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public enum DailyDumpMode 
{
    /** Reads the gzip file from the file system. */
    ReadLocal,
    /** Reads the gzip file directly from the server. */
    ReadRemote,
    /** Downloads the gzip file from the server. */
    Download,
    /** Downloads the gzip file from the server, then reads it from the file system. */
    DownloadAndRead,
}
