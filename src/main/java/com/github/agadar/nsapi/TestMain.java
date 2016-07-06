package com.github.agadar.nsapi;

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
            System.out.println("Called " + ++counter + " times!");
        }
    }
    
}
