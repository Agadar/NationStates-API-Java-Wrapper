package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.domain.region.Embassy;
import com.github.agadar.nationstates.domain.region.Officer;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassyStatus;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            <FRONTIER>1</FRONTIER>
            <FOUNDER>azaroar</FOUNDER>
            <GOVERNOR>greater_helan</GOVERNOR>
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
            <MAGNETISM>5.0</MAGNETISM>
            <FLAG>https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png</FLAG>
            <BANNER>144160</BANNER>
            <BANNERURL>/images/rbanners/uploads/the_rejected_realms__144160.jpg</BANNERURL>
            <EMBASSIES><EMBASSY>Double Funky 7</EMBASSY>
            <EMBASSY type="rejected">Eden Prime</EMBASSY></EMBASSIES>
            <LASTUPDATE>1517115631</LASTUPDATE>
            <LASTMAJORUPDATE>1776484927</LASTMAJORUPDATE>
            <LASTMINORUPDATE>1776441739</LASTMINORUPDATE>  
            </REGION>    
            <REGION>
            <NAME>Hearts of Gold</NAME>
            <FACTBOOK>factbook text</FACTBOOK>
            <NUMNATIONS>6</NUMNATIONS>
            <NATIONS>greater_helan_eonis:azaroar:evergrand_imperium:the_raze_wastes:zumundis:spiira</NATIONS>
            <DELEGATE>0</DELEGATE>
            <DELEGATEVOTES>0</DELEGATEVOTES>
            <DELEGATEAUTH>WCEP</DELEGATEAUTH>
            <FRONTIER>0</FRONTIER>
            <FOUNDER>azaroar</FOUNDER>
            <GOVERNOR>0</GOVERNOR>
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
            <MAGNETISM>0</MAGNETISM>
            <FLAG>https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png</FLAG>
            <BANNER>144161</BANNER>
            <BANNERURL>/images/rbanners/uploads/the_rejected_realms__144161.jpg</BANNERURL>
            <EMBASSIES><EMBASSY>Double Funky 7</EMBASSY>
            <EMBASSY>Eden Prime</EMBASSY></EMBASSIES>
            <LASTUPDATE>1517115631</LASTUPDATE>
            <LASTMAJORUPDATE>1776484928</LASTMAJORUPDATE>
            <LASTMINORUPDATE>1776441740</LASTMINORUPDATE>
            </REGION>
            </REGIONS>""";

    @Test
    public void testParseAndFilterByName() throws Exception {
        System.out.println("testParseAndFilterByName");

        // Arrange
        final Set<String> expectedNations = new HashSet<>();
        expectedNations.add("greater_helan_eonis");
        expectedNations.add("azaroar");
        expectedNations.add("evergrand_imperium");

        final Set<Authority> expectedDelegateAuths = new HashSet<>();
        expectedDelegateAuths.add(Authority.WORLD_ASSEMBLY);
        expectedDelegateAuths.add(Authority.COMMUNICATIONS);

        final Set<Authority> expectedAuths = new HashSet<>();
        expectedAuths.add(Authority.EXECUTIVE);
        expectedAuths.add(Authority.APPEARANCE);

        final Set<Officer> expectedOfficers = new HashSet<>();
        final Officer officer1 = new Officer();
        officer1.setAssignedBy("azaroar");
        officer1.setAssignedOn(Instant.ofEpochSecond(1479126089));
        officer1.setAuthorities(expectedAuths);
        officer1.setNationName("ace_morrigan");
        officer1.setOfficeName("Public Relations Manager");
        officer1.setOrder(5);
        final Officer officer2 = new Officer();
        officer2.setAssignedBy("azaroar");
        officer2.setAssignedOn(Instant.ofEpochSecond(1479183029));
        officer2.setAuthorities(expectedAuths);
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
        embassy2.setStatus(EmbassyStatus.REJECTED);
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
        assertTrue(region.isFrontier());
        assertEquals("azaroar", region.getFounder());
        assertEquals("greater_helan", region.getGovernor());
        assertEquals(expectedOfficers, region.getOfficers());
        assertEquals("Low", region.getPower());
        assertEquals(5.0f, region.getMagnetism());
        assertEquals("https://www.nationstates.net/images/flags/uploads/rflags/hearts_of_iron__785233.png",
                region.getFlagUrl());
        assertEquals("144160", region.getBanner());
        assertEquals("/images/rbanners/uploads/the_rejected_realms__144160.jpg", region.getBannerUrl());
        assertEquals(expectedEmbassies, region.getEmbassies());
        assertEquals(Instant.ofEpochSecond(1517115631), region.getLastUpdate());
        assertEquals(Instant.ofEpochSecond(1776484927), region.getLastMajorUpdate());
        assertEquals(Instant.ofEpochSecond(1776441739), region.getLastMinorUpdate());
    }
}
