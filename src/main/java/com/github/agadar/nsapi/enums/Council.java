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
    
    /** The underlying council number */
    private final int councilNumber;
    
    /**
     * Instantiate a new entry with the given underlying council number.
     * 
     * @param councilNumber the underlying council number
     */
    private Council(int councilNumber) 
    {
        this.councilNumber = councilNumber;
    }

    /**
     * Return the underlying council number.
     * 
     * @return the underlying council number
     */
    public int getCouncilNumber() 
    {
        return councilNumber;
    }
}
