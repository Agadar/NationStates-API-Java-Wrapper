package com.github.agadar.nationstates.adapter;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a colon-separated String to a List of Strings and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class ColonSeparatedToListAdapter extends XmlAdapter<String, List<String>> {

    @Override
    public String marshal(List<String> bt) {
        return String.join(":", bt.toArray(new String[bt.size()]));
    }

    @Override
    public List<String> unmarshal(String vt) {
        return Arrays.<String>asList(vt.split(":"));
    }

}
