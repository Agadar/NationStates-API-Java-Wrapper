package com.github.agadar.nationstates.domain.common;

import com.github.agadar.nationstates.adapter.ColonSeparatedToSetAdapter;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * An option for a regional poll.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OPTION")
public class PollOption {

    /**
     * The id of the poll option.
     */
    @XmlAttribute(name = "id")
    public int id;

    /**
     * The option's text.
     */
    @XmlElement(name = "OPTIONTEXT")
    public String text;

    /**
     * The number of votes this option's received.
     */
    @XmlElement(name = "VOTES")
    public int numberOfVotes;

    /**
     * Names of the nations that voted for this option.
     */
    @XmlElement(name = "VOTERS")
    @XmlJavaTypeAdapter(ColonSeparatedToSetAdapter.class)
    public Set<String> voterNames;

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
