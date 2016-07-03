package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.Region;
import com.github.agadar.nsapi.enums.RegionShard;

/**
 *
 * @author Martin
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NationStatesAPIException
    {
        NationStatesAPI ns = new NationStatesAPI();
        Region r = ns.region("the north pacific", RegionShard.EmbassiesRMBPerms, RegionShard.Factbook);
        
        //System.out.println(r.EmbassiesRMBPerms + " " + r.Factbook);
        
//        for (Embassy e : r.Embassies)
//        {
//            System.out.println(e.RegionName + " " + e.Status);
//        }
        
    }
    
}
