package com.github.agadar.nationstates.domain.common.happening;

/**
 * A happening regarding a nation's settings or description change, e.g.
 * '@@d-community@@ changed its national motto to "Abrir todas las jaulas".'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class ChangeHappening extends Happening {

    /**
     * The name of the nation affected by the settings or description change.
     */
    public String nation;

    /**
     * Description of the settings or description change that occured.
     */
    public String change;

    public ChangeHappening(long id, long timestamp, String description, String nation, String change) {
	super(id, timestamp, description);
	this.nation = nation;
	this.change = change;
    }

}
