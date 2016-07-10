package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.DailyDumpNations;
import com.github.agadar.nsapi.domain.DailyDumpRegions;
import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.domain.shared.CensusScoreHistory;
import com.github.agadar.nsapi.domain.wa.DelLogEntry;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.Council;
import com.github.agadar.nsapi.enums.DailyDumpMode;
import com.github.agadar.nsapi.enums.HapFilter;
import com.github.agadar.nsapi.enums.shard.NationShard;
import com.github.agadar.nsapi.enums.shard.RegionShard;
import com.github.agadar.nsapi.enums.shard.WAShard;
import com.github.agadar.nsapi.enums.shard.WorldShard;
import com.github.agadar.nsapi.query.APIQuery;
import com.github.agadar.nsapi.query.DailyDumpQuery;
import com.github.agadar.nsapi.query.TelegramQuery;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NationStatesAPIException, InterruptedException, NoSuchAlgorithmException
    {
//        DailyDumpQuery dd = new DailyDumpQuery();
//        //dd.download();
//        DailyDumpRegions n = dd.readRemote();
//        
//        System.out.println(n.Regions.size());
//        System.out.println(n.Regions.get(0).Name);
        
          NSAPI.setUserAgent("Agadar's Java Wrapper <test>");
          
          DailyDumpRegions ddr = NSAPI.regiondump(DailyDumpMode.DownloadAndRead)
                  .downloadDir("C:\\Users\\Martin\\Documents")
                  .readFromDir("C:\\Users\\Martin\\Documents").execute();
          System.out.println(ddr.Regions.size());
          
          DailyDumpNations ndr = NSAPI.nationdump(DailyDumpMode.DownloadAndRead)
                  .downloadDir("C:\\Users\\Martin\\Documents")
                  .readFromDir("C:\\Users\\Martin\\Documents").execute();
          System.out.println(ndr.Nations.size());
    }
    
}
