package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.RegionalMessageStatus;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts an integer to a RegionalMessageStatus enum value and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class IntToRegionalMessageStatusAdapter extends XmlAdapter<Integer, RegionalMessageStatus> {

    @Override
    public RegionalMessageStatus unmarshal(Integer v) throws Exception {
        return RegionalMessageStatus.fromInt(v);
    }

    @Override
    public Integer marshal(RegionalMessageStatus v) throws Exception {
        return v.toInt();
    }

}
