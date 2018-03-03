package com.github.agadar.nationstates.happeningspecializer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import com.github.agadar.nationstates.domain.common.happening.EmbassyHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.EmbassyHappeningType;

/**
 * Specializes generic Happenings to EmbassyHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class EmbassyHappeningSpecializer implements HappeningSpecializer<EmbassyHappening> {

    private final Map<EmbassyHappeningType, String> texts = new HashMap<>();
    private final Map<EmbassyHappeningType, BiFunction<Happening, EmbassyHappeningType, EmbassyHappening>> functions = new HashMap<>();

    public EmbassyHappeningSpecializer() {
	texts.put(EmbassyHappeningType.NATION_ABORTED_CONSTRUCTION, "aborted construction of embassies between");
	texts.put(EmbassyHappeningType.AGREED_TO_CONSTRUCT, "agreed to construct embassies between");
	texts.put(EmbassyHappeningType.EMBASSY_ESTABLISHED, "Embassy established between");
	texts.put(EmbassyHappeningType.ORDERED_CLOSURE, "ordered the closure of embassies between");
	texts.put(EmbassyHappeningType.PROPOSED_CONSTRUCTION, "proposed constructing embassies between");
	texts.put(EmbassyHappeningType.REJECTED_REQUEST, "rejected a request from");
	texts.put(EmbassyHappeningType.WITHDREW_REQUEST, "withdrew a request for embassies between");
	texts.put(EmbassyHappeningType.CANCELLED_CLOSURE, "cancelled the closure of embassies between");
	texts.put(EmbassyHappeningType.EMBASSY_CANCELLED, "Embassy cancelled between");
	texts.put(EmbassyHappeningType.CONSTRUCTION_ABORTED, "Construction of embassies aborted between");

	functions.put(EmbassyHappeningType.NATION_ABORTED_CONSTRUCTION, this::happeningWithNation);
	functions.put(EmbassyHappeningType.AGREED_TO_CONSTRUCT, this::happeningWithNation);
	functions.put(EmbassyHappeningType.ORDERED_CLOSURE, this::happeningWithNation);
	functions.put(EmbassyHappeningType.PROPOSED_CONSTRUCTION, this::happeningWithNation);
	functions.put(EmbassyHappeningType.REJECTED_REQUEST, this::happeningWithNation);
	functions.put(EmbassyHappeningType.WITHDREW_REQUEST, this::happeningWithNation);
	functions.put(EmbassyHappeningType.CANCELLED_CLOSURE, this::happeningWithNation);
	functions.put(EmbassyHappeningType.EMBASSY_CANCELLED, this::happeningWithoutNation);
	functions.put(EmbassyHappeningType.EMBASSY_ESTABLISHED, this::happeningWithoutNation);
	functions.put(EmbassyHappeningType.CONSTRUCTION_ABORTED, this::happeningWithoutNation);
    }

    @Override
    public boolean isOfSpecializedType(Happening happening) {
	if (happening.description == null) {
	    return false;
	}
	return texts.values().stream().anyMatch((text) -> happening.description.contains(text));
    }

    @Override
    public EmbassyHappening toSpecializedType(Happening happening) {
	return texts.entrySet().stream().filter((entry) -> happening.description.contains(entry.getValue()))
		.map(entry -> functions.get(entry.getKey()).apply(happening, entry.getKey())).findAny().orElse(null);
    }

    private EmbassyHappening happeningWithNation(Happening happening, EmbassyHappeningType embassyHappeningType) {
	final String[] splitOnAt = happening.description.split("@@");
	final String nation = splitOnAt[1];
	final String[] splitOnPercent = splitOnAt[2].split("%%");
	final String region1 = splitOnPercent[1];
	final String region2 = splitOnPercent[3];
	return new EmbassyHappening(happening.id, happening.timestamp, happening.description, nation, region1, region2,
		embassyHappeningType);
    }

    private EmbassyHappening happeningWithoutNation(Happening happening, EmbassyHappeningType embassyHappeningType) {
	final String[] splitOnPercent = happening.description.split("%%");
	final String region1 = splitOnPercent[1];
	final String region2 = splitOnPercent[3];
	return new EmbassyHappening(happening.id, happening.timestamp, happening.description, null, region1, region2,
		embassyHappeningType);
    }

}
