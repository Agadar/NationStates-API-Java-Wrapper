package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.adapter.CommaSeparatedToSetAdapter;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.nation.DeathCause;
import com.github.agadar.nationstates.domain.nation.Freedom;
import com.github.agadar.nationstates.domain.nation.FreedomScores;
import com.github.agadar.nationstates.domain.nation.GovernmentExpenditure;
import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.enumerator.InfluenceRank;
import com.github.agadar.nationstates.enumerator.WorldAssemblyBadgeType;
import com.github.agadar.nationstates.enumerator.WorldAssemblyStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parses a nation dump xml file. By using this custom parser, we can parse the
 * dump file way faster than the generic JAXB can.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationSaxHandler extends DefaultHandler {

    public final Set<Nation> filteredNations = new HashSet<>();

    private final String nationTag = "NATION";
    private final String freedomTag = "FREEDOM";
    private final String freedomScoresTag = "FREEDOMSCORES";
    private final String govtTag = "GOVT";
    private final String deathsTag = "DEATHS";
    private final String waBadgesTag = "WABADGES";

    private final String deathTag = "CAUSE";
    private final String waBadgeTag = "WABADGE";

    private final CommaSeparatedToSetAdapter commaAdapter = new CommaSeparatedToSetAdapter();
    private final Stack<String> elementStack = new Stack();
    private final Predicate<Nation> nationFilter;

    private BiConsumer<String, String> elementHandler = null;
    private Nation currentNation = null;
    private String currentAttributeValue = "";

    public NationSaxHandler(Predicate<Nation> nationFilter) {
        this.nationFilter = nationFilter;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        switch (qName) {
            case nationTag:
                this.currentNation = new Nation();
                this.elementHandler = this::handleNationElement;
                break;
            case freedomTag:
                this.currentNation.freedom = new Freedom();
                this.elementHandler = this::handleFreedomElement;
                break;
            case freedomScoresTag:
                this.currentNation.freedomScores = new FreedomScores();
                this.elementHandler = this::handleFreedomScoresElement;
                break;
            case govtTag:
                this.currentNation.governmentExpenditure = new GovernmentExpenditure();
                this.elementHandler = this::handleGovtElement;
                break;
            case deathsTag:
                this.currentNation.deaths = new HashSet<>();
                this.elementHandler = this::handleDeathsElement;
                break;
            case waBadgesTag:
                this.currentNation.worldAssemblyBadges = new HashSet<>();
                this.elementHandler = this::handleWaBadgesElement;
                break;

            case deathTag:
            case waBadgeTag:
                this.currentAttributeValue = atts.getValue("type");
                break;
            default:
                break;
        }
        this.elementStack.push(qName);
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        final String value = new String(ch, start, length).trim();

        if (value.length() == 0) {
            return;
        }
        final String currentElement = this.elementStack.peek();
        this.elementHandler.accept(currentElement, value);
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {

        switch (qName) {
            case nationTag:
                if (this.nationFilter.test(currentNation)) {
                    this.filteredNations.add(currentNation);
                }
                break;
            case freedomTag:
            case freedomScoresTag:
            case govtTag:
            case deathsTag:
            case waBadgesTag:
                this.elementHandler = this::handleNationElement;
                break;
            default:
                break;
        }
        this.elementStack.pop();
    }

    private void handleNationElement(String currentElement, String value) {
        switch (currentElement) {
            case "NAME":
                currentNation.name = value;
                break;
            case "TYPE":
                currentNation.governmentType = value;
                break;
            case "FULLNAME":
                currentNation.fullName = value;
                break;
            case "MOTTO":
                currentNation.motto = value;
                break;
            case "CATEGORY":
                currentNation.category = value;
                break;
            case "UNSTATUS":
                currentNation.worldAssemblyStatus = WorldAssemblyStatus.fromString(value);
                break;
            case "ENDORSEMENTS":
                currentNation.endorsedBy = commaAdapter.unmarshal(value);
                break;
            case "REGION":
                currentNation.regionName = value;
                break;
            case "POPULATION":
                currentNation.population = Integer.parseInt(value);
                break;
            case "TAX":
                currentNation.averageIncomeTaxRate = Double.parseDouble(value);
                break;
            case "ANIMAL":
                currentNation.animal = value;
                break;
            case "CURRENCY":
                currentNation.currency = value;
                break;
            case "DEMONYM":
                currentNation.demonym = value;
                break;
            case "DEMONYM2":
                currentNation.demonym2 = value;
                break;
            case "DEMONYM2PLURAL":
                currentNation.demonym2Plural = value;
                break;
            case "FLAG":
                currentNation.flagUrl = value;
                break;
            case "MAJORINDUSTRY":
                currentNation.majorIndustry = value;
                break;
            case "GOVTPRIORITY":
                currentNation.governmentPriority = value;
                break;
            case "DISPATCHES":
                currentNation.numberOfDispatches = Integer.parseInt(value);
                break;
            case "LEADER":
                currentNation.leader = value;
                break;
            case "FOUNDED":
                currentNation.foundedDescription = value;
                break;
            case "LASTACTIVITY":
                currentNation.lastActivity = value;
                break;
            case "FIRSTLOGIN":
                currentNation.firstLogin = Long.parseLong(value);
                break;
            case "PUBLICSECTOR":
                currentNation.publicSector = Double.parseDouble(value);
                break;
            case "LASTLOGIN":
                currentNation.lastLogin = Long.parseLong(value);
                break;
            case "INFLUENCE":
                currentNation.influence = InfluenceRank.fromString(value);
                break;
            case "FACTBOOKS":
                currentNation.numberOfFactbooks = Integer.parseInt(value);
                break;
            case "CAPITAL":
                currentNation.capital = value;
                break;
            case "RELIGION":
                currentNation.religion = value;
                break;
            default:
                break;
        }
    }

    private void handleFreedomElement(String currentElement, String value) {
        switch (currentElement) {
            case "CIVILRIGHTS":
                currentNation.freedom.civilRights = value;
                break;
            case "ECONOMY":
                currentNation.freedom.economy = value;
                break;
            case "POLITICALFREEDOM":
                currentNation.freedom.politicalFreedom = value;
                break;
            default:
                break;
        }
    }

    private void handleFreedomScoresElement(String currentElement, String value) {
        final int valueAsInt = Integer.parseInt(value);

        switch (currentElement) {
            case "CIVILRIGHTS":
                currentNation.freedomScores.civilRights = valueAsInt;
                break;
            case "ECONOMY":
                currentNation.freedomScores.economy = valueAsInt;
                break;
            case "POLITICALFREEDOM":
                currentNation.freedomScores.politicalFreedom = valueAsInt;
                break;
            default:
                break;
        }
    }

    private void handleGovtElement(String currentElement, String value) {
        final double valueAsDouble = Double.parseDouble(value);

        switch (currentElement) {
            case "ADMINISTRATION":
                currentNation.governmentExpenditure.administration = valueAsDouble;
                break;
            case "DEFENCE":
                currentNation.governmentExpenditure.defence = valueAsDouble;
                break;
            case "EDUCATION":
                currentNation.governmentExpenditure.education = valueAsDouble;
                break;
            case "ENVIRONMENT":
                currentNation.governmentExpenditure.environment = valueAsDouble;
                break;
            case "HEALTHCARE":
                currentNation.governmentExpenditure.healthcare = valueAsDouble;
                break;
            case "COMMERCE":
                currentNation.governmentExpenditure.commerce = valueAsDouble;
                break;
            case "INTERNATIONALAID":
                currentNation.governmentExpenditure.internationalAid = valueAsDouble;
                break;
            case "LAWANDORDER":
                currentNation.governmentExpenditure.lawAndOrder = valueAsDouble;
                break;
            case "PUBLICTRANSPORT":
                currentNation.governmentExpenditure.publicTransport = valueAsDouble;
                break;
            case "SOCIALEQUALITY":
                currentNation.governmentExpenditure.socialEquality = valueAsDouble;
                break;
            case "SPIRITUALITY":
                currentNation.governmentExpenditure.spirituality = valueAsDouble;
                break;
            case "WELFARE":
                currentNation.governmentExpenditure.welfare = valueAsDouble;
                break;
            default:
                break;
        }
    }

    private void handleDeathsElement(String currentElement, String value) {
        final DeathCause deathCause = new DeathCause();
        deathCause.percentage = Double.parseDouble(value);
        deathCause.description = this.currentAttributeValue;
        this.currentNation.deaths.add(deathCause);
    }

    private void handleWaBadgesElement(String currentElement, String value) {
        final WorldAssemblyBadge badge = new WorldAssemblyBadge();
        badge.securityCouncilResolutionId = Integer.parseInt(value);
        badge.worldAssemblyBadgeType = WorldAssemblyBadgeType.fromString(this.currentAttributeValue);
        this.currentNation.worldAssemblyBadges.add(badge);
    }
}
