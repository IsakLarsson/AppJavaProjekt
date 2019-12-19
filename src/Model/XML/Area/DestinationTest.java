package Model.XML.Area;

import Model.Logic.Level;
import Model.XML.XMLParser;

class DestinationTest {

    private Destination destination = new Destination();
    private XMLParser parser = new XMLParser(ClassLoader.getSystemResourceAsStream("src/Model/XML/Levels.xml"));
    private Level level = parser.parseXML();
/*
    @Test
    public void testCalculateQueueDoesIsNotNull() {
        assertNotNull(destination.calculateQueue(level.getPath()));
    }

    @Test
    public void testThatNullListDoesNotCrash() {
        LinkedList<Tile> nullList = null;
        assertNull(destination.calculateQueue(nullList));
    }

 */
}