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

    final String nationsXml = "<NATIONS api_version=\"9\">\n" + "    <NATION>\n"
            + "        <NAME>Join Colorado</NAME>\n" + "        <TYPE>Queen's State</TYPE>\n"
            + "        <FULLNAME>The Queen's State of Join Colorado</FULLNAME>\n"
            + "        <MOTTO>Official Recruiting Nation</MOTTO>\n" + "        <CATEGORY>Anarchy</CATEGORY>\n"
            + "        <UNSTATUS>WA Member</UNSTATUS>\n" + "        <ENDORSEMENTS>agadar,vancouvia</ENDORSEMENTS>\n"
            + "        <FREEDOM>\n" + "            <CIVILRIGHTS>Excessive</CIVILRIGHTS>\n"
            + "            <ECONOMY>Very Strong</ECONOMY>\n"
            + "            <POLITICALFREEDOM>Corrupted</POLITICALFREEDOM>\n" + "        </FREEDOM>\n"
            + "        <REGION>Double Funky 7</REGION>\n" + "        <POPULATION>5533</POPULATION>\n"
            + "        <TAX>5.8</TAX>\n" + "        <ANIMAL>deer</ANIMAL>\n" + "        <CURRENCY>betale</CURRENCY>\n"
            + "        <DEMONYM>Join Coloradoan</DEMONYM>\n" + "        <DEMONYM2>Join Coloradoan</DEMONYM2>\n"
            + "        <DEMONYM2PLURAL>Join Coloradoans</DEMONYM2PLURAL>\n"
            + "        <FLAG>https://www.nationstates.net/images/flags/Maldives.png</FLAG>\n"
            + "        <MAJORINDUSTRY>Basket Weaving</MAJORINDUSTRY>\n"
            + "        <GOVTPRIORITY>Commerce</GOVTPRIORITY>\n" + "        <GOVT>\n"
            + "            <ADMINISTRATION>1.0</ADMINISTRATION>\n" + "            <DEFENCE>2.0</DEFENCE>\n"
            + "            <EDUCATION>3.0</EDUCATION>\n" + "            <ENVIRONMENT>4.0</ENVIRONMENT>\n"
            + "            <HEALTHCARE>5.0</HEALTHCARE>\n" + "            <COMMERCE>100.0</COMMERCE>\n"
            + "            <INTERNATIONALAID>6.0</INTERNATIONALAID>\n" + "            <LAWANDORDER>7.0</LAWANDORDER>\n"
            + "            <PUBLICTRANSPORT>8.0</PUBLICTRANSPORT>\n"
            + "            <SOCIALEQUALITY>9.0</SOCIALEQUALITY>\n" + "            <SPIRITUALITY>10.0</SPIRITUALITY>\n"
            + "            <WELFARE>11.0</WELFARE>\n" + "        </GOVT>\n"
            + "        <FOUNDED>4 years 44 days ago</FOUNDED>\n" + "        <FIRSTLOGIN>1387103882</FIRSTLOGIN>\n"
            + "        <LASTLOGIN>1515878943</LASTLOGIN>\n" + "        <LASTACTIVITY>14 days ago</LASTACTIVITY>\n"
            + "        <INFLUENCE>Ambassador</INFLUENCE>\n" + "        <FREEDOMSCORES>\n"
            + "            <CIVILRIGHTS>87</CIVILRIGHTS>\n" + "            <ECONOMY>75</ECONOMY>\n"
            + "            <POLITICALFREEDOM>100</POLITICALFREEDOM>\n" + "        </FREEDOMSCORES>\n"
            + "        <PUBLICSECTOR>3.5</PUBLICSECTOR>\n" + "        <DEATHS>\n"
            + "            <CAUSE type=\"Old Age\">63.6</CAUSE>\n"
            + "            <CAUSE type=\"Exposure\">16.0</CAUSE>\n" + "        </DEATHS>\n"
            + "        <LEADER>the Governor</LEADER>\n" + "        <CAPITAL>Capital Heights</CAPITAL>\n"
            + "        <RELIGION>Temple of Doom</RELIGION>\n" + "        <FACTBOOKS>2</FACTBOOKS>\n"
            + "        <DISPATCHES>3</DISPATCHES>\n" + "        <WABADGES>\n"
            + "            <WABADGE type=\"commend\">420</WABADGE>\n"
            + "            <WABADGE type=\"condemn\">69</WABADGE>\n" + "        </WABADGES>\n" + "    </NATION>\n"
            + "    <NATION>\n" + "        <NAME>Join Alabama</NAME>\n" + "        <TYPE>Queen's State</TYPE>\n"
            + "        <FULLNAME>The Queen's State of Join Colorado</FULLNAME>\n"
            + "        <MOTTO>Official Recruiting Nation</MOTTO>\n" + "        <CATEGORY>Anarchy</CATEGORY>\n"
            + "        <UNSTATUS>WA Member</UNSTATUS>\n" + "        <ENDORSEMENTS>agadar,vancouvia</ENDORSEMENTS>\n"
            + "        <FREEDOM>\n" + "            <CIVILRIGHTS>Excessive</CIVILRIGHTS>\n"
            + "            <ECONOMY>Very Strong</ECONOMY>\n"
            + "            <POLITICALFREEDOM>Corrupted</POLITICALFREEDOM>\n" + "        </FREEDOM>\n"
            + "        <REGION>Double Funky 7</REGION>\n" + "        <POPULATION>5533</POPULATION>\n"
            + "        <TAX>5.8</TAX>\n" + "        <ANIMAL>deer</ANIMAL>\n" + "        <CURRENCY>betale</CURRENCY>\n"
            + "        <DEMONYM>Join Coloradoan</DEMONYM>\n" + "        <DEMONYM2>Join Coloradoan</DEMONYM2>\n"
            + "        <DEMONYM2PLURAL>Join Coloradoans</DEMONYM2PLURAL>\n"
            + "        <FLAG>https://www.nationstates.net/images/flags/Maldives.png</FLAG>\n"
            + "        <MAJORINDUSTRY>Basket Weaving</MAJORINDUSTRY>\n"
            + "        <GOVTPRIORITY>Commerce</GOVTPRIORITY>\n" + "        <GOVT>\n"
            + "            <ADMINISTRATION>1.0</ADMINISTRATION>\n" + "            <DEFENCE>2.0</DEFENCE>\n"
            + "            <EDUCATION>3.0</EDUCATION>\n" + "            <ENVIRONMENT>4.0</ENVIRONMENT>\n"
            + "            <HEALTHCARE>5.0</HEALTHCARE>\n" + "            <COMMERCE>100.0</COMMERCE>\n"
            + "            <INTERNATIONALAID>6.0</INTERNATIONALAID>\n" + "            <LAWANDORDER>7.0</LAWANDORDER>\n"
            + "            <PUBLICTRANSPORT>8.0</PUBLICTRANSPORT>\n"
            + "            <SOCIALEQUALITY>9.0</SOCIALEQUALITY>\n" + "            <SPIRITUALITY>10.0</SPIRITUALITY>\n"
            + "            <WELFARE>11.0</WELFARE>\n" + "        </GOVT>\n"
            + "        <FOUNDED>4 years 44 days ago</FOUNDED>\n" + "        <FIRSTLOGIN>1387103882</FIRSTLOGIN>\n"
            + "        <LASTLOGIN>1515878943</LASTLOGIN>\n" + "        <LASTACTIVITY>14 days ago</LASTACTIVITY>\n"
            + "        <INFLUENCE>Ambassador</INFLUENCE>\n" + "        <FREEDOMSCORES>\n"
            + "            <CIVILRIGHTS>87</CIVILRIGHTS>\n" + "            <ECONOMY>75</ECONOMY>\n"
            + "            <POLITICALFREEDOM>100</POLITICALFREEDOM>\n" + "        </FREEDOMSCORES>\n"
            + "        <PUBLICSECTOR>3.5</PUBLICSECTOR>\n" + "        <DEATHS>\n"
            + "            <CAUSE type=\"Old Age\">63.6</CAUSE>\n"
            + "            <CAUSE type=\"Exposure\">16.0</CAUSE>\n" + "        </DEATHS>\n"
            + "        <LEADER>the Governor</LEADER>\n" + "        <CAPITAL>Capital Heights</CAPITAL>\n"
            + "        <RELIGION>Temple of Doom</RELIGION>\n" + "        <FACTBOOKS>2</FACTBOOKS>\n"
            + "        <DISPATCHES>3</DISPATCHES>\n" + "        <WABADGES>\n"
            + "            <WABADGE type=\"commend\">420</WABADGE>\n"
            + "            <WABADGE type=\"condemn\">69</WABADGE>\n" + "        </WABADGES>\n" + "    </NATION>\n"
            + "</NATIONS>";

    @Test
    public void testParseAndFilterByName()
            throws ParserConfigurationException, SAXException, FileNotFoundException, IOException {
        System.out.println("testParseAndFilterByName");

        // Arrange
        final Set<String> expectedEndorsedBy = new HashSet<>();
        expectedEndorsedBy.add("agadar");
        expectedEndorsedBy.add("vancouvia");

        final Set<DeathCause> expectedDeathCauses = new HashSet<>();
        final DeathCause cause1 = new DeathCause();
        cause1.setDescription("Old Age");
        cause1.setPercentage(63.6);
        final DeathCause cause2 = new DeathCause();
        cause2.setDescription("Exposure");
        cause2.setPercentage(16.0);
        expectedDeathCauses.add(cause1);
        expectedDeathCauses.add(cause2);

        final Set<WorldAssemblyBadge> expectedWaBadges = new HashSet<>();
        final WorldAssemblyBadge badge1 = new WorldAssemblyBadge();
        badge1.setSecurityCouncilResolutionId(420);
        badge1.setWorldAssemblyBadgeType(WorldAssemblyBadgeType.COMMEND);
        final WorldAssemblyBadge badge2 = new WorldAssemblyBadge();
        badge2.setSecurityCouncilResolutionId(69);
        badge2.setWorldAssemblyBadgeType(WorldAssemblyBadgeType.CONDEMN);
        expectedWaBadges.add(badge1);
        expectedWaBadges.add(badge2);

        final Predicate<Nation> nationFilter = nation -> nation.getName().equals("Join Colorado");
        final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        final InputStream xmlInput = new ByteArrayInputStream(nationsXml.getBytes(StandardCharsets.UTF_8.name()));
        final NationSaxHandler nationSaxHandler = new NationSaxHandler(nationFilter);

        // Act
        saxParser.parse(xmlInput, nationSaxHandler);

        // Assert
        assertEquals(1, nationSaxHandler.filteredNations.size());
        final Nation nation = nationSaxHandler.filteredNations.iterator().next();
        assertEquals("Join Colorado", nation.getName());
        assertEquals("Queen's State", nation.getGovernmentType());
        assertEquals("The Queen's State of Join Colorado", nation.getFullName());
        assertEquals("Official Recruiting Nation", nation.getMotto());
        assertEquals("Anarchy", nation.getCategory());
        assertEquals(WorldAssemblyStatus.MEMBER, nation.getWorldAssemblyStatus());
        assertEquals(expectedEndorsedBy, nation.getEndorsedBy());
        assertEquals("Excessive", nation.getFreedom().getCivilRights());
        assertEquals("Very Strong", nation.getFreedom().getEconomy());
        assertEquals("Corrupted", nation.getFreedom().getPoliticalFreedom());
        assertEquals("Double Funky 7", nation.getRegionName());
        assertEquals(5533, nation.getPopulation());
        assertEquals(5.8, nation.getAverageIncomeTaxRate(), .01);
        assertEquals("deer", nation.getAnimal());
        assertEquals("betale", nation.getCurrency());
        assertEquals("Join Coloradoan", nation.getDemonym());
        assertEquals("Join Coloradoan", nation.getDemonym2());
        assertEquals("Join Coloradoans", nation.getDemonym2Plural());
        assertEquals("https://www.nationstates.net/images/flags/Maldives.png", nation.getFlagUrl());
        assertEquals("Basket Weaving", nation.getMajorIndustry());
        assertEquals("Commerce", nation.getGovernmentPriority());
        assertEquals(1.0, nation.getGovernmentExpenditure().getAdministration(), .01);
        assertEquals(2.0, nation.getGovernmentExpenditure().getDefence(), .01);
        assertEquals(3.0, nation.getGovernmentExpenditure().getEducation(), .01);
        assertEquals(4.0, nation.getGovernmentExpenditure().getEnvironment(), .01);
        assertEquals(5.0, nation.getGovernmentExpenditure().getHealthcare(), .01);
        assertEquals(100.0, nation.getGovernmentExpenditure().getCommerce(), .01);
        assertEquals(6.0, nation.getGovernmentExpenditure().getInternationalAid(), .01);
        assertEquals(7.0, nation.getGovernmentExpenditure().getLawAndOrder(), .01);
        assertEquals(8.0, nation.getGovernmentExpenditure().getPublicTransport(), .01);
        assertEquals(9.0, nation.getGovernmentExpenditure().getSocialEquality(), .01);
        assertEquals(10.0, nation.getGovernmentExpenditure().getSpirituality(), .01);
        assertEquals(11.0, nation.getGovernmentExpenditure().getWelfare(), .01);
        assertEquals("4 years 44 days ago", nation.getFoundedDescription());
        assertEquals(1387103882, nation.getFirstLogin());
        assertEquals(1515878943, nation.getLastLogin());
        assertEquals("14 days ago", nation.getLastActivity());
        assertEquals(InfluenceRank.AMBASSADOR, nation.getInfluence());
        assertEquals(87, nation.getFreedomScores().getCivilRights());
        assertEquals(75, nation.getFreedomScores().getEconomy());
        assertEquals(100, nation.getFreedomScores().getPoliticalFreedom());
        assertEquals(3.5, nation.getPublicSector(), .01);
        assertEquals(expectedDeathCauses, nation.getDeaths());
        assertEquals("the Governor", nation.getLeader());
        assertEquals("Capital Heights", nation.getCapital());
        assertEquals("Temple of Doom", nation.getReligion());
        assertEquals(2, nation.getNumberOfFactbooks());
        assertEquals(3, nation.getNumberOfDispatches());
        assertEquals(expectedWaBadges, nation.getWorldAssemblyBadges());
    }
}
