package com.github.agadar.nationstates.xmlconverter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.github.agadar.nationstates.exception.NationStatesAPIException;

import lombok.extern.slf4j.Slf4j;

/**
 * Converts XML streams to properly annotated objects, and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Slf4j
public class XmlConverterImpl implements XmlConverter {

    /**
     * The JAXBContext for this API.
     */
    private JAXBContext jaxbContext;

    /**
     * The classes for the JAXBContext.
     */
    private final Collection<Class<?>> JAXB_CONTEXT_CLASSES = new HashSet<>();

    /**
     * Adds the given classes to the JAXB context so that they can be parsed to from
     * retrieved XML responses and files. Classes that inherit any of the classes in
     * the domain-package don't need any xml-annotations. Classes that do no inherit
     * those classes, do need xml-annotations.
     *
     * @param types the classes to add to the JAXB context
     * @throws NationStatesAPIException
     */
    @Override
    public final void registerTypes(Class<?>... types) throws NationStatesAPIException {
        // Place new types in jaxbContextClasses.
        JAXB_CONTEXT_CLASSES.addAll(Arrays.asList(types));
        final int numberOfClasses = JAXB_CONTEXT_CLASSES.size();

        // Use jaxbContextClasses to reinitialize the JAXB context.
        try {
            jaxbContext = JAXBContext.newInstance(JAXB_CONTEXT_CLASSES.toArray(new Class[numberOfClasses]));
        } catch (JAXBException ex) {
            log.error("An error occured while (re)initialising the JAXB context", ex);
            throw new NationStatesAPIException(ex);
        }
    }

    /**
     * Uses JAXB to parse the supplied XML stream to an instance of the specified
     * type.
     *
     * @param <T>    the type to parse to
     * @param xml    the XML stream
     * @param toType the type to parse to
     * @return instance of the specified type
     * @throws NationStatesAPIException
     */
    @Override
    public final <T> T xmlToObject(InputStream xml, Class<T> toType) throws NationStatesAPIException {
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StreamSource xmlstream = new StreamSource(xml);
            JAXBElement<T> je1 = unmarshaller.unmarshal(xmlstream, toType);
            return je1.getValue();
        } catch (JAXBException ex) {
            log.error("An error occured while parsing a XML stream to an object", ex);
            throw new NationStatesAPIException(ex);
        }
    }

    /**
     * Uses JAXB to parse an object to an output stream.
     *
     * @param obj object to parse to output stream
     * @return an output stream
     * @throws NationStatesAPIException
     */
    @Override
    public final ByteArrayOutputStream objectToXml(Object obj) throws NationStatesAPIException {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            marshaller.marshal(obj, stream);
            return stream;
        } catch (JAXBException ex) {
            log.error("An error occured while parsing an object to a XML stream", ex);
            throw new NationStatesAPIException(ex);
        }
    }
}
