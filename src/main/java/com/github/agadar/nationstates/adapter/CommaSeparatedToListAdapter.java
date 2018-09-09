package com.github.agadar.nationstates.adapter;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a comma-separated String to a List of Strings and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class CommaSeparatedToListAdapter extends XmlAdapter<String, List<String>> {

    @Override
    public String marshal(List<String> bt) {
        return String.join(",", bt);
    }

    @Override
    public List<String> unmarshal(String vt) {
        return Arrays.asList(vt.split(","));
    }
}
