package com.github.agadar.nationstates.domain.common;

import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a dispatch. Also used for factbooks, as those are a type of
 * dispatch.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DISPATCH")
public class Dispatch {

    /**
     * This dispatch's id.
     */
    @XmlAttribute(name = "id")
    private long id;

    /**
     * The dispatch's title.
     */
    @XmlElement(name = "TITLE")
    private String title = "";

    /**
     * The author nation.
     */
    @XmlElement(name = "AUTHOR")
    private String author = "";

    /**
     * The dispatch's category.
     */
    @XmlElement(name = "CATEGORY")
    @XmlJavaTypeAdapter(DispatchCategory.Adapter.class)
    private DispatchCategory category = DispatchCategory.NULL;

    /**
     * The dispatch's sub-category.
     */
    @XmlElement(name = "SUBCATEGORY")
    @XmlJavaTypeAdapter(DispatchSubCategory.Adapter.class)
    private DispatchSubCategory subCategory = DispatchSubCategory.NULL;

    /**
     * UNIX timestamp when this dispatch was created.
     */
    @XmlElement(name = "CREATED")
    private long createdOn;

    /**
     * UNIX timestamp when this dispatch was last edited.
     */
    @XmlElement(name = "EDITED")
    private long lastEditedOn;

    /**
     * Number of views of this dispatch.
     */
    @XmlElement(name = "VIEWS")
    private int views;

    /**
     * This dispatch's score.
     */
    @XmlElement(name = "SCORE")
    private int score;

    /**
     * This dispatch's text. Not provided by the 'nation' resource: Only by the
     * 'world' resource with the SelectedDispatch shard.
     */
    @XmlElement(name = "TEXT")
    private String text = "";

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Dispatch other = (Dispatch) obj;
        return this.id == other.id;
    }

}
