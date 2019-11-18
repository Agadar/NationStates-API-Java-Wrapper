package com.github.agadar.nationstates.function;

import java.util.function.BiFunction;

/**
 * BiFunction reference that can throw an Exception. See also
 * {@link BiFunction}.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface CheckedBiFunction<T, U, R> {
    R apply(T t, U u) throws Exception;
}
