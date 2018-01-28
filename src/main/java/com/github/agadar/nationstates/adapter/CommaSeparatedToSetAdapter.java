package com.github.agadar.nationstates.adapter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a comma-separated String to a Set of Strings and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class CommaSeparatedToSetAdapter extends XmlAdapter<String, Set<String>> {

    @Override
    public String marshal(Set<String> bt) {
        return String.join(",", bt);
    }

    @Override
    public Set<String> unmarshal(String vt) {
        return new HashSet<>(Arrays.asList(vt.split(",")));
    }

}
