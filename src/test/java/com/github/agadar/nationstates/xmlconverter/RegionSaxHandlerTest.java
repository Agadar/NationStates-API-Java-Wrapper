package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.domain.region.Embassy;
import com.github.agadar.nationstates.domain.region.Officer;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassyStatus;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionSaxHandlerTest {

    final String regionsXml = """
            <REGIONS api_version="9">
            <REGION>
            <NAME>Hearts of Iron</NAME>
            <FACTBOOK>factbook text</FACTBOOK>
            <NUMNATIONS>6</NUMNATIONS>
            <NATIONS>greater_helan_eonis:azaroar:evergrand_imperium</NATIONS>
            <DELEGATE>vancouvia</DELEGATE>
            <DELEGATEVOTES>420</DELEGATEVOTES>
            <DELEGATEAUTH>WC</DELEGATEAUTH>
            <FOUNDER>azaroar</FOUNDER>
            <FOUNDERAUTH>XA</FOUNDERAUTH>
            <OFFICERS><OFFICER><NATION>ace_morrigan</NATION>
            <OFFICE>Public Relations Manager</OFFICE>
            <AUTHORITY>XA</AUTHORITY>
            <TIME>1479126089</TIME>
            <BY>azaroar</BY>
            <ORDER>5</ORDER>
            </OFFICER>
            <OFFICER><NATION>azaroar</NATION>
            <OFFICE>Chief of Scientific Advancement</OFFICE>
            <AUTHORITY>WC</AUTHORITY>
            <TIME>1479183029</TIME>
            <BY>azaroar</BY>
            <ORDER>8</ORDER>
            </OFFICER>
            </OFFICERS>
            <POWER>Low</POWER>
            <FLAG>https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png</FLAG>
            <EMBASSIES><EMBASSY>Double Funky 7</EMBASSY>
            <EMBASSY>Eden Prime</EMBASSY></EMBASSIES>

            <LASTUPDATE>1517115631</LASTUPDATE>
            </REGION><REGION>
            <NAME>Hearts of Gold</NAME>
            <FACTBOOK>factbook text</FACTBOOK>
            <NUMNATIONS>6</NUMNATIONS>
            <NATIONS>greater_helan_eonis:azaroar:evergrand_imperium:the_raze_wastes:zumundis:spiira</NATIONS>
            <DELEGATE>0</DELEGATE>
            <DELEGATEVOTES>0</DELEGATEVOTES>
            <DELEGATEAUTH>WCEP</DELEGATEAUTH>
            <FOUNDER>azaroar</FOUNDER>
            <FOUNDERAUTH>XABCEP</FOUNDERAUTH>
            <OFFICERS><OFFICER><NATION>ace_morrigan</NATION>
            <OFFICE>Public Relations Manager</OFFICE>
            <AUTHORITY>AC</AUTHORITY>
            <TIME>1479126089</TIME>
            <BY>azaroar</BY>
            <ORDER>5</ORDER>
            </OFFICER>
            <OFFICER><NATION>azaroar</NATION>
            <OFFICE>Chief of Scientific Advancement</OFFICE>
            <AUTHORITY>P</AUTHORITY>
            <TIME>1479183029</TIME>
            <BY>azaroar</BY>
            <ORDER>8</ORDER>
            </OFFICER>
            </OFFICERS>
            <POWER>Low</POWER>
            <FLAG>https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png</FLAG>
            <EMBASSIES><EMBASSY>Double Funky 7</EMBASSY>
            <EMBASSY>Eden Prime</EMBASSY></EMBASSIES>

            <LASTUPDATE>1517115631</LASTUPDATE>
            </REGION></REGIONS>""";

    @Test
    public void testParseAndFilterByName()
            throws ParserConfigurationException, SAXException, IOException {
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
        officer1.setAssignedBy("azaroar");
        officer1.setAssignedOn(1479126089);
        officer1.setAuthorities(expectedFounderAuths);
        officer1.setNationName("ace_morrigan");
        officer1.setOfficeName("Public Relations Manager");
        officer1.setOrder(5);
        final Officer officer2 = new Officer();
        officer2.setAssignedBy("azaroar");
        officer2.setAssignedOn(1479183029);
        officer2.setAuthorities(expectedFounderAuths);
        officer2.setNationName("azaroar");
        officer2.setOfficeName("Chief of Scientific Advancement");
        officer2.setOrder(8);
        expectedOfficers.add(officer1);
        expectedOfficers.add(officer2);

        final Set<Embassy> expectedEmbassies = new HashSet<>();
        final Embassy embassy1 = new Embassy();
        embassy1.setRegionName("Double Funky 7");
        embassy1.setStatus(EmbassyStatus.ESTABLISHED);
        final Embassy embassy2 = new Embassy();
        embassy2.setRegionName("Eden Prime");
        embassy2.setStatus(EmbassyStatus.ESTABLISHED);
        expectedEmbassies.add(embassy1);
        expectedEmbassies.add(embassy2);

        final Predicate<Region> regionFilter = region -> region.getName().equals("Hearts of Iron");
        final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        final InputStream xmlInput = new ByteArrayInputStream(regionsXml.getBytes(StandardCharsets.UTF_8.name()));
        final RegionSaxHandler regionSaxHandler = new RegionSaxHandler(regionFilter);

        // Act
        saxParser.parse(xmlInput, regionSaxHandler);

        // Assert
        assertEquals(1, regionSaxHandler.filteredRegions.size());
        final Region region = regionSaxHandler.filteredRegions.iterator().next();
        assertEquals("Hearts of Iron", region.getName());
        assertEquals("factbook text", region.getFactbook());
        assertEquals(6, region.getNumberOfNations());
        assertEquals(expectedNations, region.getNationNames());
        assertEquals("vancouvia", region.getDelegate());
        assertEquals(420, region.getDelegateEndorsements());
        assertEquals(expectedDelegateAuths, region.getDelegateAuthorities());
        assertEquals("azaroar", region.getFounder());
        assertEquals(expectedFounderAuths, region.getFounderAuthorities());
        assertEquals(expectedOfficers, region.getOfficers());
        assertEquals("Low", region.getPower());
        assertEquals("https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png",
                region.getFlagUrl());
        assertEquals(expectedEmbassies, region.getEmbassies());
        assertEquals(1517115631, region.getLastUpdate());
    }
}
