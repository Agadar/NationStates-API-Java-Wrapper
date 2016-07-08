package com.github.agadar.nsapi.adapter;

import com.github.agadar.nsapi.enums.Council;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts an integer to a Council.java enum value.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public final class IntToCouncilAdapter extends XmlAdapter<Integer, Council>
{
    @Override
    public Integer marshal(Council bt) throws Exception
    {
        // We never actually marshal something, so no need to implement this method.
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public Council unmarshal(Integer vt) throws Exception
    {
        return Council.getByNumber(vt);
    }

}
