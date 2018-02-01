package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.adapter.ColonSeparatedToSetAdapter;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.region.Embassy;
import com.github.agadar.nationstates.domain.region.Officer;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassyStatus;
import com.github.agadar.nationstates.enumerator.WorldAssemblyBadgeType;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parses a region dump xml file. By using this custom parser, we can parse the
 * dump file way faster than the generic JAXB can.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionSaxHandler extends DefaultHandler {

    public final Set<Region> filteredRegions = new HashSet<>();

    private final String regionTag = "REGION";
    private final String waBadgesTag = "WABADGES";
    private final String embassiesTag = "EMBASSIES";
    private final String officersTag = "OFFICERS";

    private final String waBadgeTag = "WABADGE";
    private final String embassyTag = "EMBASSY";
    private final String officerTag = "OFFICER";

    private final ColonSeparatedToSetAdapter colonAdapter = new ColonSeparatedToSetAdapter();
    private final Authority.Adapter authorityAdapter = new Authority.Adapter();
    private final EmbassyStatus.Adapter embassyAdapter = new EmbassyStatus.Adapter();
    private final Stack<String> elementStack = new Stack();
    private final Predicate<Region> regionFilter;

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
            case regionTag:
                this.currentRegion = new Region();
                this.elementHandler = this::handleRegionElement;
                break;
            case waBadgesTag:
                this.currentRegion.worldAssemblyBadges = new HashSet<>();
                this.elementHandler = this::handleWaBadgesElement;
                break;
            case embassiesTag:
                this.currentRegion.embassies = new HashSet<>();
                this.elementHandler = this::handleEmbassiesElement;
                break;
            case officersTag:
                this.currentRegion.officers = new HashSet<>();
                this.elementHandler = this::handleOfficersElement;
                break;

            case officerTag:
                this.currentOfficer = new Officer();
                this.elementHandler = this::handleOfficerElement;
                break;

            case embassyTag:
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
                this.currentRegion.officers.add(currentOfficer);
                break;
            default:
                break;
        }
        this.elementStack.pop();
    }

    private void handleRegionElement(String currentElement, String value) {
        switch (currentElement) {
            case "NAME":
                currentRegion.name = value;
                break;
            case "FLAG":
                currentRegion.flagUrl = value;
                break;
            case "FACTBOOK":
                currentRegion.factbook = value;
                break;
            case "LASTUPDATE":
                currentRegion.lastUpdate = Long.parseLong(value);
                break;
            case "DELEGATEAUTH":
                currentRegion.delegateAuthorities = authorityAdapter.unmarshal(value);
                break;
            case "FOUNDER":
                currentRegion.founder = value;
                break;
            case "NUMNATIONS":
                currentRegion.numberOfNations = Integer.parseInt(value);
                break;
            case "DELEGATEVOTES":
                currentRegion.delegateEndorsements = Integer.parseInt(value);
                break;
            case "DELEGATE":
                currentRegion.delegate = value;
                break;
            case "FOUNDERAUTH":
                currentRegion.founderAuthorities = authorityAdapter.unmarshal(value);
                break;
            case "POWER":
                currentRegion.power = value;
                break;
            case "NATIONS":
                currentRegion.nationNames = colonAdapter.unmarshal(value);
                break;
            default:
                break;
        }
    }

    private void handleWaBadgesElement(String currentElement, String value) {
        final WorldAssemblyBadge badge = new WorldAssemblyBadge();
        badge.securityCouncilResolutionId = Integer.parseInt(value);
        badge.worldAssemblyBadgeType = WorldAssemblyBadgeType.fromString(this.currentAttributeValue);
        this.currentRegion.worldAssemblyBadges.add(badge);
    }

    private void handleEmbassiesElement(String currentElement, String value) {
        final Embassy embassy = new Embassy();
        embassy.regionName = value;
        embassy.status = embassyAdapter.unmarshal(this.currentAttributeValue);
        this.currentRegion.embassies.add(embassy);
    }

    private void handleOfficersElement(String currentElement, String value) {
        // Empty, seeing as individual Officer entries are handled in handleOfficerElement.
    }

    private void handleOfficerElement(String currentElement, String value) {
        switch (currentElement) {
            case "OFFICE":
                currentOfficer.officeName = value;
                break;
            case "ORDER":
                currentOfficer.order = Integer.parseInt(value);
                break;
            case "BY":
                currentOfficer.assignedBy = value;
                break;
            case "AUTHORITY":
                currentOfficer.authorities = authorityAdapter.unmarshal(value);
                break;
            case "TIME":
                currentOfficer.assignedOn = Long.parseLong(value);
                break;
            default:
                break;
        }
    }
}
