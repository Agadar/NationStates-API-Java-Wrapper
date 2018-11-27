package com.github.agadar.nationstates.domain.worldassembly;

import com.github.agadar.nationstates.enumerator.DelegateAction;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents an entry in the World Assembly's delegate logs for the current
 * resolution at vote. Used for DelegateLog, DelegateVotesFor, and
 * DelegateVotesAgainst.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ENTRY")
public class DelegateLogsEntry implements Comparable<DelegateLogsEntry> {

    /**
     * UNIX timestamp of when the vote was made.
     */
    @XmlElement(name = "TIMESTAMP")
    public long timestamp;

    /**
     * Name of the delegate that made the vote.
     */
    @XmlElement(name = "NATION")
    public String delegateName = "";

    /**
     * How the delegate voted (FOR, AGAINST, or WITHDREW). Always blank in
     * DelegateVotesFor and DelegateVotesAgainst.
     */
    @XmlElement(name = "ACTION")
    @XmlJavaTypeAdapter(DelegateAction.Adapter.class)
    public DelegateAction action = DelegateAction.NULL;

    /**
     * The voting power with which the delegate voted (always 0 if WITHDREW).
     */
    @XmlElement(name = "VOTES")
    public int votes;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.timestamp ^ (this.timestamp >>> 32));
        hash = 41 * hash + Objects.hashCode(this.delegateName);
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
        final DelegateLogsEntry other = (DelegateLogsEntry) obj;
        if (this.timestamp != other.timestamp) {
            return false;
        }
        return Objects.equals(this.delegateName, other.delegateName);
    }

    @Override
    public int compareTo(DelegateLogsEntry o) {
        if (this.timestamp > o.timestamp) {
            return -1;
        } else if (this.timestamp < o.timestamp) {
            return 1;
        }
        return 0;
    }

}
