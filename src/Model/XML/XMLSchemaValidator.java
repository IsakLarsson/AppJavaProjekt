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
    private Schema schema;
    private SchemaFactory factory;

    public XMLSchemaValidator() {
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        factory = SchemaFactory.newInstance(schemaLang);
        Schema schema = null;
    }

    public void validateXML() {
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
            JOptionPane.showMessageDialog(null, "Incorrect format for Levels.XML");
        }
        JOptionPane.showMessageDialog(null, "Validator finished without errors");
    }
}

