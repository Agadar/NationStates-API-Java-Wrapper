package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.enums.shard.NationShard;
import java.util.ArrayList;
import java.util.List;

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
        NationStatesAPI ns = new NationStatesAPI();
        List<NationShard> shards = new ArrayList<>();
        shards.add(NationShard.Name);
        shards.add(NationShard.Census);
        Nation n = ns.nation("agadar", shards, null);
        System.out.println(n);
    }
    
}
