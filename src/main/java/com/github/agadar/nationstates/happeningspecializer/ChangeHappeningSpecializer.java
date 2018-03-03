package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.ChangeHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;

/**
 * Specializes generic Happenings to ChangeHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class ChangeHappeningSpecializer implements HappeningSpecializer<ChangeHappening> {

    @Override
    public boolean isOfSpecializedType(Happening happening) {
	if (happening.description == null) {
	    return false;
	}
	return happening.description.contains("changed its") || happening.description.contains("altered its")
		|| happening.description.contains("was reclassified from")
		|| happening.description.contains("created a");
    }

    @Override
    public ChangeHappening toSpecializedType(Happening happening) {
	final String[] splitOnAt = happening.description.split("@@");
	final String nation = splitOnAt[1];
	String change = splitOnAt[2];
	change = change.substring(1, change.length() - 1);
	return new ChangeHappening(happening.id, happening.timestamp, happening.description, nation, change);
    }

}
