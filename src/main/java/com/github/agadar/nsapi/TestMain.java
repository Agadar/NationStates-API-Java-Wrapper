package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.domain.shared.CensusScoreHistory;
import com.github.agadar.nsapi.domain.wa.DelLogEntry;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.Council;
import com.github.agadar.nsapi.enums.shard.NationShard;
import com.github.agadar.nsapi.enums.shard.RegionShard;
import com.github.agadar.nsapi.enums.shard.WAShard;
import com.github.agadar.nsapi.enums.shard.WorldShard;
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
        
//        Nation n = NSQuery.nation("testlandia").shards(NationShard.Census, NationShard.Name)
//                .censusIds(22, 44).censusHistoryFrom(1457856000)
//                .execute();
        
//        Region n = NSQuery.region("the_western_isles").shards(RegionShard.Census, RegionShard.Name)
//                .censusIds(3).censusHistoryFrom(1457856000)
//                .execute();
        
//        World n = NSQuery.world(WorldShard.Census, WorldShard.NumberOfNations)
//                .censusIds(3).censusHistoryFrom(1457856000).execute();
        
        WorldAssembly a = NSQuery.wa(Council.GENERAL_ASSEMBLY).shards(WAShard.DelegateLog).execute();

        System.out.println(a.VoteTrackFor().get(3));
        System.out.println(a.VoteTrackAgainst().get(3));
        
        
        //DelLogEntry d = a.DelegateLog.get(0);
        //System.out.println(d.Timestamp + " " + d.DelegateName + " " + d.Votes + " " + d.Action);
        
    }
    
}
