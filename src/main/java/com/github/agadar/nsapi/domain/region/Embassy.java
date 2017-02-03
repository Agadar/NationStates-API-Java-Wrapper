package com.github.agadar.nsapi.domain.region;

import com.github.agadar.nsapi.enums.EmbassyStatus;

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
     * The status of the embassy. The default value is 'ESTABLISHED' because the
     * API does not return a value that can be parsed when an embassy is
     * established.
     */
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(EmbassyStatus.Adapter.class)
    public EmbassyStatus status = EmbassyStatus.ESTABLISHED;

    /**
     * The name of the region this embassy is with.
     */
    @XmlValue
    public String regionName;
}
