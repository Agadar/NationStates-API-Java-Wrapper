package com.github.agadar.nationstates.happeningspecializer.dispatch;

import java.util.regex.Pattern;

import com.github.agadar.nationstates.domain.common.happening.DispatchHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;
import com.github.agadar.nationstates.happeningspecializer.HappeningSpecializer;

/**
 * Specializes generic Happenings to DispatchHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class DispatchHappeningSpecializer implements HappeningSpecializer<DispatchHappening> {

	private final Pattern pattern = Pattern
			.compile("@@(.+)@@ published \"(?!<a href=\"page=dispatch\\/id=.+\">)(.+)\" \\((.+): (.+)\\)\\.");

	@Override
	public boolean isOfSpecializedType(Happening happening) {
		String description = happening.getDescription();
		return pattern.matcher(description).find();
	}

	@Override
	public DispatchHappening toSpecializedType(Happening happening) {
		var matcher = pattern.matcher(happening.getDescription());
		matcher.find();
		String author = matcher.group(1);
		String dispatchName = matcher.group(2);
		var category = DispatchCategory.fromString(matcher.group(3));
		var subcategory = DispatchSubCategory.fromString(matcher.group(4));
		return new DispatchHappening(happening.getId(), happening.getTimestamp(), happening.getDescription(), author, 0,
				dispatchName, category, subcategory);
	}

}
