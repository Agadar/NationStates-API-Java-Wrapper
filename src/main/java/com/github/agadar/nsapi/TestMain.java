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
        Nation nation = ns.nation("agadar", NationShard.NAME, NationShard.ZOMBIE);
        System.out.println(nation.Name);
        
        System.out.println(nation.ZombieInfo.Action + ", " + nation.ZombieInfo.Dead + ", "
        + nation.ZombieInfo.Survivors + ", " + nation.ZombieInfo.Zombies + ", ");
        
//        for (Dispatch d : nation.Factbooks)
//        {
//            System.out.println(d.Author + ", " + d.Category + ", " + d.SubCategory 
//                + ", " + d.Title + ", " + d.CreatedOn + ", " + d.Id + ", " +
//                d.LastEditedOn + ", " + d.Score + ", " + d.Views);
//        }
        //NationStatesAPI.region("the western isles");
        //NationStatesAPI.world(WorldShard.nations, WorldShard.poll);
        //NationStatesAPI.worldAssembly(Council.General_Assembly, WorldAssemblyShard.happenings);
    }
    
}
