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
     * @param happening
     * @return True if it is, otherwise false.
     */
    public boolean isOfSpecializedType(Happening happening);

    /**
     * Converts a generic Happening to the specialized type this specializer is
     * responsible for.
     * 
     * @param happening
     * @return The specialized type.
     * @throws NationStatesAPIException If the happening is not of the specialized
     *                                  type.
     */
    public T toSpecializedType(Happening happening) throws NationStatesAPIException;
}
