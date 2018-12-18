package com.github.agadar.nationstates.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.happeningspecializer.ChangeHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.DispatchHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.EjectedHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.EmbassyHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.HappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.LawHappeningSpecializer;

/**
 * Takes a collection of happenings and returns a sorted set containing those
 * same happenings, specialized to corresponding subclasses where applicable.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public final class HappeningSpecializationHelper {

    private static Collection<HappeningSpecializer<? extends Happening>> happeningSpecializers;

    static {
        happeningSpecializers = new ArrayList<>();
        happeningSpecializers.add(new ChangeHappeningSpecializer());
        happeningSpecializers.add(new DispatchHappeningSpecializer());
        happeningSpecializers.add(new EjectedHappeningSpecializer());
        happeningSpecializers.add(new EmbassyHappeningSpecializer());
        happeningSpecializers.add(new LawHappeningSpecializer());
    }

    /**
     * Takes a collection of happenings and returns a sorted set containing those
     * same happenings, specialized to corresponding subclasses where applicable.
     * 
     * @param happenings
     * @return
     */
    public static List<Happening> specializeHappenings(Collection<Happening> happenings) {
        return happenings.stream().map(happening -> specializeHappeningIfPossible(happening))
                .collect(Collectors.toList());

    }

    private static Happening specializeHappeningIfPossible(Happening happening) {
        return getCorrectSpecializer(happening)
                .map((specializer) -> (Happening) specializer.toSpecializedType(happening)).orElse(happening);
    }

    private static Optional<HappeningSpecializer<? extends Happening>> getCorrectSpecializer(Happening happening) {
        return happeningSpecializers.stream().filter((specializer) -> specializer.isOfSpecializedType(happening))
                .findAny();
    }

}
