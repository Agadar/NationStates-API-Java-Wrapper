package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.DispatchCat;
import com.github.agadar.nsapi.enums.DispatchSubCat;
import com.github.agadar.nsapi.enums.shard.WorldShard;
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
          
          World w = NSAPI.world(WorldShard.Dispatches).dispatchCategory(
                  DispatchCat.Factbook).dispatchSubcategory(
                          DispatchSubCat.Gameplay).dispatchAuthor("agadar").execute();
          
          System.out.println(w.Dispatches.size());
          System.out.println(w.Dispatches.get(0).Title);
    }
    
}
