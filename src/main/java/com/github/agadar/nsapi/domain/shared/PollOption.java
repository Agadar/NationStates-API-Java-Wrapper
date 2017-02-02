package com.github.agadar.nsapi.domain.shared;

import com.github.agadar.nsapi.adapter.ColonSeparatedToListAdapter;

import java.util.List;

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
    @XmlJavaTypeAdapter(ColonSeparatedToListAdapter.class)
    public List<String> voterNames;
}
