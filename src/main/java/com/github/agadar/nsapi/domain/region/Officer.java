package com.github.agadar.nsapi.domain.region;

import com.github.agadar.nsapi.adapter.AuthCodesToAuthNamesAdapter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A regional officer.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OFFICER")
public class Officer 
{
    /** Name of the nation that is fulfilling this officer position. */
    @XmlElement(name = "NATION")
    public String NationName;
    
    /** Name of the officer position. */
    @XmlElement(name = "OFFICE")
    public String OfficeName;
    
    /** The authorities granted to this officer. */
    @XmlElement(name = "AUTHORITY")
    @XmlJavaTypeAdapter(AuthCodesToAuthNamesAdapter.class)
    public List<String> Authorities;
    
    /** UNIX timestamp of when this officer position was assigned. */
    @XmlElement(name = "TIME")
    public long AssignedOn;
    
    /** Name of the nation by which this officer position was assigned. */
    @XmlElement(name = "BY")
    public String AssignedBy;
    
    /** Order of this officer position in the regional factbook. */
    @XmlElement(name = "ORDER")
    public int Order;
}
