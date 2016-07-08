package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.Authority;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String containing authority codes to a List of authority names.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class CharsToAuthNamesAdapter extends XmlAdapter<String, List<String>>
{

    @Override
    public String marshal(List<String> bt) throws Exception
    {
        // We never actually marshal something, so no need to implement this method.
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public List<String> unmarshal(String vt) throws Exception
    {
        List<String> names = new ArrayList<>();
        
        for (String letter : vt.split(""))
        {
            String name = Authority.valueOf(letter).toString();
            names.add(name);
        }
        
        return names;
    }
}
