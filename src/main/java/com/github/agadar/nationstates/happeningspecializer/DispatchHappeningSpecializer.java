package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.DispatchHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;

/**
 * Specializes generic Happenings to DispatchHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class DispatchHappeningSpecializer implements IHappeningSpecializer<DispatchHappening> {

    @Override
    public boolean isOfSpecializedType(Happening happening) {
        return happening.getDescription().contains("page=dispatch");
    }

    @Override
    public DispatchHappening toSpecializedType(Happening happening) {
        var splitOnAt = happening.getDescription().split("@@");
        String nation = splitOnAt[1];
        var splitOnParenthesis = splitOnAt[2].split("\"");

        // Dispatch id.
        String containingId = splitOnParenthesis[2];
        containingId = containingId.substring(17, containingId.length());
        long dispatchId = Long.parseLong(containingId);

        // Dispatch name.
        String containingName = splitOnParenthesis[3];
        String dispatchName = containingName.substring(1, containingName.length() - 4);

        // Dispatch categories.
        String containingCategories = splitOnParenthesis[4];
        containingCategories = containingCategories.substring(2, containingCategories.length() - 2);
        var containingCategoriesSplit = containingCategories.split(":");
        var dispatchCategory = DispatchCategory.fromString(containingCategoriesSplit[0]);
        var dispatchSubCategory = DispatchSubCategory.fromString(containingCategoriesSplit[1].trim());

        return new DispatchHappening(happening.getId(), happening.getTimestamp(), happening.getDescription(), nation,
                dispatchId, dispatchName, dispatchCategory, dispatchSubCategory);
    }

}
