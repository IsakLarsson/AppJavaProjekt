package Model.XML.Area;

import Model.Level;
import Model.XML.XMLParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationTest {

    private Destination destination = new Destination();
    private XMLParser parser = new XMLParser();
    private Level level = parser.parseXML();

    @Test
    public void testCalculateQueueDoesIsNotNull() {
        assertNotNull(destination.calculateQueue(level.getPath()));
    }

}