package com.github.agadar.nationstates.xmlconverter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.exception.NationStatesAPIException;

import static org.junit.Assert.*;

/**
 * @author Agadar (https://github.com/Agadar/)
 */
public class XmlConverterImplTest {

    private final String xmlString = "<EVENT id=\"183475995\"><TIMESTAMP>1514247453</TIMESTAMP><TEXT>@@einigkeitreich@@ relocated from %%the_pacific%% to %%the_rejected_realms%%.</TEXT></EVENT>";
    private final long expectedId = 183475995;
    private final String expectedText = "@@einigkeitreich@@ relocated from %%the_pacific%% to %%the_rejected_realms%%.";
    private final long expectedTimestamp = 1514247453;

    private XmlConverterImpl xmlConverter;

    @Before
    public void setUp() throws NationStatesAPIException {
        xmlConverter = new XmlConverterImpl();
        xmlConverter.registerTypes(Happening.class);
    }

    @After
    public void tearDown() {
        xmlConverter = null;
    }

    /**
     * Test of xmlToObject method, of class XmlConverter.
     *
     * @throws java.io.UnsupportedEncodingException
     * @throws NationStatesAPIException
     */
    @Test
    public void testXmlToObject() throws UnsupportedEncodingException, NationStatesAPIException {
        System.out.println("xmlToObject");

        // Arrange
        final InputStream input = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8.name()));

        // Act
        final Happening object = xmlConverter.xmlToObject(input, Happening.class);

        // Assert
        assertNotNull(object);
        assertEquals(object.getId(), expectedId);
        assertEquals(object.getDescription(), expectedText);
        assertEquals(object.getTimestamp(), expectedTimestamp);
    }

    /**
     * Test of objectToXml method, of class XmlConverter.
     *
     * @throws java.io.UnsupportedEncodingException
     * @throws NationStatesAPIException
     */
    @Test
    public void testObjectToXml() throws UnsupportedEncodingException, NationStatesAPIException {
        System.out.println("objectToXml");

        // Arrange
        final Happening happening = new Happening();
        happening.setId(expectedId);
        happening.setDescription(expectedText);
        happening.setTimestamp(expectedTimestamp);

        // Act
        final ByteArrayOutputStream stream = xmlConverter.objectToXml(happening);
        final String xml = new String(stream.toByteArray(), StandardCharsets.UTF_8.name());

        // Assert
        assertEquals(xml, xmlString);
    }

}
