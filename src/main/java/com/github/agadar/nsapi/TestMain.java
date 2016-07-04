package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.enums.RegionShard;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NationStatesAPIException
    {
        NationStatesAPI ns = new NationStatesAPI();
        Region r = ns.region("the western isles", RegionShard.Tags);
        
        System.out.println(r.Tags.get(2));
        
//        for (Embassy e : r.Embassies)
//        {
//            System.out.println(e.RegionName + " " + e.Status);
//        }
        
    }
    
}
