package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.EmbassiesRmbPermissions;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String to an EmbassiesRMBPermissions, and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class StringToEmbassiesRmbPermissionsAdapter extends XmlAdapter<String, EmbassiesRmbPermissions> {

    @Override
    public EmbassiesRmbPermissions unmarshal(String vt) throws Exception {
        return EmbassiesRmbPermissions.fromString(vt);
    }

    @Override
    public String marshal(EmbassiesRmbPermissions bt) throws Exception {
        return bt.toString();
    }

}
