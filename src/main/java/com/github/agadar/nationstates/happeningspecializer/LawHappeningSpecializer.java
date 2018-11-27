package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.domain.common.happening.LawHappening;

/**
 * Specializes generic Happenings to LawHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class LawHappeningSpecializer implements IHappeningSpecializer<LawHappening> {

    @Override
    public boolean isOfSpecializedType(Happening happening) {
        return happening.getDescription().contains("Following new legislation in");
    }

    @Override
    public LawHappening toSpecializedType(Happening happening) {
        var splitOnAt = happening.getDescription().split("@@");
        var nation = splitOnAt[1];
        var result = splitOnAt[2];
        result = result.substring(2, result.length() - 1);
        return new LawHappening(happening.getId(), happening.getTimestamp(), happening.getDescription(), nation,
                result);
    }

}
