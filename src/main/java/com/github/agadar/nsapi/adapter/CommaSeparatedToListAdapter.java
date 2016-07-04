package com.github.agadar.nsapi.adapter;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a comma-separated String to a List of Strings.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class CommaSeparatedToListAdapter extends XmlAdapter<String, List<String>>
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
        return Arrays.<String>asList(vt.split(","));
    }

}
