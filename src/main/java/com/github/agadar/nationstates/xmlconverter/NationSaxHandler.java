package com.github.agadar.nationstates.xmlconverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.github.agadar.nationstates.adapter.CommaSeparatedToLinkedHashSetAdapter;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.nation.DeathCause;
import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.enumerator.InfluenceRank;
import com.github.agadar.nationstates.enumerator.WorldAssemblyBadgeType;
import com.github.agadar.nationstates.enumerator.WorldAssemblyStatus;

/**
 * Parses a nation dump xml file. By using this custom parser, we can parse the
 * dump file way faster than the generic JAXB can.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationSaxHandler extends DefaultHandler {

    public final Collection<Nation> filteredNations = new LinkedHashSet<>();

    private final String nationTag = "NATION";
    private final String freedomTag = "FREEDOM";
    private final String freedomScoresTag = "FREEDOMSCORES";
    private final String govtTag = "GOVT";
    private final String deathsTag = "DEATHS";
    private final String waBadgesTag = "WABADGES";

    private final String deathTag = "CAUSE";
    private final String waBadgeTag = "WABADGE";

    private final CommaSeparatedToLinkedHashSetAdapter commaAdapter = new CommaSeparatedToLinkedHashSetAdapter();
    private final Predicate<Nation> nationFilter;
    private final StringBuilder stringBuilder = new StringBuilder();

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
            this.elementHandler = this::handleFreedomElement;
            break;
        case freedomScoresTag:
            this.elementHandler = this::handleFreedomScoresElement;
            break;
        case govtTag:
            this.elementHandler = this::handleGovtElement;
            break;
        case deathsTag:
            this.elementHandler = this::handleDeathsElement;
            break;
        case waBadgesTag:
            this.elementHandler = this::handleWaBadgesElement;
            break;

        case deathTag:
        case waBadgeTag:
            this.currentAttributeValue = atts.getValue("type");
            break;
        default:
            break;
        }
        this.stringBuilder.setLength(0);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        for (int i = start; i < start + length; i++) {
            this.stringBuilder.append(ch[i]);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

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
            this.elementHandler.accept(qName, this.stringBuilder.toString());
            break;
        }
    }

    private void handleNationElement(String currentElement, String value) {
        switch (currentElement) {
        case "NAME":
            currentNation.setName(value);
            break;
        case "TYPE":
            currentNation.setGovernmentType(value);
            break;
        case "FULLNAME":
            currentNation.setFullName(value);
            break;
        case "MOTTO":
            currentNation.setMotto(value);
            break;
        case "CATEGORY":
            currentNation.setCategory(value);
            break;
        case "UNSTATUS":
            currentNation.setWorldAssemblyStatus(WorldAssemblyStatus.fromString(value));
            break;
        case "ENDORSEMENTS":
            currentNation.setEndorsedBy(commaAdapter.unmarshal(value));
            break;
        case "REGION":
            currentNation.setRegionName(value);
            break;
        case "POPULATION":
            currentNation.setPopulation(Integer.parseInt(value));
            break;
        case "TAX":
            currentNation.setAverageIncomeTaxRate(Double.parseDouble(value));
            break;
        case "ANIMAL":
            currentNation.setAnimal(value);
            break;
        case "CURRENCY":
            currentNation.setCurrency(value);
            break;
        case "DEMONYM":
            currentNation.setDemonym(value);
            break;
        case "DEMONYM2":
            currentNation.setDemonym2(value);
            break;
        case "DEMONYM2PLURAL":
            currentNation.setDemonym2Plural(value);
            break;
        case "FLAG":
            currentNation.setFlagUrl(value);
            break;
        case "MAJORINDUSTRY":
            currentNation.setMajorIndustry(value);
            break;
        case "GOVTPRIORITY":
            currentNation.setGovernmentPriority(value);
            break;
        case "DISPATCHES":
            currentNation.setNumberOfDispatches(Integer.parseInt(value));
            break;
        case "LEADER":
            currentNation.setLeader(value);
            break;
        case "FOUNDED":
            currentNation.setFoundedDescription(value);
            break;
        case "LASTACTIVITY":
            currentNation.setLastActivity(value);
            break;
        case "FIRSTLOGIN":
            currentNation.setFirstLogin(Long.parseLong(value));
            break;
        case "PUBLICSECTOR":
            currentNation.setPublicSector(Double.parseDouble(value));
            break;
        case "LASTLOGIN":
            currentNation.setLastLogin(Long.parseLong(value));
            break;
        case "INFLUENCE":
            currentNation.setInfluence(InfluenceRank.fromString(value));
            break;
        case "FACTBOOKS":
            currentNation.setNumberOfFactbooks(Integer.parseInt(value));
            break;
        case "CAPITAL":
            currentNation.setCapital(value);
            break;
        case "RELIGION":
            currentNation.setReligion(value);
            break;
        default:
            break;
        }
    }

    private void handleFreedomElement(String currentElement, String value) {
        switch (currentElement) {
        case "CIVILRIGHTS":
            currentNation.getFreedom().setCivilRights(value);
            break;
        case "ECONOMY":
            currentNation.getFreedom().setEconomy(value);
            break;
        case "POLITICALFREEDOM":
            currentNation.getFreedom().setPoliticalFreedom(value);
            break;
        default:
            break;
        }
    }

    private void handleFreedomScoresElement(String currentElement, String value) {
        int valueAsInt = Integer.parseInt(value);

        switch (currentElement) {
        case "CIVILRIGHTS":
            currentNation.getFreedomScores().setCivilRights(valueAsInt);
            break;
        case "ECONOMY":
            currentNation.getFreedomScores().setEconomy(valueAsInt);
            break;
        case "POLITICALFREEDOM":
            currentNation.getFreedomScores().setPoliticalFreedom(valueAsInt);
            break;
        default:
            break;
        }
    }

    private void handleGovtElement(String currentElement, String value) {
        double valueAsDouble = Double.parseDouble(value);

        switch (currentElement) {
        case "ADMINISTRATION":
            currentNation.getGovernmentExpenditure().setAdministration(valueAsDouble);
            break;
        case "DEFENCE":
            currentNation.getGovernmentExpenditure().setDefence(valueAsDouble);
            break;
        case "EDUCATION":
            currentNation.getGovernmentExpenditure().setEducation(valueAsDouble);
            break;
        case "ENVIRONMENT":
            currentNation.getGovernmentExpenditure().setEnvironment(valueAsDouble);
            break;
        case "HEALTHCARE":
            currentNation.getGovernmentExpenditure().setHealthcare(valueAsDouble);
            break;
        case "COMMERCE":
            currentNation.getGovernmentExpenditure().setCommerce(valueAsDouble);
            break;
        case "INTERNATIONALAID":
            currentNation.getGovernmentExpenditure().setInternationalAid(valueAsDouble);
            break;
        case "LAWANDORDER":
            currentNation.getGovernmentExpenditure().setLawAndOrder(valueAsDouble);
            break;
        case "PUBLICTRANSPORT":
            currentNation.getGovernmentExpenditure().setPublicTransport(valueAsDouble);
            break;
        case "SOCIALEQUALITY":
            currentNation.getGovernmentExpenditure().setSocialEquality(valueAsDouble);
            break;
        case "SPIRITUALITY":
            currentNation.getGovernmentExpenditure().setSpirituality(valueAsDouble);
            break;
        case "WELFARE":
            currentNation.getGovernmentExpenditure().setWelfare(valueAsDouble);
            break;
        default:
            break;
        }
    }

    private void handleDeathsElement(String currentElement, String value) {
        var deathCause = new DeathCause();
        deathCause.setPercentage(Double.parseDouble(value));
        deathCause.setDescription(this.currentAttributeValue);
        this.currentNation.getDeaths().add(deathCause);
    }

    private void handleWaBadgesElement(String currentElement, String value) {
        var badge = new WorldAssemblyBadge();
        badge.setSecurityCouncilResolutionId(Integer.parseInt(value));
        badge.setWorldAssemblyBadgeType(WorldAssemblyBadgeType.fromString(this.currentAttributeValue));
        this.currentNation.getWorldAssemblyBadges().add(badge);
    }
}
