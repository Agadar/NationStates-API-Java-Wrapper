package com.github.agadar.nationstates.function;

import java.util.function.Supplier;

/**
 * Supplier reference that can throw an Exception. See also {@link Supplier}.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface CheckedSupplier<R> {
    R get() throws Exception;
}
