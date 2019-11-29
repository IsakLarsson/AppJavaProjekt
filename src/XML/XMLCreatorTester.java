package XML;
import org.junit.jupiter.api.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XMLCreatorTester {
    XMLCreator xmlCreator;

    @BeforeEach
    public void beforeEach() throws TransformerException, ParserConfigurationException {
        xmlCreator = new XMLCreator();
        xmlCreator.createLevels();
    }

    @Test
    public void testCreate() {
        Assertions.assertNotNull(xmlCreator);
    }

}
