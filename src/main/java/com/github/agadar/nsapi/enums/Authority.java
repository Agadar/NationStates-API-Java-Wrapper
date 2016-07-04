package com.github.agadar.nsapi.enums;

/**
 * Regional authority codes to authority names.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public enum Authority 
{
    /** Executive */
    X("Executive"),
    /** World Assembly */
    W("World Assembly"),
    /** Appearance */
    A("Appearance"),
    /** Border Control */
    B("Border Control"),
    /** Communications */
    C("Communications"),
    /** Embassies */
    E("Embassies"),
    /** Polls */
    P("Polls");
        
    /** The underlying authority name */
    private final String authName;
    
    /**
     * Instantiate a new entry with the given authority name.
     * 
     * @param authName the name of the underlying authority
     */
    private Authority(String authName) 
    {
        this.authName = authName;
    }

    /**
     * Return the underlying authority name.
     * 
     * @return the underlying authority name
     */
    @Override
    public String toString() 
    {
        return authName;
    }
}
