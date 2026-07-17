package com.github.agadar.nationstates.adapter;

import java.util.*;
import java.util.stream.Collectors;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.happeningspecializer.ChangeHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.EmbassyHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.HappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.LawHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.dispatch.DispatchHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.dispatch.DispatchHappeningWithIdSpecializer;
import com.github.agadar.nationstates.happeningspecializer.ejected.XEjectedAndBannedYFromZHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.ejected.XEjectedAndBannedYHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.ejected.XEjectedYFromZHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.ejected.XEjectedYHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.ejected.XWasEjectedAndBannedFromYByZHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.ejected.XWasEjectedFromYByZHappeningSpecializer;

import lombok.SneakyThrows;

/**
 * Takes a collection of happenings and returns a sorted set containing those
 * same happenings, specialized to corresponding subclasses where applicable.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public final class HappeningSpecializationHelper {

    private static final Collection<HappeningSpecializer<? extends Happening>> happeningSpecializers = new HashSet<>();

    static {
        registerSpecializer(new DispatchHappeningSpecializer());
        registerSpecializer(new DispatchHappeningWithIdSpecializer());

        registerSpecializer(new XEjectedAndBannedYFromZHappeningSpecializer());
        registerSpecializer(new XEjectedAndBannedYHappeningSpecializer());
        registerSpecializer(new XEjectedYFromZHappeningSpecializer());
        registerSpecializer(new XEjectedYHappeningSpecializer());
        registerSpecializer(new XWasEjectedAndBannedFromYByZHappeningSpecializer());
        registerSpecializer(new XWasEjectedFromYByZHappeningSpecializer());

        registerSpecializer(new EmbassyHappeningSpecializer());
        registerSpecializer(new LawHappeningSpecializer());
        registerSpecializer(new ChangeHappeningSpecializer());
    }

    /**
     * Takes a collection of happenings and returns a sorted set containing those
     * same happenings, specialized to corresponding subclasses where applicable.
     * 
     * @return The specialized happenings.
     */
    public static ArrayList<Happening> specializeHappenings(Collection<Happening> happenings) {
        return happenings.stream()
                .map(HappeningSpecializationHelper::specializeHappeningIfPossible)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Register a new specializer to this helper.
     */
    public static void registerSpecializer(HappeningSpecializer<? extends Happening> specializer) {
        happeningSpecializers.add(specializer);
    }

    private static Happening specializeHappeningIfPossible(Happening happening) {
        return getCorrectSpecializer(happening)
                .map((specializer) -> toSpecializedType(specializer, happening))
                .orElse(happening);
    }

    private static Optional<HappeningSpecializer<? extends Happening>> getCorrectSpecializer(Happening happening) {
        return happeningSpecializers.stream().filter((specializer) -> specializer.isOfSpecializedType(happening))
                .findAny();
    }

    @SneakyThrows
    private static Happening toSpecializedType(HappeningSpecializer<?> specializer, Happening happening) {
        return specializer.toSpecializedType(happening);
    }

}
