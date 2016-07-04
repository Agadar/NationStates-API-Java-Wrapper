package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.region.Officer;
import com.github.agadar.nsapi.domain.region.Region;
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
        Region r = ns.region("the western isles", RegionShard.Officers);
        
        for (Officer f : r.Officers)
        {
            System.out.print(f.NationName);
            
            for (String s : f.Authorities)
            {
                System.out.print(" " + s);
            }
            
            System.out.println();
        }
        
//        for (Embassy e : r.Embassies)
//        {
//            System.out.println(e.RegionName + " " + e.Status);
//        }
        
    }
    
}
