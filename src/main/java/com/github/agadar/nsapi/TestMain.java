package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.enums.Council;
import com.github.agadar.nsapi.enums.shard.WorldAssemblyShard;

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
        WorldAssembly wa = ns.worldAssembly(Council.SECURITY_COUNCIL, WorldAssemblyShard.LastResolutionResult);
        System.out.println(wa.LastResolutionResult);
        
        //System.out.println(wa.LastResolutionResult);
        
//        for (Embassy e : r.Embassies)
//        {
//            System.out.println(e.RegionName + " " + e.Status);
//        }
        
    }
    
}
