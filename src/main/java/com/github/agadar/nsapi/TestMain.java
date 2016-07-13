package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;

/**
 *
 * @author marti
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        NSAPI.setUserAgent("Agadar's Test Script (https://github.com/agadar)");
        Nation n = NSAPI.nation("agadar").execute();
        System.out.println(n.FullName);
    }
    
}
