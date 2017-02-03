package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.WorldAssemblyStatus;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a string to a WorldAssemblyStatus, and vice versa.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public class StringToWorldAssemblyStatusAdapter extends XmlAdapter<String, WorldAssemblyStatus> {

    @Override
    public WorldAssemblyStatus unmarshal(String vt) throws Exception {
        return WorldAssemblyStatus.fromString(vt);
    }

    @Override
    public String marshal(WorldAssemblyStatus bt) throws Exception {
        return bt.toString();
    }
    
}
