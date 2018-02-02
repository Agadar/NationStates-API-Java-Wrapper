package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.domain.region.Embassy;
import com.github.agadar.nationstates.domain.region.Officer;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassyStatus;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
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
            + "<NATIONS>greater_helan_eonis:azaroar:evergrand_imperium</NATIONS>\n"
            + "<DELEGATE>vancouvia</DELEGATE>\n"
            + "<DELEGATEVOTES>420</DELEGATEVOTES>\n"
            + "<DELEGATEAUTH>WC</DELEGATEAUTH>\n"
            + "<FOUNDER>azaroar</FOUNDER>\n"
            + "<FOUNDERAUTH>XA</FOUNDERAUTH>\n"
            + "<OFFICERS><OFFICER><NATION>ace_morrigan</NATION>\n"
            + "<OFFICE>Public Relations Manager</OFFICE>\n"
            + "<AUTHORITY>XA</AUTHORITY>\n"
            + "<TIME>1479126089</TIME>\n"
            + "<BY>azaroar</BY>\n"
            + "<ORDER>5</ORDER>\n"
            + "</OFFICER>\n"
            + "<OFFICER><NATION>azaroar</NATION>\n"
            + "<OFFICE>Chief of Scientific Advancement</OFFICE>\n"
            + "<AUTHORITY>WC</AUTHORITY>\n"
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
        final Set<String> expectedNations = new HashSet<>();
        expectedNations.add("greater_helan_eonis");
        expectedNations.add("azaroar");
        expectedNations.add("evergrand_imperium");

        final Set<Authority> expectedDelegateAuths = new HashSet<>();
        expectedDelegateAuths.add(Authority.WORLD_ASSEMBLY);
        expectedDelegateAuths.add(Authority.COMMUNICATIONS);

        final Set<Authority> expectedFounderAuths = new HashSet<>();
        expectedFounderAuths.add(Authority.EXECUTIVE);
        expectedFounderAuths.add(Authority.APPEARANCE);

        final Set<Officer> expectedOfficers = new HashSet<>();
        final Officer officer1 = new Officer();
        officer1.assignedBy = "azaroar";
        officer1.assignedOn = 1479126089;
        officer1.authorities = expectedFounderAuths;
        officer1.nationName = "ace_morrigan";
        officer1.officeName = "Public Relations Manager";
        officer1.order = 5;
        final Officer officer2 = new Officer();
        officer2.assignedBy = "azaroar";
        officer2.assignedOn = 1479183029;
        officer2.authorities = expectedFounderAuths;
        officer2.nationName = "azaroar";
        officer2.officeName = "Chief of Scientific Advancement";
        officer2.order = 8;
        expectedOfficers.add(officer1);
        expectedOfficers.add(officer2);

        final Set<Embassy> expectedEmbassies = new HashSet<>();
        final Embassy embassy1 = new Embassy();
        embassy1.regionName = "Double Funky 7";
        embassy1.status = EmbassyStatus.ESTABLISHED;
        final Embassy embassy2 = new Embassy();
        embassy2.regionName = "Eden Prime";
        embassy2.status = EmbassyStatus.ESTABLISHED;
        expectedEmbassies.add(embassy1);
        expectedEmbassies.add(embassy2);

        final Predicate<Region> regionFilter = region -> region.name.equals("Hearts of Iron");
        final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        final InputStream xmlInput = new ByteArrayInputStream(regionsXml.getBytes(StandardCharsets.UTF_8.name()));
        final RegionSaxHandler regionSaxHandler = new RegionSaxHandler(regionFilter);

        // Act
        saxParser.parse(xmlInput, regionSaxHandler);

        // Assert
        assertEquals(1, regionSaxHandler.filteredRegions.size());
        final Region region = regionSaxHandler.filteredRegions.iterator().next();
        assertEquals("Hearts of Iron", region.name);
        assertEquals("factbook text", region.factbook);
        assertEquals(6, region.numberOfNations);
        assertEquals(expectedNations, region.nationNames);
        assertEquals("vancouvia", region.delegate);
        assertEquals(420, region.delegateEndorsements);
        assertEquals(expectedDelegateAuths, region.delegateAuthorities);
        assertEquals("azaroar", region.founder);
        assertEquals(expectedFounderAuths, region.founderAuthorities);
        assertEquals(expectedOfficers, region.officers);
        assertEquals("Low", region.power);
        assertEquals("https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png", region.flagUrl);
        assertEquals(expectedEmbassies, region.embassies);
        assertEquals(1517115631, region.lastUpdate);
    }
}
