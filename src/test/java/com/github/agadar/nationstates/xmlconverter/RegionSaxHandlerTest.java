package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.domain.region.Region;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.xml.sax.SAXException;

/**
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionSaxHandlerTest {

    final String regionsXml = "<REGIONS api_version=\"9\">\n"
            + "<REGION>\n"
            + "<NAME>Hearts of Iron</NAME>\n"
            + "<FACTBOOK>factbook text</FACTBOOK>\n"
            + "<NUMNATIONS>6</NUMNATIONS>\n"
            + "<NATIONS>greater_helan_eonis:azaroar:evergrand_imperium:the_raze_wastes:zumundis:spiira</NATIONS>\n"
            + "<DELEGATE>0</DELEGATE>\n"
            + "<DELEGATEVOTES>0</DELEGATEVOTES>\n"
            + "<DELEGATEAUTH>WCEP</DELEGATEAUTH>\n"
            + "<FOUNDER>azaroar</FOUNDER>\n"
            + "<FOUNDERAUTH>XABCEP</FOUNDERAUTH>\n"
            + "<OFFICERS><OFFICER><NATION>ace_morrigan</NATION>\n"
            + "<OFFICE>Public Relations Manager</OFFICE>\n"
            + "<AUTHORITY>AC</AUTHORITY>\n"
            + "<TIME>1479126089</TIME>\n"
            + "<BY>azaroar</BY>\n"
            + "<ORDER>5</ORDER>\n"
            + "</OFFICER>\n"
            + "<OFFICER><NATION>azaroar</NATION>\n"
            + "<OFFICE>Chief of Scientific Advancement</OFFICE>\n"
            + "<AUTHORITY>P</AUTHORITY>\n"
            + "<TIME>1479183029</TIME>\n"
            + "<BY>azaroar</BY>\n"
            + "<ORDER>8</ORDER>\n"
            + "</OFFICER>\n"
            + "</OFFICERS>\n"
            + "<POWER>Low</POWER>\n"
            + "<FLAG>https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png</FLAG>\n"
            + "<EMBASSIES><EMBASSY>Double Funky 7</EMBASSY>\n"
            + "<EMBASSY>Eden Prime</EMBASSY></EMBASSIES>\n"
            + "\n"
            + "<LASTUPDATE>1517115631</LASTUPDATE>\n"
            + "</REGION>"
            + "<REGION>\n"
            + "<NAME>Hearts of Gold</NAME>\n"
            + "<FACTBOOK>factbook text</FACTBOOK>\n"
            + "<NUMNATIONS>6</NUMNATIONS>\n"
            + "<NATIONS>greater_helan_eonis:azaroar:evergrand_imperium:the_raze_wastes:zumundis:spiira</NATIONS>\n"
            + "<DELEGATE>0</DELEGATE>\n"
            + "<DELEGATEVOTES>0</DELEGATEVOTES>\n"
            + "<DELEGATEAUTH>WCEP</DELEGATEAUTH>\n"
            + "<FOUNDER>azaroar</FOUNDER>\n"
            + "<FOUNDERAUTH>XABCEP</FOUNDERAUTH>\n"
            + "<OFFICERS><OFFICER><NATION>ace_morrigan</NATION>\n"
            + "<OFFICE>Public Relations Manager</OFFICE>\n"
            + "<AUTHORITY>AC</AUTHORITY>\n"
            + "<TIME>1479126089</TIME>\n"
            + "<BY>azaroar</BY>\n"
            + "<ORDER>5</ORDER>\n"
            + "</OFFICER>\n"
            + "<OFFICER><NATION>azaroar</NATION>\n"
            + "<OFFICE>Chief of Scientific Advancement</OFFICE>\n"
            + "<AUTHORITY>P</AUTHORITY>\n"
            + "<TIME>1479183029</TIME>\n"
            + "<BY>azaroar</BY>\n"
            + "<ORDER>8</ORDER>\n"
            + "</OFFICER>\n"
            + "</OFFICERS>\n"
            + "<POWER>Low</POWER>\n"
            + "<FLAG>https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png</FLAG>\n"
            + "<EMBASSIES><EMBASSY>Double Funky 7</EMBASSY>\n"
            + "<EMBASSY>Eden Prime</EMBASSY></EMBASSIES>\n"
            + "\n"
            + "<LASTUPDATE>1517115631</LASTUPDATE>\n"
            + "</REGION>"
            + "</REGIONS>";

    @Test
    public void testParseAndFilterByName() throws ParserConfigurationException, SAXException, FileNotFoundException, IOException, URISyntaxException {
        System.out.println("testParseAndFilterByName");

        // Arrange
        final Predicate<Region> regionFilter = region -> region.name.equals("Hearts of Iron");
        final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        final InputStream xmlInput = new ByteArrayInputStream(regionsXml.getBytes(StandardCharsets.UTF_8.name()));
        final RegionSaxHandler regionSaxHandler = new RegionSaxHandler(regionFilter);

        // Act
        saxParser.parse(xmlInput, regionSaxHandler);

        // Assert
        assertEquals(1, regionSaxHandler.filteredRegions.size());
        final Region region = regionSaxHandler.filteredRegions.iterator().next();
    }
}
