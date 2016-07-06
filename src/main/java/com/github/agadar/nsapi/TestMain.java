package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.shard.WorldShard;

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
        World w = ns.world(WorldShard.CensusDescriptions);
        System.out.println(w.Regions() == null);
        System.out.println(w.RegionsByTag() == null);
    }
    
}
