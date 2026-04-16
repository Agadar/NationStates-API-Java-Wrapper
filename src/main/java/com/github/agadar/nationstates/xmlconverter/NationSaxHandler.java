package com.github.agadar.nationstates.xmlconverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;
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

    private final CsvStringToStringSetAdapter commaAdapter = new CsvStringToStringSetAdapter();
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
            case nationTag -> {
                this.currentNation = new Nation();
                this.elementHandler = this::handleNationElement;
            }
            case freedomTag -> this.elementHandler = this::handleFreedomElement;
            case freedomScoresTag -> this.elementHandler = this::handleFreedomScoresElement;
            case govtTag -> this.elementHandler = this::handleGovtElement;
            case deathsTag -> this.elementHandler = this::handleDeathsElement;
            case waBadgesTag -> this.elementHandler = this::handleWaBadgesElement;
            case deathTag, waBadgeTag -> this.currentAttributeValue = atts.getValue("type");
            default -> {
            }
        }
        this.stringBuilder.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
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
            case "NAME" -> currentNation.setName(value);
            case "TYPE" -> currentNation.setGovernmentType(value);
            case "FULLNAME" -> currentNation.setFullName(value);
            case "MOTTO" -> currentNation.setMotto(value);
            case "CATEGORY" -> currentNation.setCategory(value);
            case "UNSTATUS" -> currentNation.setWorldAssemblyStatus(WorldAssemblyStatus.fromString(value));
            case "ENDORSEMENTS" -> currentNation.setEndorsedBy(commaAdapter.unmarshal(value));
            case "REGION" -> currentNation.setRegionName(value);
            case "POPULATION" -> currentNation.setPopulation(Integer.parseInt(value));
            case "TAX" -> currentNation.setAverageIncomeTaxRate(Double.parseDouble(value));
            case "ANIMAL" -> currentNation.setAnimal(value);
            case "CURRENCY" -> currentNation.setCurrency(value);
            case "DEMONYM" -> currentNation.setDemonym(value);
            case "DEMONYM2" -> currentNation.setDemonym2(value);
            case "DEMONYM2PLURAL" -> currentNation.setDemonym2Plural(value);
            case "FLAG" -> currentNation.setFlagUrl(value);
            case "MAJORINDUSTRY" -> currentNation.setMajorIndustry(value);
            case "GOVTPRIORITY" -> currentNation.setGovernmentPriority(value);
            case "DISPATCHES" -> currentNation.setNumberOfDispatches(Integer.parseInt(value));
            case "LEADER" -> currentNation.setLeader(value);
            case "FOUNDED" -> currentNation.setFoundedDescription(value);
            case "LASTACTIVITY" -> currentNation.setLastActivity(value);
            case "FIRSTLOGIN" -> currentNation.setFirstLogin(Long.parseLong(value));
            case "PUBLICSECTOR" -> currentNation.setPublicSector(Double.parseDouble(value));
            case "LASTLOGIN" -> currentNation.setLastLogin(Long.parseLong(value));
            case "INFLUENCE" -> currentNation.setInfluence(InfluenceRank.fromString(value));
            case "FACTBOOKS" -> currentNation.setNumberOfFactbooks(Integer.parseInt(value));
            case "CAPITAL" -> currentNation.setCapital(value);
            case "RELIGION" -> currentNation.setReligion(value);
            default -> {
            }
        }
    }

    private void handleFreedomElement(String currentElement, String value) {
        switch (currentElement) {
            case "CIVILRIGHTS" -> currentNation.getFreedom().setCivilRights(value);
            case "ECONOMY" -> currentNation.getFreedom().setEconomy(value);
            case "POLITICALFREEDOM" -> currentNation.getFreedom().setPoliticalFreedom(value);
            default -> {
            }
        }
    }

    private void handleFreedomScoresElement(String currentElement, String value) {
        int valueAsInt = Integer.parseInt(value);

        switch (currentElement) {
            case "CIVILRIGHTS" -> currentNation.getFreedomScores().setCivilRights(valueAsInt);
            case "ECONOMY" -> currentNation.getFreedomScores().setEconomy(valueAsInt);
            case "POLITICALFREEDOM" -> currentNation.getFreedomScores().setPoliticalFreedom(valueAsInt);
            default -> {
            }
        }
    }

    private void handleGovtElement(String currentElement, String value) {
        double valueAsDouble = Double.parseDouble(value);

        switch (currentElement) {
            case "ADMINISTRATION" -> currentNation.getGovernmentExpenditure().setAdministration(valueAsDouble);
            case "DEFENCE" -> currentNation.getGovernmentExpenditure().setDefence(valueAsDouble);
            case "EDUCATION" -> currentNation.getGovernmentExpenditure().setEducation(valueAsDouble);
            case "ENVIRONMENT" -> currentNation.getGovernmentExpenditure().setEnvironment(valueAsDouble);
            case "HEALTHCARE" -> currentNation.getGovernmentExpenditure().setHealthcare(valueAsDouble);
            case "COMMERCE" -> currentNation.getGovernmentExpenditure().setCommerce(valueAsDouble);
            case "INTERNATIONALAID" -> currentNation.getGovernmentExpenditure().setInternationalAid(valueAsDouble);
            case "LAWANDORDER" -> currentNation.getGovernmentExpenditure().setLawAndOrder(valueAsDouble);
            case "PUBLICTRANSPORT" -> currentNation.getGovernmentExpenditure().setPublicTransport(valueAsDouble);
            case "SOCIALEQUALITY" -> currentNation.getGovernmentExpenditure().setSocialEquality(valueAsDouble);
            case "SPIRITUALITY" -> currentNation.getGovernmentExpenditure().setSpirituality(valueAsDouble);
            case "WELFARE" -> currentNation.getGovernmentExpenditure().setWelfare(valueAsDouble);
            default -> {
            }
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
