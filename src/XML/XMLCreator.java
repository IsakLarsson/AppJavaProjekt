package XML;

import org.w3c.dom.Attr;
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

//TODO: Skapa metoder för olika levels
//TODO: XML ska följa och valideras mha ett XML-Schema
//TODO: Win conditions
//TODO: Flera möjliga vägsträckor
//TODO: Alla resultat ska sparas central på en server mha av en databas
//TODO: Highscores baserad på tid, poäng etc.
//TODO: Ska finnas banor med "växlar"
//TODO: Speciella områden, ex. teleport

public class XMLCreator {
    private String path;
    private DocumentBuilderFactory docBuildFactory;
    private DocumentBuilder docBuilder;
    private Document doc;

    public XMLCreator() {
    }

    //Create file in specified String p (path)
    public XMLCreator(String p) {
        path = p;
    }

    /**
     * Creates a new XML-file named Levels.xml
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
        path = "src\\XML\\Levels.xml";
        StreamResult streamRes = new StreamResult(new File(path));

        //Transform code to XML-format and place it in given directory(see path)
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
        mapSize.appendChild(doc.createTextNode("20"));
        level1.appendChild(mapSize);

        //Sets coordinates for playerPath
        Element playerPath = doc.createElement("playerPath");
        level1.appendChild(playerPath);
        for (int i = 0; i < 20; i++){
            Element coordinates = doc.createElement("coordinates");
            coordinates.appendChild(doc.createTextNode(i + ",0"));
            playerPath.appendChild(coordinates);
        }

        //Sets coordinates for towerArea
        Element towerArea = doc.createElement("towerArea");
        level1.appendChild(towerArea);
        for (int i = 0; i < 2; i++){
            Element coordinates = doc.createElement("coordinates");
            coordinates.appendChild(doc.createTextNode(i + ",1"));
            towerArea.appendChild(coordinates);
        }

        //Sets coordinates for spawnArea
        Element spawnArea = doc.createElement("spawnArea");
        level1.appendChild(spawnArea);
        Element coordinates = doc.createElement("coordinates");
        coordinates.appendChild(doc.createTextNode("0,0"));
        spawnArea.appendChild(coordinates);

        //Sets coordinates for goalArea
        Element goalArea = doc.createElement("goalArea");
        level1.appendChild(goalArea);
        coordinates = doc.createElement("coordinates");
        coordinates.appendChild(doc.createTextNode("0,19"));
        goalArea.appendChild(coordinates);

    }

}
