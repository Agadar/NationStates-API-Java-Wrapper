package com.github.agadar.nationstates.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.happeningspecializer.ChangeHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.DispatchHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.EjectedHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.EmbassyHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.IHappeningSpecializer;
import com.github.agadar.nationstates.happeningspecializer.LawHappeningSpecializer;

/**
 * Takes a collection of happenings and returns a sorted set containing those
 * same happenings, specialized to corresponding subclasses where applicable.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class HappeningSpecializationHelper implements IHappeningSpecializer<Happening> {

    private static List<IHappeningSpecializer<? extends Happening>> happeningSpecializers;
    private static HappeningSpecializationHelper instance;

    static {
        happeningSpecializers = new ArrayList<>();
        happeningSpecializers.add(new ChangeHappeningSpecializer());
        happeningSpecializers.add(new DispatchHappeningSpecializer());
        happeningSpecializers.add(new EjectedHappeningSpecializer());
        happeningSpecializers.add(new EmbassyHappeningSpecializer());
        happeningSpecializers.add(new LawHappeningSpecializer());
        instance = new HappeningSpecializationHelper();
    }

    /**
     * Takes a collection of happenings and returns a sorted set containing those
     * same happenings, specialized to corresponding subclasses where applicable.
     * 
     * @param happenings
     * @return
     */
    public static SortedSet<Happening> specializeHappenings(Collection<Happening> happenings) {
        if (happenings == null) {
            return null;
        }
        return new TreeSet<Happening>(happenings.stream()
                .map(happening -> happeningSpecializers.stream()
                        .filter(specializer -> specializer.isOfSpecializedType(happening)).findAny().orElse(instance)
                        .toSpecializedType(happening))
                .collect(Collectors.toSet()));
    }

    @Override
    public boolean isOfSpecializedType(Happening happening) {
        throw new UnsupportedOperationException("This method should not have been called");
    }

    @Override
    public Happening toSpecializedType(Happening happening) {
        return happening;
    }

}
