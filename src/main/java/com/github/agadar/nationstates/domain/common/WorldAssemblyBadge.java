package com.github.agadar.nationstates.domain.common;

import com.github.agadar.nationstates.enumerator.WorldAssemblyBadgeType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents a badge granted by the Security Council to a nation or region.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WABADGE")
public class WorldAssemblyBadge {

    /**
     * The number of the Security Council resolution that granted this badge.
     */
    @XmlValue
    public int securityCouncilResolutionId;

    /**
     * The type of this badge.
     */
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(WorldAssemblyBadgeType.Adapter.class)
    public WorldAssemblyBadgeType worldAssemblyBadgeType = WorldAssemblyBadgeType.NULL;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.securityCouncilResolutionId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorldAssemblyBadge other = (WorldAssemblyBadge) obj;
        return this.securityCouncilResolutionId == other.securityCouncilResolutionId;
    }

}
