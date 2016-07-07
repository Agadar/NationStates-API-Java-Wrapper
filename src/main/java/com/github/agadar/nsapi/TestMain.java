package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.shared.CensusScoreHistory;
import com.github.agadar.nsapi.enums.shard.NationShard;
import com.github.agadar.nsapi.query.NSQuery;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NationStatesAPIException, InterruptedException
    {
        //.censusModes(CensusMode.History)
        
        Nation n = NSQuery.nation("testlandia").shards(NationShard.Census, NationShard.Name)
                .censusIds(44).censusHistoryFrom(1457856000)
                .execute();
        
        CensusScoreHistory one = n.Census.get(0).History.get(0);
        System.out.println(one.Timestamp + " " + one.Score);
    }
    
}
