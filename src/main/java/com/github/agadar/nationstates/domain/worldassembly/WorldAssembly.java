package com.github.agadar.nationstates.domain.worldassembly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.exception.NationStatesAPIException;

import lombok.Getter;
import lombok.Setter;

/**
 * Representation of the World Assembly. This class' fields have a 1:1
 * correspondence with the shards in WorldAssemblyShard.java, except for
 * VoteTrackFor, VoteTrackAgainst, DelegateVotesFor, and DelegateVotesAgainst:
 * the former two are both filled by the VoteTrack shard, and the latter two are
 * filled by the DelegateVotes shard.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WA")
public class WorldAssembly {

    /**
     * The number of member nations. Same for both councils.
     */
    @XmlElement(name = "NUMNATIONS")
    private int numberOfMembers;

    /**
     * The number of delegates. Same for both councils.
     */
    @XmlElement(name = "NUMDELEGATES")
    private int numberOfDelegates;

    /**
     * The list of delegates. Same for both councils.
     */
    @XmlElement(name = "DELEGATES")
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private Collection<String> delegates = new LinkedHashSet<String>();

    /**
     * The list of member nations. Same for both councils.
     */
    @XmlElement(name = "MEMBERS")
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private Collection<String> members = new LinkedHashSet<String>();

    /**
     * Most recent happenings. Same for both councils.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    private List<Happening> recentHappenings = new ArrayList<Happening>();

    /**
     * Most recent member log entries. Same for both councils.
     */
    @XmlElementWrapper(name = "MEMBERLOG")
    @XmlElement(name = "EVENT")
    private List<Happening> recentMemberLog = new ArrayList<Happening>();

    /**
     * Current proposed resolutions.
     */
    @XmlElementWrapper(name = "PROPOSALS")
    @XmlElement(name = "PROPOSAL")
    private Collection<Proposal> currentProposals = new LinkedHashSet<Proposal>();

    /**
     * The current resolution at vote, or a specific one if an id is supplied.
     */
    @XmlElement(name = "RESOLUTION")
    private Resolution resolution = new Resolution();

    /**
     * Brief description of the end result of the last proposed resolution.
     */
    @XmlElement(name = "LASTRESOLUTION")
    private String lastResolutionResult = "";

    /**
     * Executed after JAXB finishes unmmarshalling.
     * 
     * @param unmarshaller
     * @param parent
     * @throws NationStatesAPIException If happening specialization failed.
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) throws NationStatesAPIException {
        this.recentHappenings = HappeningSpecializationHelper.specializeHappenings(this.recentHappenings);
        this.recentMemberLog = HappeningSpecializationHelper.specializeHappenings(this.recentMemberLog);
    }
}
