package com.github.agadar.nationstates.domain.common.happening;

/**
 * A happening regarding a legislatory change in a nation, e.g. 'Following new
 * legislation in @@terkaulia@@, naturists are jailed regularly for indecent
 * exposure.'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class LawHappening extends Happening {

    /**
     * The name of the nation affected by the legislatory change.
     */
    public String nation;

    /**
     * Description of the result of the legislatory change.
     */
    public String result;

    public LawHappening(long id, long timestamp, String description, String nation, String result) {
	super(id, timestamp, description);
	this.nation = nation;
	this.result = result;
    }

}
