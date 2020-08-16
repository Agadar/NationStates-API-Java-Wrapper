package com.github.agadar.nationstates.adapter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Converts a comma-separated String to a LinkedHashSet of Longs and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Slf4j
public class CsvStringToLongSetSetAdapter extends XmlAdapter<String, Collection<Long>> {

    @Override
    public String marshal(Collection<Long> bt) {
        return bt.stream().map(element -> element.toString()).collect(Collectors.joining(","));
    }

    @Override
    public Collection<Long> unmarshal(String vt) {
        return Stream.of(vt.split(",")).map(element -> {
            try {
                return Long.parseLong(element);
            } catch (NumberFormatException ex) {
                log.error("Failed to parse value '" + element + "' to Long", ex);
                return null;
            }
        }).filter(value -> value != null).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
