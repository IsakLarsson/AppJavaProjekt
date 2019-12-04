package XML;

import GUI.Tile;
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

    private ArrayList<Tile> tileMap;
    private int TILE_SIZE = 20;

    public ArrayList parseXML() {

        tileMap = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/XML/Levels.xml");
            Element element = doc.getDocumentElement();

            NodeList lvl = element.getElementsByTagName("Level1");
            Node l = lvl.item(0);

            Element level = (Element) l;
            NodeList tileList = level.getElementsByTagName("Tile");

            int mapSize = getMapSize(level);


            for (int i = 0; i < tileList.getLength(); i++){
                Node readTile = tileList.item(i);
                Element tile = (Element)readTile;
                String tileAttribute = tile.getAttribute("AreaType");

                NodeList coordinateList = tile.getElementsByTagName(
                        "Coordinates");
                Node coordinate = coordinateList.item(0);
                String str = coordinate.getTextContent();
                String[] tokens = str.split(",");
                int X = Integer.valueOf(tokens[0]);
                int Y = Integer.valueOf(tokens[1]);

                Class<?> tileClass = Class.forName("XML."+ tileAttribute);
                Constructor tileConstructor =
                        tileClass.getDeclaredConstructor(int.class, int.class
                                    , int.class);
                Object tileObject = tileConstructor.newInstance(X,Y,TILE_SIZE);
                tileMap.add((Tile) tileObject);
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return tileMap;
    }

    private int getMapSize(Element level) {
        NodeList sizeOfMap = level.getElementsByTagName("mapSize");
        Node ms = sizeOfMap.item(0);
        Element s = (Element) ms;
        String size = s.getTextContent();

        return Integer.valueOf(size);
    }
}
