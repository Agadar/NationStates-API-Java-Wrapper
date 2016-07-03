package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Representation of a Nation.
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Nation
{
    public String ADMIRABLE;
    public String ANIMAL;
    public String ANIMALTRAIT;
    public String BANNER;
    public String BANNERS;
    public String CAPITAL;
    public String CATEGORY;
    //census;
    public String CRIME;
    public String CURRENCY;
    public String CUSTOMLEADER;
    public String CUSTOMCAPITAL;
    public String CUSTOMRELIGION;
    //deaths;
    public String DEMONYM;
    public String DEMONYM2;
    public String DEMONYM2PLURAL;
    public int DISPATCHES;
    //dispatchlist;
    public String ENDORSEMENTS; // Should be converted to String[]
    public int FACTBOOKS;
    //factbooklist;
    public int FIRSTLOGIN; // Unix timestamp; should be converted to readable date
    public String FLAG;
    public String FOUNDED;
    //freedom;
    public String FULLNAME;
    public String GAVOTE;
    public int GDP;
    //govt;
    public String GOVTDESC;
    public String GOVTPRIORITY;
    //happenings;
    public int INCOME;
    public String INDUSTRYDESC;
    public String INFLUENCE;
    public String LASTACTIVITY;
    public int LASTLOGIN; // Unix timestamp; should be converted to readable date
    public String LEADER;
    //legislation;
    public String MAJORINDUSTRY;
    public String MOTTO;
    public String NAME;
    public String NOTABLE;
    public int POOREST;
    public int POPULATION; // 14.000 -> 14 billion
    public double PUBLICSECTOR;
    public int RCENSUS;
    public String REGION;
    public String RELIGION;
    public int RICHEST;
    public String SCVOTE;
    //sectors;
    public String SENSIBILITIES;
    public double TAX;
    public int TGCANRECRUIT; // should be boolean
    public int TGCANCAMPAIGN; // should be boolean
    public String TYPE;
    public String WA;
    public int WCENSUS;
    //zombie
}
