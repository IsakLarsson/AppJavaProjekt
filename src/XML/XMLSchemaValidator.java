package XML;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class XMLSchemaValidator {

    public static void main(String[] args) throws IOException, SAXException {
        // define the type of schema - we use W3C:
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        // get validation driver:
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
        // create schema by reading it from an XSD file:
        Schema schema = null;

        try {
            schema = factory.newSchema(new StreamSource("XMLSchema.xsd"));
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        Validator val = schema.newValidator();
        val.validate(new StreamSource("Levels.xml"));

    }
}

