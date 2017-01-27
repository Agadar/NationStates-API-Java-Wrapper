package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.Authority;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String containing authority codes to a List of authority names,
 * and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class CharsToAuthNamesAdapter extends XmlAdapter<String, List<String>> {

    @Override
    public String marshal(List<String> bt) throws Exception {
        String codes = "";

        for (String s : bt) {
            codes += Authority.getByAuthName(s);
        }

        return codes;
    }

    @Override
    public List<String> unmarshal(String vt) throws Exception {
        final List<String> names = new ArrayList<>();

        for (String letter : vt.split("")) {
            final String name = Authority.valueOf(letter).toString();
            names.add(name);
        }

        return names;
    }
}
