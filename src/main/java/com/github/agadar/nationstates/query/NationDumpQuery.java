package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.domain.DailyDumpNations;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;

/**
 * Query for retrieving daily nation dumps from NationStates.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationDumpQuery extends DailyDumpQuery<NationDumpQuery, DailyDumpNations> {

    /**
     * Constructor, accepting a mode.
     *
     * @param mode the daily dump mode to use
     */
    public NationDumpQuery(DailyDumpMode mode) {
        super(mode);
    }

    @Override
    protected String getFileName() {
        return "nations.xml.gz";
    }
}
