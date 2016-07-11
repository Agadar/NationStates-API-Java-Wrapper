package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.query.blueprint.DailyDumpQuery;
import com.github.agadar.nsapi.domain.DailyDumpNations;
import com.github.agadar.nsapi.enums.DailyDumpMode;

/**
 * Query for retrieving daily nation dumps from NationStates.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationDumpQuery extends DailyDumpQuery<NationDumpQuery, DailyDumpNations>
{
    /**
     * Constructor, accepting a mode.
     * 
     * @param mode the daily dump mode to use
     */
    public NationDumpQuery(DailyDumpMode mode)
    {
        super(mode);
    }
    
    @Override
    protected String getFileName()
    {
        return "nations.xml.gz";
    }
}
