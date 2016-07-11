package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.DailyDumpRegions;
import com.github.agadar.nsapi.domain.region.Region;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class DailyDumpRegionsChild extends DailyDumpRegions
{
    public Region atIndex(int index)
    {
        return this.Regions.get(index);
    }
}
