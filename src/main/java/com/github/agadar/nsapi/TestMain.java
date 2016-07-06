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
    public static void main(String[] args) throws NationStatesAPIException
    {
        NationStatesAPI ns = new NationStatesAPI();
        RateLimiter rl = new RateLimiter(50, 30000);
        int counter = 0;
        
        while (true)
        {
            rl.Await();
            Nation n = ns.nation("agadar", NationShard.Founded);
            System.out.println(counter + " - " + n.Founded);
        }
    }
    
}
