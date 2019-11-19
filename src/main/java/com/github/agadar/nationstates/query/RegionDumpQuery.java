package com.github.agadar.nationstates.query;

import java.io.InputStream;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.SAXParserFactory;

import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.xmlconverter.RegionSaxHandler;

/**
 * Query for retrieving daily region dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionDumpQuery extends DailyDumpQuery<RegionDumpQuery, Region> {

    public RegionDumpQuery(String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode,
            Predicate<Region> filter) {
        super(baseUrl, userAgent, defaultDirectory, mode, filter);
    }

    @Override
    protected String getFileName() {
        return "regions.xml.gz";
    }

    @Override
    protected Collection<Region> parseResponse(InputStream stream) throws Exception {
        stream = new GZIPInputStream(stream);
        var saxParser = SAXParserFactory.newInstance().newSAXParser();
        var regionSaxHandler = new RegionSaxHandler(filter);
        saxParser.parse(stream, regionSaxHandler);
        return regionSaxHandler.filteredRegions;
    }

}
