package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a dispatch. Also used for factbooks, as those are a type of dispatch.
 * 
 * @author Martin
 */
@XmlRootElement(name = "DISPATCH")
public class Dispatch 
{
    @XmlAttribute(name = "id")
    public int Id;
    
    @XmlElement(name = "TITLE")
    public String Title;
    
    @XmlElement(name = "AUTHOR")
    public String Author;
    
    @XmlElement(name = "CATEGORY")
    public String Category;
    
    @XmlElement(name = "SUBCATEGORY")
    public String SubCategory;
    
    @XmlElement(name = "CREATED")
    public long CreatedOn;
    
    @XmlElement(name = "EDITED")
    public long LastEditedOn;
    
    @XmlElement(name = "VIEWS")
    public int Views;
    
    @XmlElement(name = "SCORE")
    public int Score;
}
