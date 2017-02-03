package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.EmbassiesRMBPermissions;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String to an EmbassiesRMBPermissions, and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class StringToEmbassiesRMBPermissionsAdapter extends XmlAdapter<String, EmbassiesRMBPermissions> {

    @Override
    public EmbassiesRMBPermissions unmarshal(String vt) throws Exception {
        return EmbassiesRMBPermissions.fromString(vt);
    }

    @Override
    public String marshal(EmbassiesRMBPermissions bt) throws Exception {
        return bt.toString();
    }

}
