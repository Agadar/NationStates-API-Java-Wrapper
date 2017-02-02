package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.EmbassyStatus;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String to an EmbassyStatus, and vice versa.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public class StringToEmbassyAdapter extends XmlAdapter<String, EmbassyStatus> {

    @Override
    public EmbassyStatus unmarshal(String vt) {
        return EmbassyStatus.getByString(vt);
    }

    @Override
    public String marshal(EmbassyStatus bt) {
        return bt.toString();
    }
}
