package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.exception.NationStatesAPIException;

/**
 * Responsible for converting generic Happenings to a specialized type.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <T> The specialized Happening type this specializer can handle.
 */
public interface HappeningSpecializer<T extends Happening> {

    /**
     * Checks whether the supplied happening is of the type this specializer can
     * handle.
     * 
     * @return True if it is, otherwise false.
     */
    boolean isOfSpecializedType(Happening happening);

    /**
     * Converts a generic Happening to the specialized type this specializer is
     * responsible for.
     * 
     * @return The specialized type.
     * @throws NationStatesAPIException If the happening is not of the specialized
     *                                  type.
     */
    T toSpecializedType(Happening happening) throws NationStatesAPIException;
}
