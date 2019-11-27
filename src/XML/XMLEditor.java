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

//TODO: Skapa metoder för olika levels
//TODO: XML ska följa och valideras mha ett XML-Schema
//TODO: Win conditions
//TODO: Flera möjliga vägsträckor
//TODO: Alla resultat ska sparas central på en server mha av en databas
//TODO: Highscores baserad på tid, poäng etc.
//TODO: Ska finnas banor med "växlar"
//TODO: Speciella områden, ex. teleport

public class XMLEditor {

    public XMLEditor() {
    }

    /**
     * NOTE: Does not create a new GameMap.xml file.
     * GameMap.xml needs to exist in the same directory
     * as XMLEditor to work properly (atm)
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createLevel1() throws ParserConfigurationException, TransformerException {
        //Create documentBuilder - Can create documents
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();

        //Create document with documentBuilder and add a new element named Game to the document
        Document doc = docBuilder.newDocument();
        Element elem = doc.createElement("Level1");
        doc.appendChild(elem);

        Element Area = doc.createElement("Area");
        doc.appendChild(elem);

        //Create ID named Farmer and assign it to elem
        Attr attribute = doc.createAttribute("UnitID");
        attribute.setValue("Farmer");
        elem.setAttributeNode(attribute);

        //Specify where output should be redirected
        String path = "C:\\Users\\Albin Jönsson\\Desktop\\Programmering\\" +
                "Applikationsutveckling i Java\\Grupp2\\src\\XML\\GameMap.xml";
        StreamResult streamRes = new StreamResult(path);

        //Transform code to XML-format and place it in specified path (streamRes: \\GameMap.xml)
        TransformerFactory transformerFac = TransformerFactory.newInstance();
        Transformer transformer = transformerFac.newTransformer();
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, streamRes);
    }
}
