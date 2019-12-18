package Model.XML;
import org.junit.jupiter.api.*;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 *  Tester for the class XMLCreator
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class XMLCreatorTester {
    /** XMLCreator which is used in every test **/
    private XMLCreator xmlCreator;

    /**
     * Before each test XMLCreator is initiated
     * and a XML is generated
     */
    @BeforeEach
    public void beforeEach() {
        xmlCreator = new XMLCreator();
        xmlCreator.createLevels();
    }

    /**
     * Tests if the xmlCreator is not null
     */
    @Test
    public void testCreate() {
        Assertions.assertNotNull(xmlCreator);
    }

}
