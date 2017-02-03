package com.github.agadar.nationstates.domain.region;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's total RMB posts over a previously specified period of time.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class MostPostsRank {

    /**
     * Name of the nation.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * Number of posts made by this nation.
     */
    @XmlElement(name = "POSTS")
    public int posts;
}
