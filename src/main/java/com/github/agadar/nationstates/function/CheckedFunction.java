package com.github.agadar.nationstates.function;

import java.util.function.Function;

/**
 * Function reference that can throw an Exception. See also {@link Function}.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}
