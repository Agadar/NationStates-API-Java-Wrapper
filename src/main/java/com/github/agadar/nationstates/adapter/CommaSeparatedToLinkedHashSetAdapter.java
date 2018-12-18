package com.github.agadar.nationstates.adapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a comma-separated String to a LinkedHashSet of Strings and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class CommaSeparatedToLinkedHashSetAdapter extends XmlAdapter<String, Collection<String>> {

    @Override
    public String marshal(Collection<String> bt) {
        return String.join(",", bt);
    }

    @Override
    public Collection<String> unmarshal(String vt) {
        return new LinkedHashSet<>(Arrays.asList(vt.split(",")));
    }

}
