package com.github.agadar.nsapi.enums;

/**
 * Regional authority codes to authority names.
 * 
 * @author Martin
 */
public enum Authority 
{
    X("Executive"),
    W("World Assembly"),
    A("Appearance"),
    B("Border Control"),
    C("Communications"),
    E("Embassies"),
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
