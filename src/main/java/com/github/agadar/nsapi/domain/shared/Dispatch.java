package com.github.agadar.nsapi.domain.shared;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a dispatch. Also used for factbooks, as those are a type of
 * dispatch.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlRootElement(name = "DISPATCH")
public class Dispatch {

    /**
     * This dispatch's id.
     */
    @XmlAttribute(name = "id")
    public long id;

    /**
     * The dispatch's title.
     */
    @XmlElement(name = "TITLE")
    public String title;

    /**
     * The author nation.
     */
    @XmlElement(name = "AUTHOR")
    public String author;

    /**
     * The dispatch's category.
     */
    @XmlElement(name = "CATEGORY")
    public String category;

    /**
     * The dispatch's subcategory.
     */
    @XmlElement(name = "SUBCATEGORY")
    public String subCategory;

    /**
     * UNIX timestamp when this dispatch was created.
     */
    @XmlElement(name = "CREATED")
    public long createdOn;

    /**
     * UNIX timestamp when this dispatch was last edited.
     */
    @XmlElement(name = "EDITED")
    public long lastEditedOn;

    /**
     * Number of views of this dispatch.
     */
    @XmlElement(name = "VIEWS")
    public int views;

    /**
     * This dispatch's score.
     */
    @XmlElement(name = "SCORE")
    public int score;

    /**
     * This dispatch's text. Not provided by the 'nation' resource: Only by the
     * 'world' resource with the SelectedDispatch shard.
     */
    @XmlElement(name = "TEXT")
    public String text;
}
