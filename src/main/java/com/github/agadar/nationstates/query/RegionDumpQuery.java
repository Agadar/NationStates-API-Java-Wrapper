package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.IXmlConverter;
import com.github.agadar.nationstates.domain.DailyDumpRegions;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;

/**
 * Query for retrieving daily region dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionDumpQuery extends DailyDumpQuery<RegionDumpQuery, DailyDumpRegions> {

    /**
     * Constructor, accepting a mode.
     *
     * @param xmlConverter
     * @param baseUrl
     * @param userAgent
     * @param defaultDirectory
     * @param mode the daily dump mode to use
     */
    public RegionDumpQuery(IXmlConverter xmlConverter, String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode) {
        super(xmlConverter, baseUrl, userAgent, defaultDirectory, mode);
    }

    @Override
    protected String getFileName() {
        return "regions.xml.gz";
    }
}
