package com.github.agadar.nsapi;

/**
 * Custom exception for Agadar's NationStates API Java Wrapper.
 * 
 * @author Martin
 */
public class NationStatesAPIException extends Exception
{
    public NationStatesAPIException()
    {
        super();
    }

    public NationStatesAPIException(String message)
    {
        super(message);
    }

    public NationStatesAPIException(Throwable cause)
    {
        super(cause);
    }

    public NationStatesAPIException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
