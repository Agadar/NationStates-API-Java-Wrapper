package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.nation.DeathCause;
import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.enumerator.InfluenceRank;
import com.github.agadar.nationstates.enumerator.WorldAssemblyBadgeType;
import com.github.agadar.nationstates.enumerator.WorldAssemblyStatus;

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
public class NationSaxHandlerTest {

    final String nationsXml = "<NATIONS api_version=\"9\">\n"
            + "    <NATION>\n"
            + "        <NAME>Join Colorado</NAME>\n"
            + "        <TYPE>Queen's State</TYPE>\n"
            + "        <FULLNAME>The Queen's State of Join Colorado</FULLNAME>\n"
            + "        <MOTTO>Official Recruiting Nation</MOTTO>\n"
            + "        <CATEGORY>Anarchy</CATEGORY>\n"
            + "        <UNSTATUS>WA Member</UNSTATUS>\n"
            + "        <ENDORSEMENTS>agadar,vancouvia</ENDORSEMENTS>\n"
            + "        <FREEDOM>\n"
            + "            <CIVILRIGHTS>Excessive</CIVILRIGHTS>\n"
            + "            <ECONOMY>Very Strong</ECONOMY>\n"
            + "            <POLITICALFREEDOM>Corrupted</POLITICALFREEDOM>\n"
            + "        </FREEDOM>\n"
            + "        <REGION>Double Funky 7</REGION>\n"
            + "        <POPULATION>5533</POPULATION>\n"
            + "        <TAX>5.8</TAX>\n"
            + "        <ANIMAL>deer</ANIMAL>\n"
            + "        <CURRENCY>betale</CURRENCY>\n"
            + "        <DEMONYM>Join Coloradoan</DEMONYM>\n"
            + "        <DEMONYM2>Join Coloradoan</DEMONYM2>\n"
            + "        <DEMONYM2PLURAL>Join Coloradoans</DEMONYM2PLURAL>\n"
            + "        <FLAG>https://www.nationstates.net/images/flags/Maldives.png</FLAG>\n"
            + "        <MAJORINDUSTRY>Basket Weaving</MAJORINDUSTRY>\n"
            + "        <GOVTPRIORITY>Commerce</GOVTPRIORITY>\n"
            + "        <GOVT>\n"
            + "            <ADMINISTRATION>1.0</ADMINISTRATION>\n"
            + "            <DEFENCE>2.0</DEFENCE>\n"
            + "            <EDUCATION>3.0</EDUCATION>\n"
            + "            <ENVIRONMENT>4.0</ENVIRONMENT>\n"
            + "            <HEALTHCARE>5.0</HEALTHCARE>\n"
            + "            <COMMERCE>100.0</COMMERCE>\n"
            + "            <INTERNATIONALAID>6.0</INTERNATIONALAID>\n"
            + "            <LAWANDORDER>7.0</LAWANDORDER>\n"
            + "            <PUBLICTRANSPORT>8.0</PUBLICTRANSPORT>\n"
            + "            <SOCIALEQUALITY>9.0</SOCIALEQUALITY>\n"
            + "            <SPIRITUALITY>10.0</SPIRITUALITY>\n"
            + "            <WELFARE>11.0</WELFARE>\n"
            + "        </GOVT>\n"
            + "        <FOUNDED>4 years 44 days ago</FOUNDED>\n"
            + "        <FIRSTLOGIN>1387103882</FIRSTLOGIN>\n"
            + "        <LASTLOGIN>1515878943</LASTLOGIN>\n"
            + "        <LASTACTIVITY>14 days ago</LASTACTIVITY>\n"
            + "        <INFLUENCE>Ambassador</INFLUENCE>\n"
            + "        <FREEDOMSCORES>\n"
            + "            <CIVILRIGHTS>87</CIVILRIGHTS>\n"
            + "            <ECONOMY>75</ECONOMY>\n"
            + "            <POLITICALFREEDOM>100</POLITICALFREEDOM>\n"
            + "        </FREEDOMSCORES>\n"
            + "        <PUBLICSECTOR>3.5</PUBLICSECTOR>\n"
            + "        <DEATHS>\n"
            + "            <CAUSE type=\"Old Age\">63.6</CAUSE>\n"
            + "            <CAUSE type=\"Exposure\">16.0</CAUSE>\n"
            + "        </DEATHS>\n"
            + "        <LEADER>the Governor</LEADER>\n"
            + "        <CAPITAL>Capital Heights</CAPITAL>\n"
            + "        <RELIGION>Temple of Doom</RELIGION>\n"
            + "        <FACTBOOKS>2</FACTBOOKS>\n"
            + "        <DISPATCHES>3</DISPATCHES>\n"
            + "        <WABADGES>\n"
            + "            <WABADGE type=\"commend\">420</WABADGE>\n"
            + "            <WABADGE type=\"condemn\">69</WABADGE>\n"
            + "        </WABADGES>\n"
            + "    </NATION>\n"
            + "    <NATION>\n"
            + "        <NAME>Join Alabama</NAME>\n"
            + "        <TYPE>Queen's State</TYPE>\n"
            + "        <FULLNAME>The Queen's State of Join Colorado</FULLNAME>\n"
            + "        <MOTTO>Official Recruiting Nation</MOTTO>\n"
            + "        <CATEGORY>Anarchy</CATEGORY>\n"
            + "        <UNSTATUS>WA Member</UNSTATUS>\n"
            + "        <ENDORSEMENTS>agadar,vancouvia</ENDORSEMENTS>\n"
            + "        <FREEDOM>\n"
            + "            <CIVILRIGHTS>Excessive</CIVILRIGHTS>\n"
            + "            <ECONOMY>Very Strong</ECONOMY>\n"
            + "            <POLITICALFREEDOM>Corrupted</POLITICALFREEDOM>\n"
            + "        </FREEDOM>\n"
            + "        <REGION>Double Funky 7</REGION>\n"
            + "        <POPULATION>5533</POPULATION>\n"
            + "        <TAX>5.8</TAX>\n"
            + "        <ANIMAL>deer</ANIMAL>\n"
            + "        <CURRENCY>betale</CURRENCY>\n"
            + "        <DEMONYM>Join Coloradoan</DEMONYM>\n"
            + "        <DEMONYM2>Join Coloradoan</DEMONYM2>\n"
            + "        <DEMONYM2PLURAL>Join Coloradoans</DEMONYM2PLURAL>\n"
            + "        <FLAG>https://www.nationstates.net/images/flags/Maldives.png</FLAG>\n"
            + "        <MAJORINDUSTRY>Basket Weaving</MAJORINDUSTRY>\n"
            + "        <GOVTPRIORITY>Commerce</GOVTPRIORITY>\n"
            + "        <GOVT>\n"
            + "            <ADMINISTRATION>1.0</ADMINISTRATION>\n"
            + "            <DEFENCE>2.0</DEFENCE>\n"
            + "            <EDUCATION>3.0</EDUCATION>\n"
            + "            <ENVIRONMENT>4.0</ENVIRONMENT>\n"
            + "            <HEALTHCARE>5.0</HEALTHCARE>\n"
            + "            <COMMERCE>100.0</COMMERCE>\n"
            + "            <INTERNATIONALAID>6.0</INTERNATIONALAID>\n"
            + "            <LAWANDORDER>7.0</LAWANDORDER>\n"
            + "            <PUBLICTRANSPORT>8.0</PUBLICTRANSPORT>\n"
            + "            <SOCIALEQUALITY>9.0</SOCIALEQUALITY>\n"
            + "            <SPIRITUALITY>10.0</SPIRITUALITY>\n"
            + "            <WELFARE>11.0</WELFARE>\n"
            + "        </GOVT>\n"
            + "        <FOUNDED>4 years 44 days ago</FOUNDED>\n"
            + "        <FIRSTLOGIN>1387103882</FIRSTLOGIN>\n"
            + "        <LASTLOGIN>1515878943</LASTLOGIN>\n"
            + "        <LASTACTIVITY>14 days ago</LASTACTIVITY>\n"
            + "        <INFLUENCE>Ambassador</INFLUENCE>\n"
            + "        <FREEDOMSCORES>\n"
            + "            <CIVILRIGHTS>87</CIVILRIGHTS>\n"
            + "            <ECONOMY>75</ECONOMY>\n"
            + "            <POLITICALFREEDOM>100</POLITICALFREEDOM>\n"
            + "        </FREEDOMSCORES>\n"
            + "        <PUBLICSECTOR>3.5</PUBLICSECTOR>\n"
            + "        <DEATHS>\n"
            + "            <CAUSE type=\"Old Age\">63.6</CAUSE>\n"
            + "            <CAUSE type=\"Exposure\">16.0</CAUSE>\n"
            + "        </DEATHS>\n"
            + "        <LEADER>the Governor</LEADER>\n"
            + "        <CAPITAL>Capital Heights</CAPITAL>\n"
            + "        <RELIGION>Temple of Doom</RELIGION>\n"
            + "        <FACTBOOKS>2</FACTBOOKS>\n"
            + "        <DISPATCHES>3</DISPATCHES>\n"
            + "        <WABADGES>\n"
            + "            <WABADGE type=\"commend\">420</WABADGE>\n"
            + "            <WABADGE type=\"condemn\">69</WABADGE>\n"
            + "        </WABADGES>\n"
            + "    </NATION>\n"
            + "</NATIONS>";

    @Test
    public void testParseAndFilterByName() throws ParserConfigurationException, SAXException, FileNotFoundException, IOException, URISyntaxException {
        System.out.println("testParseAndFilterByName");

        // Arrange
        final Set<String> expectedEndorsedBy = new HashSet<>();
        expectedEndorsedBy.add("agadar");
        expectedEndorsedBy.add("vancouvia");

        final Set<DeathCause> expectedDeathCauses = new HashSet<>();
        final DeathCause cause1 = new DeathCause();
        cause1.description = "Old Age";
        cause1.percentage = 63.6;
        final DeathCause cause2 = new DeathCause();
        cause1.description = "Exposure";
        cause1.percentage = 16.0;
        expectedDeathCauses.add(cause1);
        expectedDeathCauses.add(cause2);

        final Set<WorldAssemblyBadge> expectedWaBadges = new HashSet<>();
        final WorldAssemblyBadge badge1 = new WorldAssemblyBadge();
        badge1.securityCouncilResolutionId = 420;
        badge1.worldAssemblyBadgeType = WorldAssemblyBadgeType.COMMEND;
        final WorldAssemblyBadge badge2 = new WorldAssemblyBadge();
        badge2.securityCouncilResolutionId = 69;
        badge2.worldAssemblyBadgeType = WorldAssemblyBadgeType.CONDEMN;

        final Predicate<Nation> nationFilter = nation -> nation.name.equals("Join Colorado");
        final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        final InputStream xmlInput = new ByteArrayInputStream(nationsXml.getBytes(StandardCharsets.UTF_8.name()));
        final NationSaxHandler nationSaxHandler = new NationSaxHandler(nationFilter);

        // Act
        saxParser.parse(xmlInput, nationSaxHandler);

        // Assert
        assertEquals(nationSaxHandler.filteredNations.size(), 1);
        final Nation nation = nationSaxHandler.filteredNations.iterator().next();
        assertEquals("Join Colorado", nation.name);
        assertEquals("Queen's State", nation.governmentType);
        assertEquals("The Queen's State of Join Colorado", nation.fullName);
        assertEquals("Official Recruiting Nation", nation.motto);
        assertEquals("Anarchy", nation.category);
        assertEquals(WorldAssemblyStatus.MEMBER, nation.worldAssemblyStatus);
        assertEquals(expectedEndorsedBy, nation.endorsedBy);
        assertEquals("Excessive", nation.freedom.civilRights);
        assertEquals("Very Strong", nation.freedom.economy);
        assertEquals("Corrupted", nation.freedom.politicalFreedom);
        assertEquals("Double Funky 7", nation.regionName);
        assertEquals(5533, nation.population);
        assertEquals(5.8, nation.averageIncomeTaxRate, .01);
        assertEquals("deer", nation.animal);
        assertEquals("betale", nation.currency);
        assertEquals("Join Coloradoan", nation.demonym);
        assertEquals("Join Coloradoan", nation.demonym2);
        assertEquals("Join Coloradoans", nation.demonym2Plural);
        assertEquals("https://www.nationstates.net/images/flags/Maldives.png", nation.flagUrl);
        assertEquals("Basket Weaving", nation.majorIndustry);
        assertEquals("Commerce", nation.governmentPriority);
        assertEquals(1.0, nation.governmentExpenditure.administration, .01);
        assertEquals(2.0, nation.governmentExpenditure.defence, .01);
        assertEquals(3.0, nation.governmentExpenditure.education, .01);
        assertEquals(4.0, nation.governmentExpenditure.environment, .01);
        assertEquals(5.0, nation.governmentExpenditure.healthcare, .01);
        assertEquals(100.0, nation.governmentExpenditure.commerce, .01);
        assertEquals(6.0, nation.governmentExpenditure.internationalAid, .01);
        assertEquals(7.0, nation.governmentExpenditure.lawAndOrder, .01);
        assertEquals(8.0, nation.governmentExpenditure.publicTransport, .01);
        assertEquals(9.0, nation.governmentExpenditure.socialEquality, .01);
        assertEquals(0.0, nation.governmentExpenditure.spirituality, .01);
        assertEquals(11.0, nation.governmentExpenditure.welfare, .01);
        assertEquals("4 years 44 days ago", nation.foundedDescription);
        assertEquals(1387103882, nation.firstLogin);
        assertEquals(1515878943, nation.lastLogin);
        assertEquals("14 days ago", nation.lastActivity);
        assertEquals(InfluenceRank.AMBASSADOR, nation.influence);
        assertEquals(87, nation.freedomScores.civilRights);
        assertEquals(75, nation.freedomScores.economy);
        assertEquals(100, nation.freedomScores.politicalFreedom);
        assertEquals(3.5, nation.publicSector, .01);
        assertEquals(expectedDeathCauses, nation.deaths);
        assertEquals("the Governor", nation.leader);
        assertEquals("Capital Heights", nation.capital);
        assertEquals("Temple of Doom", nation.religion);
        assertEquals(2, nation.numberOfFactbooks);
        assertEquals(3, nation.numberOfDispatches);
        assertEquals(expectedWaBadges, nation.worldAssemblyBadges);
    }
}
