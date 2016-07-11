package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class NationTest extends Nation
{
    public String fancy()
    {
        return "----------" + this.FullName + "----------";
    }
}
