package com.github.agadar.nationstates.domain.region;

import com.github.agadar.nationstates.enumerator.EmbassyStatus;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of an embassy.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EMBASSY")
public class Embassy {

    /**
     * The status of the embassy. The default value is 'ESTABLISHED' because the API
     * does not return a value that can be parsed when an embassy is established.
     */
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(EmbassyStatus.Adapter.class)
    public EmbassyStatus status = EmbassyStatus.ESTABLISHED;

    /**
     * The name of the region this embassy is with.
     */
    @XmlValue
    public String regionName = "";

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.regionName);
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
        final Embassy other = (Embassy) obj;
        return Objects.equals(this.regionName, other.regionName);
    }

}
