package com.github.agadar.nsapi.domain.region;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Representation of an embassy.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EMBASSY")
public class Embassy 
{
    /**
     * The status of the embassy. If the status is null, then the embassy is
     * established. Otherwise, the status can be:
     * 
     * 'invited': if the region was invited by another region to build embassies;
     * 'requested': if the region invited another region to build embassies;
     * 'pending': if the invitation or request was accepted and the embassies are underway;
     * 'rejected': if the region rejected an invitation;
     * 'denied': if the other region rejected the request;
     * 'closing': if the embassies are being closed.
     */
    @XmlAttribute(name = "type")
    public String Status;
    
    /** The name of the region this embassy is with */
    @XmlValue
    public String RegionName;
}
