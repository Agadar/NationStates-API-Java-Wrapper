package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.Nation;
import com.github.agadar.nsapi.enums.NationShard;

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
        Nation nation = ns.nation("Agadar", NationShard.ADMIRABLE, NationShard.ANIMAL);
        System.out.println(nation.ADMIRABLE);
        System.out.println(nation.ANIMAL);
        System.out.println(nation.DISPATCHES);
        //NationStatesAPI.region("the western isles");
        //NationStatesAPI.world(WorldShard.nations, WorldShard.poll);
        //NationStatesAPI.worldAssembly(Council.General_Assembly, WorldAssemblyShard.happenings);
    }
    
}
