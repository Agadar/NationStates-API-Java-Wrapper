package com.github.agadar.nationstates.xmlconverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.github.agadar.nationstates.adapter.ColonStringToStringSetAdapter;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.region.Embassy;
import com.github.agadar.nationstates.domain.region.Officer;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassyStatus;
import com.github.agadar.nationstates.enumerator.WorldAssemblyBadgeType;

/**
 * Parses a region dump xml file. By using this custom parser, we can parse the
 * dump file way faster than the generic JAXB can.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionSaxHandler extends DefaultHandler {

    public final Collection<Region> filteredRegions = new LinkedHashSet<>();

    private final String regionTag = "REGION";
    private final String waBadgesTag = "WABADGES";
    private final String embassiesTag = "EMBASSIES";
    private final String officersTag = "OFFICERS";

    private final String waBadgeTag = "WABADGE";
    private final String embassyTag = "EMBASSY";
    private final String officerTag = "OFFICER";

    private final ColonStringToStringSetAdapter colonAdapter = new ColonStringToStringSetAdapter();
    private final Authority.Adapter authorityAdapter = new Authority.Adapter();
    private final EmbassyStatus.Adapter embassyAdapter = new EmbassyStatus.Adapter();
    private final Predicate<Region> regionFilter;
    private final StringBuilder stringBuilder = new StringBuilder();

    private BiConsumer<String, String> elementHandler = null;
    private Region currentRegion = null;
    private Officer currentOfficer = null;
    private String currentAttributeValue = "";

    public RegionSaxHandler(Predicate<Region> regionFilter) {
        this.regionFilter = regionFilter;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        switch (qName) {
            case regionTag -> {
                this.currentRegion = new Region();
                this.elementHandler = this::handleRegionElement;
            }
            case waBadgesTag -> this.elementHandler = this::handleWaBadgesElement;
            case embassiesTag -> this.elementHandler = this::handleEmbassiesElement;
            case officersTag -> this.elementHandler = this::handleOfficersElement;
            case officerTag -> {
                this.currentOfficer = new Officer();
                this.elementHandler = this::handleOfficerElement;
            }
            case embassyTag, waBadgeTag -> this.currentAttributeValue = atts.getValue("type");
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
        case regionTag:
            if (this.regionFilter.test(currentRegion)) {
                this.filteredRegions.add(currentRegion);
            }
            break;
        case waBadgesTag:
        case embassiesTag:
        case officersTag:
            this.elementHandler = this::handleRegionElement;
            break;
        case officerTag:
            this.elementHandler = this::handleOfficersElement;
            this.currentRegion.getOfficers().add(currentOfficer);
            break;
        default:
            this.elementHandler.accept(qName, this.stringBuilder.toString());
            break;
        }
    }

    private void handleRegionElement(String currentElement, String value) {
        switch (currentElement) {
            case "NAME" -> currentRegion.setName(value);
            case "FLAG" -> currentRegion.setFlagUrl(value);
            case "FACTBOOK" -> currentRegion.setFactbook(value);
            case "LASTUPDATE" -> currentRegion.setLastUpdate(Long.parseLong(value));
            case "DELEGATEAUTH" -> currentRegion.setDelegateAuthorities(authorityAdapter.unmarshal(value));
            case "FOUNDER" -> currentRegion.setFounder(value);
            case "NUMNATIONS" -> currentRegion.setNumberOfNations(Integer.parseInt(value));
            case "DELEGATEVOTES" -> currentRegion.setDelegateEndorsements(Integer.parseInt(value));
            case "DELEGATE" -> currentRegion.setDelegate(value);
            case "FOUNDERAUTH" -> currentRegion.setFounderAuthorities(authorityAdapter.unmarshal(value));
            case "POWER" -> currentRegion.setPower(value);
            case "NATIONS" -> currentRegion.setNationNames(colonAdapter.unmarshal(value));
            default -> {
            }
        }
    }

    private void handleWaBadgesElement(String currentElement, String value) {
        var badge = new WorldAssemblyBadge();
        badge.setSecurityCouncilResolutionId(Integer.parseInt(value));
        badge.setWorldAssemblyBadgeType(WorldAssemblyBadgeType.fromString(this.currentAttributeValue));
        this.currentRegion.getWorldAssemblyBadges().add(badge);
    }

    private void handleEmbassiesElement(String currentElement, String value) {
        var embassy = new Embassy();
        embassy.setRegionName(value);
        embassy.setStatus(embassyAdapter.unmarshal(this.currentAttributeValue));
        this.currentRegion.getEmbassies().add(embassy);
    }

    private void handleOfficersElement(String currentElement, String value) {
        // Empty, seeing as individual Officer entries are handled in
        // handleOfficerElement.
    }

    private void handleOfficerElement(String currentElement, String value) {
        switch (currentElement) {
            case "NATION" -> currentOfficer.setNationName(value);
            case "OFFICE" -> currentOfficer.setOfficeName(value);
            case "ORDER" -> currentOfficer.setOrder(Integer.parseInt(value));
            case "BY" -> currentOfficer.setAssignedBy(value);
            case "AUTHORITY" -> currentOfficer.setAuthorities(authorityAdapter.unmarshal(value));
            case "TIME" -> currentOfficer.setAssignedOn(Long.parseLong(value));
            default -> {
            }
        }
    }
}
