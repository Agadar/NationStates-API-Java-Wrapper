package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NSAPI;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.DailyDumpRegions;
import com.github.agadar.nsapi.enums.DailyDumpMode;
import static com.github.agadar.nsapi.query.AbstractQuery.logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.CodeSource;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;

/**
 * Query for retrieving daily dumps from NationStates.
 *
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class DailyDumpQuery<Q extends DailyDumpQuery, R> extends AbstractQuery<Q, R>
{   
    /** Path to the default download directory. */
    private final static String DEFAULT_DIR;
    
    /** What mode to use. */
    private final DailyDumpMode mode;
    
    /** The directory to download the gzip in, instead of the default directory. */
    private String downloadDir;
    
    /** The directory to load the gzip from, instead of the default directory. */
    private String readFromDir;
    
    /** 
     * Static 'constructor' to define the default download dir, which should be 
     * the dir of the running JAR, or where-ever this class is loaded from.
     */
    static
    {
        try
        {
            CodeSource codeSource = DailyDumpQuery.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            DEFAULT_DIR = jarFile.getParentFile().getPath();
        }
        catch (URISyntaxException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }
    
    /**
     * Constructor, accepting a mode.
     * 
     * @param mode the daily dump mode to use
     */
    public DailyDumpQuery(DailyDumpMode mode)
    {
        this.mode = mode;
    }
    
    /**
     * Sets a specific directory to download the gzip file in, which will be 
     * used instead of the default directory.
     * 
     * @param dir a specific directory to download the gzip in
     * @return this
     */
    public final Q downloadDir(String dir)
    {
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
    public final Q readFromDir(String dir)
    {
        this.readFromDir = dir;
        return (Q) this;
    }
    
    /**
     * Gives the file name to use. Daily regions dump is 'regions.xml.gz', daily
     * nations dump is 'nations.xml.gz'.
     * 
     * @return 
     */
    protected abstract String getFileName();
    
    @Override
    protected String buildURL()
    {
        return super.buildURL() + "pages/" + getFileName();
    }

    @Override
    protected R translateResponse(InputStream response)
    {
        try
        {
            response = new GZIPInputStream(response);
            return super.translateResponse(response);
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }

    @Override
    public R execute()
    {
        validateQueryParameters();
        boolean downloadAndRead = mode == DailyDumpMode.DownloadAndRead;
        
        if (downloadAndRead || mode == DailyDumpMode.Download)
        {
            // Download.
            String dir = downloadDir != null && !downloadDir.isEmpty() ? 
                         downloadDir : DEFAULT_DIR;
            download(dir);
        }
        
        if (downloadAndRead || mode == DailyDumpMode.ReadLocal)
        {
            // Read locally.
            String dir = readFromDir != null && !readFromDir.isEmpty() ? 
                         readFromDir : DEFAULT_DIR;
            return readLocal(dir);
        }
        else if (mode == DailyDumpMode.ReadRemote)
        {
            // Read remotely.
            return makeRequest(buildURL());
        }
        
        // If mode == DailyDumpMode.Download, return null.
        return null;
    }

    @Override
    protected void validateQueryParameters()
    {
        super.validateQueryParameters();
        
        if (mode == null)
        {
            throw new NationStatesAPIException("'mode' may not be null!");
        }
    }
    
    /**
     * Downloads the gzip file. This code is almost identical to makeRequest(...),
     * only it doesn't return anything, 404-codes throw exceptions, and instead
     * of sending the retrieved InputStream to translateResponse(...), it is
     * saved to the file system.
     * 
     * @param directory the directory to download it to
     */
    private void download(String directory)
    {
        // Prepare request, then make it
        HttpURLConnection conn = null;
        try
        {
            String urlStr = buildURL();
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", NSAPI.getUserAgent());
            int responseCode = conn.getResponseCode();
            String response = String.format("NationStates API returned: '%s' from URL: %s", 
                responseCode + " " + conn.getResponseMessage(), urlStr);
            
            // Depending on whether or not an error was returned, either throw
            // it or continue as planned.
            InputStream stream = conn.getErrorStream();
            if (stream == null) 
            {
                logger.log(Level.INFO, response);
                Path path = new File(directory + "\\" + getFileName()).toPath();
                Files.copy(conn.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            else
            {
                // Else, throw an exception.
                throw new NationStatesAPIException(response);
            }
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException(ex);
        }
        finally
        {
            // Always close the HttpURLConnection
            if (conn != null) 
            {
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
    private R readLocal(String directory)
    {
        try
        {
            return translateResponse(new FileInputStream(directory + "\\" + getFileName()));
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }
}
