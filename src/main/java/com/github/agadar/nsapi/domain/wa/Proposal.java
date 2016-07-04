package com.github.agadar.nsapi.domain.wa;

import com.github.agadar.nsapi.adapter.ColonSeparatedToListAdapter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A proposal to the World Assembly, awaiting approval from the delegates.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PROPOSAL")
public class Proposal 
{
    /** The id of this proposal. */
    @XmlAttribute(name = "id")
    public String Id;
    
    /** This proposal's category. */
    @XmlElement(name = "CATEGORY")
    public String Category;
    
    /** UNIX timestamp of when this proposal was created. */
    @XmlElement(name = "CREATED")
    public long CreatedOn;
    
    /** This proposal's textual content. */
    @XmlElement(name = "DESC")
    public String Text;
    
    /** Redundant second declaration of this proposal's id. 
     *  Included for completeness' sake. */
    @XmlElement(name = "ID")
    public String Id2;
    
    /** This proposal's name. */
    @XmlElement(name = "NAME")
    public String Name;
    
    /** The option given for this proposal. Possible values depends on Category. */
    @XmlElement(name = "OPTION")
    public String Option;
    
    /** Name of the nation that created this proposal. */
    @XmlElement(name = "PROPOSED_BY")
    public String ProposedBy;
    
    /** The list of delegates that approved this proposal. */
    @XmlElement(name = "APPROVALS")
    @XmlJavaTypeAdapter(ColonSeparatedToListAdapter.class)
    public List<String> ApprovedBy;
}
