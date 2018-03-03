package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.EjectedHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;

/**
 * Specializes generic Happenings to EjectedHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class EjectedHappeningSpecializer implements HappeningSpecializer<EjectedHappening> {

    @Override
    public boolean isOfSpecializedType(Happening happening) {
	if (happening.description == null) {
	    return false;
	}
	return happening.description.contains("was ejected from") || happening.description.contains("banned");
    }

    @Override
    public EjectedHappening toSpecializedType(Happening happening) {
	final String[] splitOnAt = happening.description.split("@@");
	int ejectedNationPos = 1;
	int ejectingNationPos = 3;
	int fromRegionPos = 2;
	boolean banned = false;

	if (splitOnAt[2].contains("was ejected and banned from")) {
	    banned = true;
	} else if (splitOnAt[2].contains("banned")) {
	    ejectedNationPos = 3;
	    ejectingNationPos = 1;
	    fromRegionPos = 4;
	    banned = true;
	}

	final String ejectedNation = splitOnAt[ejectedNationPos];
	final String ejectingNation = splitOnAt[ejectingNationPos];
	final String[] splitOnPercent = splitOnAt[fromRegionPos].split("%%");
	final String fromRegion = splitOnPercent[1];

	return new EjectedHappening(happening.id, happening.timestamp, happening.description, ejectingNation,
		ejectedNation, banned, fromRegion);
    }

}
