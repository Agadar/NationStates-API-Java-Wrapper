package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.enums.shard.NationShard;

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
        Nation n = ns.nation("agadar", NationShard.Banner);
        System.out.println(n.BannerAsURL());
    }
    
}
