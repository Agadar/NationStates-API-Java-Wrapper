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
        String description = happening.getDescription();
        return description.contains("changed its") || description.contains("altered its")
                || description.contains("was reclassified from") || description.contains("created a");
    }

    @Override
    public ChangeHappening toSpecializedType(Happening happening) {
        var splitOnAt = happening.getDescription().split("@@");
        String nation = splitOnAt[1];
        String change = splitOnAt[2];
        change = change.substring(1, change.length() - 1);
        return new ChangeHappening(happening.getId(), happening.getTimestamp(), happening.getDescription(), nation,
                change);
    }

}
