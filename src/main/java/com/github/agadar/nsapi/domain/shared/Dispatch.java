package com.github.agadar.nsapi.domain.shared;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a dispatch. Also used for factbooks, as those are a type of dispatch.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlRootElement(name = "DISPATCH")
public class Dispatch 
{
    /** This dispatch's id. */
    @XmlAttribute(name = "id")
    public long Id;
    
    /** The dispatch's title. */
    @XmlElement(name = "TITLE")
    public String Title;
    
    /** The author nation. */
    @XmlElement(name = "AUTHOR")
    public String Author;
    
    /** The dispatch's category. */
    @XmlElement(name = "CATEGORY")
    public String Category;
    
    /** The dispatch's subcategory. */
    @XmlElement(name = "SUBCATEGORY")
    public String SubCategory;
    
    /** UNIX timestamp when this dispatch was created. */
    @XmlElement(name = "CREATED")
    public long CreatedOn;
    
    /** UNIX timestamp when this dispatch was last edited. */
    @XmlElement(name = "EDITED")
    public long LastEditedOn;
    
    /** Number of views of this dispatch. */
    @XmlElement(name = "VIEWS")
    public int Views;
    
    /** This dispatch's score. */
    @XmlElement(name = "SCORE")
    public int Score;
    
    /** 
     * This dispatch's text. Not provided by the 'nation' resource:
     * Only by the 'world' resource with the SelectedDispatch shard.
     */
    @XmlElement(name = "TEXT")
    public String Text;
}
