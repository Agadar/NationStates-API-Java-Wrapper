package com.github.agadar.nsapi;

/**
 * Custom exception for Agadar's NationStates API Java Wrapper.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public final class NationStatesAPIException extends RuntimeException
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
