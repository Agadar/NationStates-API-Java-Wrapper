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
        if (happening.description == null) {
            return false;
        }
        return happening.description.contains("page=dispatch");
    }

    @Override
    public DispatchHappening toSpecializedType(Happening happening) {
        final String[] splitOnAt = happening.description.split("@@");
        final String nation = splitOnAt[1];
        final String[] splitOnParenthesis = splitOnAt[2].split("\"");

        // Dispatch id.
        String containingId = splitOnParenthesis[2];
        containingId = containingId.substring(17, containingId.length());
        final long dispatchId = Long.parseLong(containingId);

        // Dispatch name.
        final String containingName = splitOnParenthesis[3];
        final String dispatchName = containingName.substring(1, containingName.length() - 4);

        // Dispatch categories.
        String containingCategories = splitOnParenthesis[4];
        containingCategories = containingCategories.substring(2, containingCategories.length() - 2);
        final String[] containingCategoriesSplit = containingCategories.split(":");
        final DispatchCategory dispatchCategory = DispatchCategory.fromString(containingCategoriesSplit[0]);
        final DispatchSubCategory dispatchSubCategory = DispatchSubCategory
                .fromString(containingCategoriesSplit[1].trim());

        return new DispatchHappening(happening.id, happening.timestamp, happening.description, nation, dispatchId,
                dispatchName, dispatchCategory, dispatchSubCategory);
    }

}
