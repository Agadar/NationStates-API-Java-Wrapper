package com.github.agadar.nationstates;

/**
 * Custom unchecked exception for Agadar's NationStates API Java Wrapper.
 * Commonly used to wrap checked exceptions so that users of this library don't
 * have to catch all sorts of checked exceptions.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class NationStatesAPIException extends RuntimeException {

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
