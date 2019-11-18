package com.github.agadar.nationstates.exception;

/**
 * Custom checked exception for Agadar's NationStates API Java Wrapper.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationStatesAPIException extends Exception {

    private static final long serialVersionUID = 1L;

    public NationStatesAPIException() {
        super();
    }

    public NationStatesAPIException(String message) {
        super(message);
    }

    public NationStatesAPIException(Throwable cause) {
        super(cause);
    }

    public NationStatesAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
