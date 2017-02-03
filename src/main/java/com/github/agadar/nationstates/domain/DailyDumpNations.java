package com.github.agadar.nationstates.domain;

import com.github.agadar.nationstates.domain.nation.Nation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of nations retrieved from the daily dump.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATIONS")
public class DailyDumpNations {

    @XmlElement(name = "NATION")
    public List<Nation> nations;
}
