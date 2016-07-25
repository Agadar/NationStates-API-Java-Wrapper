package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Regional authority codes to authority names.
 * 
 * @author Agadar (https://github.com/Agadar/)
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
    
    /** Reverse mapping. */
    private final static Map<String, Authority> reverse = new HashMap<>();
    
    /** Static init for filling the reverse mapping. */
    static
    {
        for (Authority auth : values())
        {
            reverse.put(auth.toString(), auth);
        }
    }
    
    /** The underlying authority name */
    private final String AuthName;
    
    /**
     * Returns the Authority holding a specific authName.
     * 
     * @param authName string to find the corresponding Authority of
     * @return the corresponding Authority
     */
    public static Authority getByAuthName(String authName)
    {
        return reverse.get(authName);
    }
    
    /**
     * Instantiate a new entry with the given authority name.
     * 
     * @param authName the name of the underlying authority
     */
    private Authority(String authName) 
    {
        this.AuthName = authName;
    }

    /**
     * Return the underlying authority name.
     * 
     * @return the underlying authority name
     */
    @Override
    public String toString() 
    {
        return AuthName;
    }
}
