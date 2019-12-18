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

public class XMLParser {

    private int TILE_SIZE = 30;
    private int mapSize;
    private ArrayList<String> levels= new ArrayList<>();
    private Level map;
    private String levelName = "Level1";
    private String filePath = "src/Model/XML/Levels.xml";

    public XMLParser (String filePath, String levelName) {
        this.filePath = filePath;
        this.levelName = levelName;

    }

    public XMLParser (String filePath) {
        this.filePath = filePath;
    }

    public Level parseXML() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filePath);
            Element element = doc.getDocumentElement();

            NodeList nodes = element.getChildNodes();
            System.out.println(element.getNodeName() + " has "+nodes.getLength()+" children");
            for(int i=0; i<nodes.getLength(); i++) {
                Node n = nodes.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    levels.add(n.getNodeName());
                }
                System.out.println("\t"+n.getNodeName());

            }


            NodeList lvl = element.getElementsByTagName(levelName);
            Node l = lvl.item(0);

            Element level = (Element) l;
            NodeList tileList = level.getElementsByTagName("Tile");

            mapSize = getMapSize(level);

            map = new Level(mapSize, TILE_SIZE);

            for (int i = 0; i < tileList.getLength(); i++){
                getTile(tileList, i);
            }

            NodeList list = level.getElementsByTagName("startMoney");
            Node node = list.item(0);
            map.addMoney(Integer.valueOf(node.getTextContent()));


            list = level.getElementsByTagName("winCondition");
            node = list.item(0);
            map.setWinCondition(Integer.valueOf(node.getTextContent()));


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
                    map.addPath((Tile) tileObject);
                    break;
                case "TowerArea":
                    map.addTowerArea((TowerArea) tileObject);
                    break;
                case "GoalArea":
                    map.addPath((Tile) tileObject);
                    break;
                case "AltPath1":
                    map.addAltPath1((Tile) tileObject);
                    break;
                case "AltPath2":
                    map.addAltPath2((Tile) tileObject);
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
     * @param level Element from which mapSize is gathered.
     * @return an integer with the size of the map.
     */
    private int getMapSize(Element level) {
        NodeList sizeOfMap = level.getElementsByTagName("mapSize");
        Node ms = sizeOfMap.item(0);
        Element s = (Element) ms;
        String size = s.getTextContent();

        return Integer.valueOf(size);
    }

    public ArrayList<String> getLevels(){
        return levels;
    }
}
