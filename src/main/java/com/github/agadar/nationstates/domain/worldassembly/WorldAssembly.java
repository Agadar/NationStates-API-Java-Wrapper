package com.github.agadar.nationstates.domain.worldassembly;

import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;

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
    private LinkedHashSet<String> delegates = new LinkedHashSet<>();

    /**
     * The list of member nations. Same for both councils.
     */
    @XmlElement(name = "MEMBERS")
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private LinkedHashSet<String> members = new LinkedHashSet<>();

    /**
     * Most recent happenings. Same for both councils.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    private ArrayList<Happening> recentHappenings = new ArrayList<>();

    /**
     * Most recent member log entries. Same for both councils.
     */
    @XmlElementWrapper(name = "MEMBERLOG")
    @XmlElement(name = "EVENT")
    private ArrayList<Happening> recentMemberLog = new ArrayList<>();

    /**
     * Current proposed resolutions.
     */
    @XmlElementWrapper(name = "PROPOSALS")
    @XmlElement(name = "PROPOSAL")
    private LinkedHashSet<Proposal> currentProposals = new LinkedHashSet<>();

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
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.recentHappenings = HappeningSpecializationHelper.specializeHappenings(this.recentHappenings);
        this.recentMemberLog = HappeningSpecializationHelper.specializeHappenings(this.recentMemberLog);
    }
}
