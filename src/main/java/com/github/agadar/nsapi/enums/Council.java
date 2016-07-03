package com.github.agadar.nsapi.enums;

/**
 * The different councils within the World Assembly.
 * 
 * @author Martin
 */
public enum Council 
{
    GENERAL_ASSEMBLY(1),
    SECURITY_COUNCIL(2);
    
    private final int value;
    
    private Council(int value) 
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
}
