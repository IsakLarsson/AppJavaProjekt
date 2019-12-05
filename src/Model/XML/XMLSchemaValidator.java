package Model.XML;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.swing.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class XMLSchemaValidator {

    public static void main(String[] args) throws IOException, SAXException {
        try {
            // define the type of schema - we use W3C:
            String schemaLang = "http://www.w3.org/2001/XMLSchema";
            // get validation driver:
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
            // create schema by reading it from an XSD file:
            Schema schema = null;

            try {
                schema = factory.newSchema(new StreamSource("src\\Model\\XML\\XMLSchema.xsd"));
            }
            catch (SAXException e) {
                JOptionPane.showMessageDialog(null, "Incorrect format for Model.XML");
            }
            Validator val = null;
            if (schema != null) {
                val = schema.newValidator();
            }
            if (val != null) {
                val.validate(new StreamSource("src\\Model.XML\\Levels.xml"));
            }
        }
        catch (SAXParseException e) {
            JOptionPane.showMessageDialog(null, "Incorrect format for Model.XML");
        }
        JOptionPane.showMessageDialog(null, "Validator finished without errors");
    }
}

