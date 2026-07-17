package com.github.agadar.nationstates.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Converts a comma-separated String to a List of Strings and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class CsvStringToStringListAdapter extends XmlAdapter<String, ArrayList<String>> {

    @Override
    public String marshal(ArrayList<String> bt) {
        return String.join(",", bt);
    }

    @Override
    public ArrayList<String> unmarshal(String vt) {
        return new ArrayList<>(Arrays.asList(vt.split(",")));
    }
}
