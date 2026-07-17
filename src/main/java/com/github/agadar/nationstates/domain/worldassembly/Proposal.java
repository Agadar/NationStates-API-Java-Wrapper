package com.github.agadar.nationstates.domain.worldassembly;

import java.util.LinkedHashSet;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.github.agadar.nationstates.adapter.ColonStringToStringSetAdapter;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

/**
 * A proposal to the World Assembly, awaiting approval from the delegates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PROPOSAL")
public class Proposal {

    /**
     * The id of this proposal.
     */
    @XmlAttribute(name = "id")
    private String id = "";

    /**
     * This proposal's category.
     */
    @XmlElement(name = "CATEGORY")
    private String category = "";

    /**
     * UNIX timestamp of when this proposal was created.
     */
    @XmlElement(name = "CREATED")
    private long createdOn;

    /**
     * This proposal's textual content.
     */
    @XmlElement(name = "DESC")
    private String text = "";

    /**
     * Redundant second declaration of this proposal's id. Included for
     * completeness' sake because it is provided by the API.
     */
    @XmlElement(name = "ID")
    private String id2 = "";

    /**
     * This proposal's name.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * The option given for this proposal. Possible values depends on Category.
     */
    @XmlElement(name = "OPTION")
    private String option = "";

    /**
     * Name of the nation that created this proposal.
     */
    @XmlElement(name = "PROPOSED_BY")
    private String proposedBy = "";

    /**
     * The list of delegates that approved this proposal.
     */
    @XmlElement(name = "APPROVALS")
    @XmlJavaTypeAdapter(ColonStringToStringSetAdapter.class)
    private LinkedHashSet<String> approvedBy = new LinkedHashSet<>();

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
