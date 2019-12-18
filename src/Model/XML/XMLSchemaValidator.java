package Model.XML;

import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 *  Validator for the XML-file Levels.xml
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class XMLSchemaValidator {
    /** Schema to be followed **/
    private Schema schema;
    /** Schema creator **/
    private SchemaFactory factory;

    /**
     * Constructor for XMLSchemaValidator
     */
    public XMLSchemaValidator() {
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        factory = SchemaFactory.newInstance(schemaLang);
        schema = null;
    }

    /**
     * Method to validate Levels.xml using XMLSchema.xsd
     */
    public int validateXML() {
        try {
            schema = factory.newSchema(new StreamSource("src\\Model\\XML\\XMLSchema.xsd"));
            Validator val = null;
            if (schema != null) {
                val = schema.newValidator();
            }
            if (val != null) {
                val.validate(new StreamSource("src\\Model\\XML\\Levels.xml"));
            }
        }
        catch (SAXException | IOException e) {
            return -1;
        }
        return 0;
    }
}

