package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.DailyDumpNations;
import com.github.agadar.nsapi.domain.DailyDumpRegions;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.*;
import com.github.agadar.nsapi.enums.shard.WorldShard;
import com.github.agadar.nsapi.query.NationDumpQuery;
import com.github.agadar.nsapi.query.NationQuery;
import com.github.agadar.nsapi.query.RegionDumpQuery;
import com.github.agadar.nsapi.query.RegionQuery;
import com.github.agadar.nsapi.query.TelegramQuery;
import com.github.agadar.nsapi.query.VerifyQuery;
import com.github.agadar.nsapi.query.VersionQuery;
import com.github.agadar.nsapi.query.WAQuery;
import com.github.agadar.nsapi.query.WorldQuery;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * The starting point for consumers of this Java wrapper for the NationStates API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class NSAPI
{
    /** The NationStates API version this wrapper uses. */
    public static final int API_VERSION = 8;
    
    /** The user agent with which this library makes requests. */
    private static String USER_AGENT;
    
    /** The JAXBContext for this API. */
    private static JAXBContext jc;
    
    /** The classes for the JAXBContext. */
    private static final List<Class> jaxbContextClasses = new ArrayList<>();
    
    /** Static 'constructor' that sets up the initial JAXBContext. */
    static
    {
        registerJaxbTypes(DailyDumpNations.class, DailyDumpRegions.class,
            World.class, WorldAssembly.class);
    }
    
    /**
     * Adds the given classes to the JAXB context so that they can be parsed to
     * from retrieved XML responses and files. Classes that inherit any of the
     * classes in the domain-package don't need any xml-annotations. Classes
     * that do no inherit those classes, do need xml-annotations.
     * 
     * @param types the classes to add to the JAXB context
     */
    public final static synchronized void registerJaxbTypes(Class... types)
    {
        // Place new types in jaxbContextClasses.
        jaxbContextClasses.addAll(Arrays.asList(types));
        int numberOfClasses = jaxbContextClasses.size();
        
        // Use jaxbContextClasses to reinitialize the JAXB context.
        try
        {
            jc = JAXBContext.newInstance(jaxbContextClasses.toArray(new Class[numberOfClasses]));
        }
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }   
        
    /**
     * Uses JAXB to parse the supplied XML stream to an instance of the
     * specified type.
     * 
     * @param <T> the type to parse to
     * @param xml the XML stream
     * @param toType the type to parse to
     * @return instance of the specified type
     */
    public final static <T> T xmlToObject(InputStream xml, Class<T> toType)
    {
        try 
        {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource xmlstream = new StreamSource(xml);
            JAXBElement<T> je1 = unmarshaller.unmarshal(xmlstream, toType);
            return je1.getValue();
        } 
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }
    
    /**
     * Uses JAXB to parse an object to an output stream.
     * 
     * @param obj object to parse to output stream
     * @return an output stream
     */
    public final static ByteArrayOutputStream objectToXml(Object obj)
    {
        try 
        {
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            marshaller.marshal(obj, stream);
            return stream;
        } 
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }
    
    /**
     * Sets the User Agent. If this is the first time the User Agent is set, then 
     * a version check is automatically done as well.
     * 
     * NationStates moderators should be able to identify you and your script
     * via your User Agent. As such, try providing at least your nation name,
     * and preferably include your e-mail address, a link to a website you own, 
     * or something else that can help them contact you if needed.
     * 
     * @param userAgent the User Agent to use for API calls
     */
    public static void setUserAgent(String userAgent)
    {
        // Make sure the new value is not null or empty.
        if (userAgent == null || userAgent.isEmpty())
        {
            throw new NationStatesAPIException("Tried to set null or empty User Agent!");
        }
        
        // If all is well, set the user agent and do a version check.
        final boolean isNotSetYet = USER_AGENT == null || USER_AGENT.isEmpty();
        USER_AGENT = userAgent;
        
        // Do version check if this is the first time the User Agent is set.
        if (isNotSetYet)
        {       
            int liveVersion = version().execute();

            // Validate live version and log appropriate messages.
            Logger logger = Logger.getLogger(NSAPI.class.getName());
            String start = String.format("Version check: wrapper wants to "
                    + "use version '%s', latest live version is"
                    + " '%s'.", API_VERSION, liveVersion);

            switch (liveVersion)
            {
                case API_VERSION:
                    logger.log(Level.INFO, "{0} Wrapper should work correctly.", start);
                    break;
                case API_VERSION + 1:
                    logger.log(Level.WARNING, "{0} Wrapper may fail to load daily "
                            + "dumps. Please update the wrapper.", start);
                    break;
                default:
                    logger.log(Level.SEVERE, "{0} Wrapper may not work correctly. Please"
                            + " update the wrapper.", start);
            }
        }
    }
    
    /**
     * Getter for the User Agent.
     * 
     * @return the User Agent
     */
    public static String getUserAgent()
    {
        return USER_AGENT;
    }
    
    /**
     * Starts building a nation query, using the given nation name.
     * 
     * @param nationName name of the nation to query
     * @return a new nation query
     */
    public static NationQuery nation(String nationName)
    {
        return new NationQuery(nationName);
    }
    
    /**
     * Starts building a region query, using the given region name.
     * 
     * @param regionName name of the region to query
     * @return a new region query
     */
    public static RegionQuery region(String regionName)
    {
        return new RegionQuery(regionName);
    }
    
    /**
     * Starts building a world query, using the selected shards.
     * 
     * @param shards the selected shards
     * @return a new world query
     */
    public static WorldQuery world(WorldShard... shards)
    {
        return new WorldQuery(shards);
    }
    
    /**
     * Starts building a World Assembly query, using the selected council type.
     * 
     * @param council the council type to query
     * @return a new World Assembly query
     */
    public static WAQuery wa(Council council)
    {
        return new WAQuery(council);
    }
    
    /**
     * Starts building a query that retrieves the version number of the latest 
     * live NationStates API.
     * 
     * @return a new version query
     */
    public static VersionQuery version()
    {
        return new VersionQuery();
    }
    
    /**
     * Starts building a query that verifies a nation.
     * 
     * @param nation the nation to verify
     * @param checksum the verification checksum
     * @return a new verify query
     */
    public static VerifyQuery verify(String nation, String checksum)
    {
        return new VerifyQuery(nation, checksum);
    }
    
    /**
     * Starts building a query that sends (a) telegram(s).
     * 
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param nations the nation(s) to send the telegram to
     * @return a new telegram query
     */
    public static TelegramQuery telegram(String clientKey, String telegramId, String secretKey, String... nations)
    {
        return new TelegramQuery(clientKey, telegramId, secretKey, nations);
    }
    
    /**
     * Starts building a query that retrieves the daily region dump.
     * 
     * @param mode the daily dump mode to use
     * @return a new daily region dump query
     */
    public static RegionDumpQuery regiondump(DailyDumpMode mode)
    {
        return new RegionDumpQuery(mode);
    }
    
    /**
     * Starts building a query that retrieves the daily nation dump. 
     * 
     * Warning: reading the XML file and parsing it to objects may cause a 
     * java.lang.OutOfMemoryError on older machines due to the sheer number of
     * Nation objects being created from parsing the retrieved XML file.
     * 
     * @param mode the daily dump mode to use
     * @return a new daily nation dump query
     */
    public static NationDumpQuery nationdump(DailyDumpMode mode)
    {
        return new NationDumpQuery(mode);
    }
}
