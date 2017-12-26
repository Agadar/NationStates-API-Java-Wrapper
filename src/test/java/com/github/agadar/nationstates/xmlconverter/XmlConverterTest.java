package com.github.agadar.nationstates.xmlconverter;

import com.github.agadar.nationstates.domain.common.Happening;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Agadar (https://github.com/Agadar/)
 */
public class XmlConverterTest {

    private final String xmlString = "<EVENT id=\"183475995\"><TIMESTAMP>1514247453</TIMESTAMP><TEXT>@@einigkeitreich@@ relocated from %%the_pacific%% to %%the_rejected_realms%%.</TEXT></EVENT>";
    private final long expectedId = 183475995;
    private final String expectedText = "@@einigkeitreich@@ relocated from %%the_pacific%% to %%the_rejected_realms%%.";
    private final long expectedTimestamp = 1514247453;

    private XmlConverter xmlConverter;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        xmlConverter = new XmlConverter();
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
     */
    @Test
    public void testXmlToObject() throws UnsupportedEncodingException {
        System.out.println("xmlToObject");

        // Arrange
        final InputStream input = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8.name()));

        // Act
        final Happening object = xmlConverter.xmlToObject(input, Happening.class);

        // Assert
        assertNotNull(object);
        assertEquals(object.id, expectedId);
        assertEquals(object.description, expectedText);
        assertEquals(object.timestamp, expectedTimestamp);
    }

    /**
     * Test of objectToXml method, of class XmlConverter.
     *
     * @throws java.io.UnsupportedEncodingException
     */
    @Test
    public void testObjectToXml() throws UnsupportedEncodingException {
        System.out.println("objectToXml");

        // Arrange
        final Happening happening = new Happening();
        happening.id = expectedId;
        happening.description = expectedText;
        happening.timestamp = expectedTimestamp;

        // Act
        final ByteArrayOutputStream stream = xmlConverter.objectToXml(happening);
        final String xml = new String(stream.toByteArray(), StandardCharsets.UTF_8.name());

        // Assert
        assertEquals(xml, xmlString);
    }

}
