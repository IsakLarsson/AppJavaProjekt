package XML;

import GUI.Tile;
import GUI.TileMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class ParseXML {

    private int[][] buildLevel;

    public TileMap parser() {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/XML/Levels.xml");
            Element element = doc.getDocumentElement();

            NodeList lvl = element.getElementsByTagName("Level1");

            Node l = lvl.item(0);

            Element level = (Element) l;


            int mapSize = getMapSize(level);

            TileMap tileMap = new TileMap(mapSize, mapSize);



            //buildLevel = new int[mapSize][mapSize];

            for(int i = 0; i < mapSize; i++){
                for(int j = 0; j < mapSize; j++){
                    Tile tile = new Tile(j,i,20,20, Color.BLACK);
                    tileMap.addTile(tile);
                }
            }

            tileMap = findPath(tileMap, level);
            tileMap = findTowerArea(tileMap, level);
            tileMap = findSpawnArea(tileMap, level);


            /*
            for(int i = 0; i < mapSize; i++){
                System.out.println();
                for(int j = 0; j < mapSize; j++){
                    System.out.print(buildLevel[j][i]);
                }
            }

             */




        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tileMap;
    }

    /**
     * Gets the size of the map for the current level.
     * @param level Node form which Size is found.
     * @return Size of map.
     */
    private int getMapSize(Element level){


        NodeList sizeOfMap = level.getElementsByTagName("mapSize");
        Node ms = sizeOfMap.item(0);
        Element s = (Element) ms;
        String size = s.getTextContent();

        return Integer.valueOf(size);
    }

    /**
     * Finds cordinates for Path in XML file.
     * @param tileMap Array to set to represent level.
     * @param level Element from which Path nodelist is found.
     * @return Update buildLevel array.
     */
    private TileMap findPath(TileMap tileMap, Element level){


        NodeList n = level.getElementsByTagName("playerPath");
        Node pa = n.item(0);
        Element path = (Element) pa;

        if(pa.getNodeType() == Node.ELEMENT_NODE){
            NodeList list = path.getChildNodes();

            for(int j = 0; j < list.getLength(); j++){
                Node node = list.item(j);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element cord = (Element) node;
                    String str = cord.getTextContent();
                    //Seperate X and Y (X,Y).
                    String[] tokens = str.split(",");
                    Integer X = Integer.valueOf(tokens[0]);
                    Integer Y = Integer.valueOf(tokens[1]);
                    Tile tile = new Tile(X, Y, 20, 20, Color.orange);
                    tileMap.addTile(tile);
                }
            }
        }
        return tileMap;
    }

    /**
     * Finds cordinates for TowerArea in XML file.
     * @param tileMap Array to set to represent level.
     * @param level Element from which TowerArea nodelist is found.
     * @return Update buildLevel array.
     */
    private TileMap findTowerArea(TileMap tileMap, Element level){

        NodeList p = level.getElementsByTagName("towerArea");
        Node pa = p.item(0);
        Element path = (Element) pa;

        if(pa.getNodeType() == Node.ELEMENT_NODE){
            NodeList info = path.getChildNodes();

            for(int j = 0; j < info.getLength(); j++){
                Node cords = info.item(j);

                if(cords.getNodeType() == Node.ELEMENT_NODE){
                    Element cord = (Element) cords;
                    String str = cord.getTextContent();
                    //Seperate X and Y (X,Y).
                    String[] tokens = str.split(",");
                    Integer X = Integer.valueOf(tokens[0]);
                    Integer Y = Integer.valueOf(tokens[1]);
                    Tile tile = new Tile(X, Y, 20, 20, Color.white);
                    tileMap.addTile(tile);
                }
            }
        }
        return tileMap;
    }

    /**
     * Finds cordinates for spawnArea in XML file.
     * @param tileMap Array to set to represent level.
     * @param level Element from which SpawnArea nodelist is found.
     * @return Update buildLevel array.
     */
    private TileMap findSpawnArea(TileMap tileMap, Element level){

        NodeList p = level.getElementsByTagName("spawnArea");
        Node pa = p.item(0);
        Element path = (Element) pa;

        if(pa.getNodeType() == Node.ELEMENT_NODE){
            NodeList list = path.getChildNodes();

            for(int j = 0; j < list.getLength(); j++){
                Node node = list.item(j);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element cord = (Element) node;
                    String str = cord.getTextContent();
                    //Seperate X and Y (X,Y).
                    String[] tokens = str.split(",");
                    Integer X = Integer.valueOf(tokens[0]);
                    Integer Y = Integer.valueOf(tokens[1]);
                    Tile tile = new Tile(X, Y, 20, 20, Color.magenta);
                    tileMap.addTile(tile);
                }
            }
        }
        return tileMap;
    }
}
