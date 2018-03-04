package com.github.agadar.nationstates.domain.worldassembly;

import com.github.agadar.nationstates.adapter.ColonSeparatedToSetAdapter;
import java.util.Objects;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A proposal to the World Assembly, awaiting approval from the delegates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PROPOSAL")
public class Proposal {

    /**
     * The id of this proposal.
     */
    @XmlAttribute(name = "id")
    public String id;

    /**
     * This proposal's category.
     */
    @XmlElement(name = "CATEGORY")
    public String category;

    /**
     * UNIX timestamp of when this proposal was created.
     */
    @XmlElement(name = "CREATED")
    public long createdOn;

    /**
     * This proposal's textual content.
     */
    @XmlElement(name = "DESC")
    public String text;

    /**
     * Redundant second declaration of this proposal's id. Included for
     * completeness' sake because it is provided by the API.
     */
    @XmlElement(name = "ID")
    public String id2;

    /**
     * This proposal's name.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * The option given for this proposal. Possible values depends on Category.
     */
    @XmlElement(name = "OPTION")
    public String option;

    /**
     * Name of the nation that created this proposal.
     */
    @XmlElement(name = "PROPOSED_BY")
    public String proposedBy;

    /**
     * The list of delegates that approved this proposal.
     */
    @XmlElement(name = "APPROVALS")
    @XmlJavaTypeAdapter(ColonSeparatedToSetAdapter.class)
    public Set<String> approvedBy;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Proposal other = (Proposal) obj;
        return Objects.equals(this.id, other.id);
    }

}
