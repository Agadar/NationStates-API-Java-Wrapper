package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.IXmlConverter;
import com.github.agadar.nationstates.domain.DailyDumpNations;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;

/**
 * Query for retrieving daily nation dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationDumpQuery extends DailyDumpQuery<NationDumpQuery, DailyDumpNations> {

    /**
     * Constructor, accepting a mode.
     *
     * @param xmlConverter
     * @param baseUrl
     * @param userAgent
     * @param defaultDirectory
     * @param mode the daily dump mode to use
     */
    public NationDumpQuery(IXmlConverter xmlConverter, String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode) {
        super(xmlConverter, baseUrl, userAgent, defaultDirectory, mode);
    }

    @Override
    protected String getFileName() {
        return "nations.xml.gz";
    }
}
