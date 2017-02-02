package com.github.agadar.nsapi.domain.region;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's total RMB likes received over a previously specified period of
 * time.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class MostLikesRank {

    /**
     * Name of the nation.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * Number of likes received by this nation.
     */
    @XmlElement(name = "LIKES")
    public int likes;
}
