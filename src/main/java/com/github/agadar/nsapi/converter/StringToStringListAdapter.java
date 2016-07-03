package com.github.agadar.nsapi.converter;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Creates a String List from a String and vice versa. Elements are expected to be split by commas.
 * 
 * @author Martin
 */
public class StringToStringListAdapter extends XmlAdapter<String, List<String>>
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
