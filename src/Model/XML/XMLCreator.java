package Model.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * XML creator class. Creates a XML containing
 * level1 and level2. Provides a simplified interface
 * to ease the creation of a XML.
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class XMLCreator {
    /** String for path to file **/
    private String path;
    /** DocumentBuilderFactory **/
    private DocumentBuilderFactory docBuildFactory;
    /** DocumentBuilder **/
    private DocumentBuilder docBuilder;
    /** Document **/
    private Document doc;
    /** Size of the map **/
    private int mapSizeNr = 20;

    /**
     * Constructor for XMLCreator
     */
    public XMLCreator() {}

    /**
     * Create a new XML-file named Levels.xml containing lvl1 and lvl2
     */
    public void createLevels() {
        //Create documentBuilder - Can create documents
        docBuildFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docBuildFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(null, "XML error: " +
                    "Failed to Parse");
        }

        //Create root-document with documentBuilder and
        // add a new element named Game to the document
        doc = docBuilder.newDocument();
        Element levels = doc.createElement("Levels");
        doc.appendChild(levels);

        //Create first and second level
        createLevel1(levels);
        createLevel2(levels);

        //Specify where the file should be created and the name of it
        path = "src/Model/XML/Levels.xml";
        StreamResult streamRes = new StreamResult(new File(path));

        //Transform code to Levels.XML-format and place it in given directory(see path)
        TransformerFactory transformerFac = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFac.newTransformer();
        } catch (TransformerConfigurationException e) {
            JOptionPane.showMessageDialog(null, "XML error: " +
                    "Failed to transform");
        }

        DOMSource source = new DOMSource(doc);
        try {
            if (transformer != null) {
                transformer.transform(source, streamRes);
            }
        } catch (TransformerException e) {
            JOptionPane.showMessageDialog(null, "XML error: " +
                    "Failed to transform");
        }
    }

    /**
     * Create level 1
     */
    private void createLevel1(Element levels) {
        //Root for level 1
        Element level1 = doc.createElement("Level1");
        levels.appendChild(level1);

        //Sets mapSize of level 1
        Element mapSize = doc.createElement("mapSize");
        mapSize.appendChild(doc.createTextNode(mapSizeNr + ""));
        level1.appendChild(mapSize);

        //Set start money and win condition
        setStartMoney(level1, 100);
        setWinCondition(level1, 10);

        //Create needed area types
        createSpawnArea(level1,0,0);
        createHorizontalArea(level1, 9, 1, 0, "Path");
        createVerticalArea(level1, 8, 9, 1, "Path");
        createHorizontalArea(level1, 10, 9, 9, "Path");

        createTowerArea(level1, 3, 2);
        createTowerArea(level1, 10, 4);
        createTowerArea(level1, 16, 10);
        createGoalArea(level1, 19, 9);

    }

    /**
     * Create level 2
     */
    private void createLevel2(Element levels) {
        //Root for level 1
        Element level2 = doc.createElement("Level2");
        levels.appendChild(level2);

        //Sets mapSize of level 1
        Element mapSize = doc.createElement("mapSize");
        mapSize.appendChild(doc.createTextNode(mapSizeNr + ""));
        level2.appendChild(mapSize);

        //Set start money and win condition
        setStartMoney(level2, 50);
        setWinCondition(level2, 5);

        //Create needed area types
        createSpawnArea(level2,0,9);
        createHorizontalArea(level2, 6, 1, 9, "Path");
        createVerticalArea(level2, -3,6, 8, "AltPath1");
        createVerticalArea(level2, 3,6, 10, "AltPath2");
        createHorizontalArea(level2, 6, 7, 6, "AltPath1");
        createHorizontalArea(level2, 6, 7, 12, "AltPath2");
        createVerticalArea(level2, -3,13, 12, "AltPath2");
        createVerticalArea(level2, 3,13, 6, "AltPath1");
        createHorizontalArea(level2, 6, 13, 9, "Path");

        createTowerArea(level2, 10, 5);
        createTowerArea(level2, 7, 8);
        createTowerArea(level2, 7, 10);
        createTowerArea(level2, 10, 13);
        createGoalArea(level2, 19, 9);

    }

    /**
     * Starts at given StartX and StartY and produces nrArea numbers of area tiles
     * in a horizontal direction from the X-Coordinate StartX to StartX+nrArea.
     * If nrArea is positive the area tiles are built right to left from start coordinates
     * and if nrArea is negative the tiles are built left to right.
     * @param dest Destination, where the area should be inserted as a sub-element
     * @param startX Start X-coordinate
     * @param startY Start Y-coordinate
     * @param nrArea Number of area tiles
     * @param name Name of Area to be created
     */
    private void createHorizontalArea(Element dest, int nrArea, int startX, int startY, String name) {
        int i = startX;
        //Build area tile, from coordinates, downwards
        if (nrArea > 0) {
            for (; i < (nrArea + startX); i++) {
                Element areaType = doc.createElement("Tile");
                areaType.setAttribute("AreaType", name);
                dest.appendChild(areaType);
                Element coordinates = doc.createElement("Coordinates");
                coordinates.appendChild(doc.createTextNode(i + "," + startY));
                areaType.appendChild(coordinates);
            }
        }
        //Build area tile, from coordinates, upwards
        else {
            for (; i > (nrArea + startX); i--) {
                if (i <= 0) {
                    break;
                }
                Element areaType = doc.createElement("Tile");
                areaType.setAttribute("AreaType", name);
                dest.appendChild(areaType);
                Element coordinates = doc.createElement("Coordinates");
                coordinates.appendChild(doc.createTextNode(i + "," + startY));
                areaType.appendChild(coordinates);
            }
        }

    }

    /**
     * Starts at given StartX and StartY and produces nrArea numbers of area tiles
     * in a vertical direction from the X-Coordinate StartX to StartX+nrArea.
     * If nrArea is positive the area tiles are built upwards from start coordinates
     * and if nrArea is negative the tiles are built downwards.
     * @param dest Destination, where the area should be inserted as a sub-element
     * @param startX Start X-coordinate
     * @param startY Start Y-coordinate
     * @param nrArea Number of area tiles
     * @param name Name of Area to be created
     */
    private void createVerticalArea(Element dest, int nrArea, int startX, int startY, String name) {
        int i = startY;
        //Build area tile, from coordinates, to the right
        if (nrArea > 0) {
            for (; i < (nrArea + startY); i++) {
                Element areaType = doc.createElement("Tile");
                areaType.setAttribute("AreaType", name);
                dest.appendChild(areaType);
                Element coordinates = doc.createElement("Coordinates");
                coordinates.appendChild(doc.createTextNode(startX + "," + i));
                areaType.appendChild(coordinates);
            }
        }
        //Build area tile, from coordinates, to the left
        else {
            for (; i > (nrArea + startY); i--) {
                if (i <= 0) {
                    break;
                }
                Element areaType = doc.createElement("Tile");
                areaType.setAttribute("AreaType", name);
                dest.appendChild(areaType);
                Element coordinates = doc.createElement("Coordinates");
                coordinates.appendChild(doc.createTextNode(startX + "," + i));
                areaType.appendChild(coordinates);
            }
        }

    }

    /**
     * Creates a tower area with nrArea numbers of area.
     * Randomizes placement of tower areas.
     * @param dest Destination, where the area should be inserted as a sub-element
     * @param x X-Coordinate of tower area
     * @param y Y-Coordinate of tower area
     */
    private void createTowerArea(Element dest, int x, int y) {
        Element areaType = doc.createElement("Tile");
        areaType.setAttribute("AreaType", "TowerArea");
        dest.appendChild(areaType);
        Element coordinates = doc.createElement("Coordinates");
        coordinates.appendChild(doc.createTextNode(x + "," + y));
        areaType.appendChild(coordinates);
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

    /**
     * Sets start money
     * @param dest Destination, where the area should be inserted as a sub-element
     * @param sMoney Start money
     */
    private void setStartMoney(Element dest, int sMoney) {
        Element money = doc.createElement("startMoney");
        dest.appendChild(money);
        money.appendChild(doc.createTextNode(sMoney + ""));
    }

    /**
     * Sets win condition
     * @param dest Destination, where the area should be inserted as a sub-element
     * @param wCondition Win condition
     */
    private void setWinCondition(Element dest, int wCondition) {
        Element condition = doc.createElement("winCondition");
        dest.appendChild(condition);
        condition.appendChild(doc.createTextNode(wCondition + ""));
    }
}
