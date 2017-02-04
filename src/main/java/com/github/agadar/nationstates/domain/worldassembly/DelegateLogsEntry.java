package com.github.agadar.nationstates.domain.worldassembly;

import com.github.agadar.nationstates.enumerator.DelegateAction;

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
public class DelegateLogsEntry {

    /**
     * UNIX timestamp of when the vote was made.
     */
    @XmlElement(name = "TIMESTAMP")
    public long timestamp;

    /**
     * Name of the delegate that made the vote.
     */
    @XmlElement(name = "NATION")
    public String delegateName;

    /**
     * How the delegate voted (FOR, AGAINST, or WITHDREW). Always blank in
     * DelegateVotesFor and DelegateVotesAgainst.
     */
    @XmlElement(name = "ACTION")
    @XmlJavaTypeAdapter(DelegateAction.Adapter.class)
    public DelegateAction action;

    /**
     * The voting power with which the delegate voted (always 0 if WITHDREW).
     */
    @XmlElement(name = "VOTES")
    public int votes;
}
