package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.domain.common.happening.LawHappening;

/**
 * Specializes generic Happenings to LawHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class LawHappeningSpecializer implements HappeningSpecializer<LawHappening> {

    @Override
    public boolean isOfSpecializedType(Happening happening) {
	if (happening.description == null) {
	    return false;
	}
	return happening.description.contains("Following new legislation in");
    }

    @Override
    public LawHappening toSpecializedType(Happening happening) {
	final String[] splitOnAt = happening.description.split("@@");
	final String nation = splitOnAt[1];
	String result = splitOnAt[2];
	result = result.substring(2, result.length() - 1);
	return new LawHappening(happening.id, happening.timestamp, happening.description, nation, result);
    }

}
