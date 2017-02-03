package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.Council;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts an integer to a Council.java enum value and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class IntToCouncilAdapter extends XmlAdapter<Integer, Council> {

    @Override
    public Integer marshal(Council bt) {
        return bt.toInt();
    }

    @Override
    public Council unmarshal(Integer vt) {
        return Council.fromInt(vt);
    }

}
