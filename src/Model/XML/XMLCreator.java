package Model.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

//TODO: Win conditions
//TODO: Flera möjliga vägsträckor
//TODO: Ska finnas banor med "växlar"
//TODO: Speciella områden, ex. teleport

public class XMLCreator {
    private String path;
    private DocumentBuilderFactory docBuildFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private int mapSizeNr = 20;

    public XMLCreator() {
    }

    //Create file in specified String p (path)
    public XMLCreator(String p) {
        path = p;
    }

    /**
     * Creates a new Levels.XML-file named Levels.xml
     * @throws ParserConfigurationException - Configuration error
     * @throws TransformerException - Error during transformation process
     */
    public void createLevels() throws ParserConfigurationException, TransformerException {
        //Create documentBuilder - Can create documents
        docBuildFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docBuildFactory.newDocumentBuilder();

        //Create first level
        createLevel1();

        //Specify where the file should be created and the name of it
        path = "src\\Model\\XML\\Levels.xml";
        StreamResult streamRes = new StreamResult(new File(path));

        //Transform code to Levels.XML-format and place it in given directory(see path)
        TransformerFactory transformerFac = TransformerFactory.newInstance();
        Transformer transformer = transformerFac.newTransformer();
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, streamRes);
    }

    /**
     * Create level 1
     */
    private void createLevel1() {
        //Create root-document with documentBuilder and add a new element named Game to the document
        doc = docBuilder.newDocument();
        Element levels = doc.createElement("Levels");
        doc.appendChild(levels);

        //Root for level 1
        Element level1 = doc.createElement("Level1");
        levels.appendChild(level1);

        //Sets mapSize of level 1
        Element mapSize = doc.createElement("mapSize");
        mapSize.appendChild(doc.createTextNode(mapSizeNr + ""));
        level1.appendChild(mapSize);

        //Create needed area types
        createSpawnArea(level1,0,0);
        createPathArea(10, level1);
        createGoalArea(level1, 10, 0);
        createTowerArea(2, level1, 5,2);
    }

    /**
     * Create level 2
     */
    private void createLevel2() {}

    /**
     * Creates a path area with nrArea numbers of area
     * @param nrArea Number of area tiles
     * @param dest Destination, where the area should be inserted as a sub-element
     */
    private void createPathArea(int nrArea, Element dest) {
        int i = 1;
        for (; i < nrArea; i++){
            Element areaType = doc.createElement("Tile");
            areaType.setAttribute("AreaType", "Path");
            dest.appendChild(areaType);
            Element coordinates = doc.createElement("Coordinates");
            coordinates.appendChild(doc.createTextNode(i + ",0"));
            areaType.appendChild(coordinates);
        }
    }

    /**
     * Creates a tower area with nrArea numbers of area.
     * Randomizes placement of tower areas.
     * @param nrArea Number of area tiles
     * @param dest Destination, where the area should be inserted as a sub-element
     * @param max Maximum value on X/Y-axis
     * @param min Minimum value on X/Y-axis
     */
    private void createTowerArea(int nrArea, Element dest, int max, int min) {
        int i = 0;
        for (; i < nrArea; i++){
            //Randomize two values, X and Y, as coordinates for each area
            int randX = ThreadLocalRandom.current().nextInt(min, max + 1);
            int randY = ThreadLocalRandom.current().nextInt(min, max + 1);

            Element areaType = doc.createElement("Tile");
            areaType.setAttribute("AreaType", "TowerArea");
            dest.appendChild(areaType);
            Element coordinates = doc.createElement("Coordinates");
            coordinates.appendChild(doc.createTextNode(randX + "," + randY));
            areaType.appendChild(coordinates);
        }
    }

    /**
     * Creates a spawn area placed at the first path area coordinate.
     * @param dest Destination, where the area should be inserted as a sub-element
     */
    private void createSpawnArea(Element dest, int x, int y) {
        Element areaType = doc.createElement("Tile");
        areaType.setAttribute("AreaType", "SpawnArea");
        dest.appendChild(areaType);
        Element coordinates = doc.createElement("Coordinates");
        coordinates.appendChild(doc.createTextNode(x + "," + y));
        areaType.appendChild(coordinates);
    }

    /**
     * Creates a goal area placed at the last path area coordinate.
     * @param dest Destination, where the area should be inserted as a sub-element
     */
    private void createGoalArea(Element dest, int x, int y) {
        Element areaType = doc.createElement("Tile");
        areaType.setAttribute("AreaType", "GoalArea");
        dest.appendChild(areaType);
        Element coordinates = doc.createElement("Coordinates");
        coordinates.appendChild(doc.createTextNode(x + "," + y));
        areaType.appendChild(coordinates);
    }
}
