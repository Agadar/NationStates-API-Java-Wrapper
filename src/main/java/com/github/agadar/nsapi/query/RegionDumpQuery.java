package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.query.blueprint.DailyDumpQuery;
import com.github.agadar.nsapi.domain.DailyDumpRegions;
import com.github.agadar.nsapi.enums.DailyDumpMode;

/**
 * Query for retrieving daily region dumps from NationStates.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionDumpQuery extends DailyDumpQuery<RegionDumpQuery, DailyDumpRegions>
{
    /**
     * Constructor, accepting a mode.
     * 
     * @param mode the daily dump mode to use
     */
    public RegionDumpQuery(DailyDumpMode mode)
    {
        super(mode);
    }
    
    @Override
    protected String getFileName()
    {
        return "regions.xml.gz";
    }
}
