package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.NationStatesAPIException;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.xmlconverter.RegionSaxHandler;

import java.io.IOException;

import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * Query for retrieving daily region dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionDumpQuery extends DailyDumpQuery<RegionDumpQuery, Region> {

    public RegionDumpQuery(String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode, Predicate<Region> filter) {
        super(baseUrl, userAgent, defaultDirectory, mode, filter);
    }

    @Override
    protected String getFileName() {
        return "regions.xml.gz";
    }

    @Override
    protected Set<Region> translateResponse(GZIPInputStream stream) {
        try {
            final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            final RegionSaxHandler nationSaxHandler = new RegionSaxHandler(filter);
            saxParser.parse(stream, nationSaxHandler);
            return nationSaxHandler.filteredRegions;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new NationStatesAPIException(ex);
        }
    }

}
