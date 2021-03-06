package com.github.agadar.nationstates.happeningspecializer.ejected;

import java.util.regex.Pattern;

import com.github.agadar.nationstates.domain.common.happening.EjectedHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.happeningspecializer.HappeningSpecializer;

/**
 * Specializes generic Happenings to EjectedHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class XWasEjectedFromYByZHappeningSpecializer implements HappeningSpecializer<EjectedHappening> {

	private final Pattern pattern = Pattern.compile("@@(.+)@@ was ejected from %%(.+)%% by @@(.+)@@\\.");

	@Override
	public boolean isOfSpecializedType(Happening happening) {
		String description = happening.getDescription();
		return pattern.matcher(description).find();
	}

	@Override
	public EjectedHappening toSpecializedType(Happening happening) {
		var matcher = pattern.matcher(happening.getDescription());
		matcher.find();
		String ejectedNation = matcher.group(1);
		String region = matcher.group(2);
		String ejectingNation = matcher.group(3);
		return new EjectedHappening(happening.getId(), happening.getTimestamp(), happening.getDescription(),
				ejectingNation, ejectedNation, false, region);
	}
}
