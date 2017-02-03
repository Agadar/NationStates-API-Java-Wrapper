package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.Authority;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String containing authority codes to a List of Authority values,
 * and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class CharsToAuthoritiesAdapter extends XmlAdapter<String, List<Authority>> {

    @Override
    public List<Authority> unmarshal(String vt) throws Exception {
        final List<Authority> authorities = new ArrayList<>();

        for (char code : vt.toCharArray()) {
            authorities.add(Authority.fromChar(code));
        }
        return authorities;
    }

    @Override
    public String marshal(List<Authority> bt) throws Exception {
        String codes = "";

        for (Authority authority : bt) {
            codes += authority.toChar();
        }
        return codes;
    }
}
