package com.github.agadar.nationstates.domain.common;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.ColonSeparatedToLinkedHashSetAdapter;

import lombok.Getter;
import lombok.Setter;

/**
 * An option for a regional poll.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OPTION")
public class PollOption {

    /**
     * The id of the poll option.
     */
    @XmlAttribute(name = "id")
    private int id;

    /**
     * The option's text.
     */
    @XmlElement(name = "OPTIONTEXT")
    private String text = "";

    /**
     * The number of votes this option's received.
     */
    @XmlElement(name = "VOTES")
    private int numberOfVotes;

    /**
     * Names of the nations that voted for this option.
     */
    @XmlElement(name = "VOTERS")
    @XmlJavaTypeAdapter(ColonSeparatedToLinkedHashSetAdapter.class)
    private Collection<String> voterNames = new LinkedHashSet<String>();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final PollOption other = (PollOption) obj;
        return this.id == other.id;
    }

}
