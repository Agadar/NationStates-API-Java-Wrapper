package com.github.agadar.nationstates.xmlconverter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Converts XML streams to properly annotated objects, and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public interface IXmlConverter {

    /**
     * Adds the given classes to the JAXB context so that they can be parsed to
     * from retrieved XML responses and files. Classes that inherit any of the
     * classes in the domain-package don't need any xml-annotations. Classes
     * that do no inherit those classes, do need xml-annotations.
     *
     * @param types the classes to add to the JAXB context
     */
    public void registerTypes(Class<?>... types);

    /**
     * Uses JAXB to parse the supplied XML stream to an instance of the
     * specified type.
     *
     * @param <T> the type to parse to
     * @param xml the XML stream
     * @param toType the type to parse to
     * @return instance of the specified type
     */
    public <T> T xmlToObject(InputStream xml, Class<T> toType);

    /**
     * Uses JAXB to parse an object to an output stream.
     *
     * @param obj object to parse to output stream
     * @return an output stream
     */
    public ByteArrayOutputStream objectToXml(Object obj);
}
