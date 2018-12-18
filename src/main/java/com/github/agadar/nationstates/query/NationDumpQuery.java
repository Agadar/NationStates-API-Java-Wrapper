package com.github.agadar.nationstates.query;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.exception.NationStatesAPIException;
import com.github.agadar.nationstates.xmlconverter.NationSaxHandler;

/**
 * Query for retrieving daily nation dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationDumpQuery extends DailyDumpQuery<NationDumpQuery, Nation> {

    public NationDumpQuery(String baseUrl, String userAgent, String defaultDirectory, DailyDumpMode mode, Predicate<Nation> filter) {
        super(baseUrl, userAgent, defaultDirectory, mode, filter);
    }

    @Override
    protected String getFileName() {
        return "nations.xml.gz";
    }

    @Override
    protected Collection<Nation> translateResponse(GZIPInputStream stream) {
        try {
            var saxParser = SAXParserFactory.newInstance().newSAXParser();
            var nationSaxHandler = new NationSaxHandler(filter);
            saxParser.parse(stream, nationSaxHandler);
            return nationSaxHandler.filteredNations;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new NationStatesAPIException(ex);
        }
    }

}
