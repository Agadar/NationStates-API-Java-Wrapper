package com.github.agadar.nsapi.domain.world;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * The title of the current or selected census.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSTITLE")
public class CensusTitle {

    /**
     * Id of this census title.
     */
    @XmlAttribute(name = "id")
    public int Id;

    /**
     * Title of this census.
     */
    @XmlValue
    public String Title;
}
