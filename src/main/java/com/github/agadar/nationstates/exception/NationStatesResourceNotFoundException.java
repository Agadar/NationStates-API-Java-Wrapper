package com.github.agadar.nationstates.exception;

/**
 * Thrown when a NationStates resource (e.g. a nation or a region) was not
 * found. Specifically, this is thrown when the NationStates API returns HTTP
 * error 404.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class NationStatesResourceNotFoundException extends NationStatesAPIException {

    private static final long serialVersionUID = 1L;

    public NationStatesResourceNotFoundException() {
        super("Resource not found");
    }
}
