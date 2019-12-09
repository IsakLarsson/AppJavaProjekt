package Model.XML;

import Model.Level;
import Model.XML.Area.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class XMLParser {

    private int TILE_SIZE = 20;
    private int mapSize;
    private Level map;

    public Level parseXML() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/Model/XML/Levels.xml");
            Element element = doc.getDocumentElement();

            NodeList lvl = element.getElementsByTagName("Level1");
            Node l = lvl.item(0);

            Element level = (Element) l;
            NodeList tileList = level.getElementsByTagName("Tile");

            mapSize = getMapSize(level);

            map = new Level(mapSize);

            for (int i = 0; i < tileList.getLength(); i++){
                getTile(tileList, i);
            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * Gets objects from a list of nodes and creates tile objects via
     * java reflection based on their atrribute names and returns a created
     * object
     * @param tileList the NodeList to read from
     * @param index the index to get the node from
     * @return An object created from a Tile class
     */
    private void getTile(NodeList tileList, int index) {
        try {
            Node readTile = tileList.item(index);
            Element tile = (Element)readTile;
            String tileAttribute = tile.getAttribute("AreaType");

            NodeList coordinateList = tile.getElementsByTagName(
                    "Coordinates");
            Node coordinate = coordinateList.item(0);
            String str = coordinate.getTextContent();
            String[] tokens = str.split(",");
            int X = Integer.valueOf(tokens[0]);
            int Y = Integer.valueOf(tokens[1]);

            Class<?> tileClass =
                    Class.forName("Model.XML.Area."+ tileAttribute);
            Constructor tileConstructor =
                    tileClass.getDeclaredConstructor(int.class, int.class
                            , int.class);
            Object tileObject = tileConstructor.newInstance(X,Y,TILE_SIZE);
            switch (tileAttribute){
                case "Path":
                    map.addPath((Path) tileObject);
                    break;
                case "SpawnArea":
                    map.addSpawn((SpawnArea) tileObject);
                    break;
                case "TowerArea":
                    map.addTowerArea((TowerArea) tileObject);
                    break;
                case "GoalArea":
                    map.addGoalArea((GoalArea) tileObject);
                    break;
                default:
                    map.addTile((Tile) tileObject);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the size of the map from the XML file
     * @param level
     * @return an integer with the mapsize.
     */
    private int getMapSize(Element level) {
        NodeList sizeOfMap = level.getElementsByTagName("mapSize");
        Node ms = sizeOfMap.item(0);
        Element s = (Element) ms;
        String size = s.getTextContent();

        return Integer.valueOf(size);
    }


    public int getMapSize(){
        return mapSize;
    }
}
