package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ParseXML {

    public void parser() {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("tea.xml");
            Element level = doc.getDocumentElement();
            NodeList teaList = level.getElementsByTagName("tea");

            for (int i = 0; i < teaList.getLength(); i++) {

                Node tea = teaList.item(i);

                if (tea.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) tea;
                    String id = element.getAttribute("id");
                    System.out.println(id);

                    NodeList info = element.getChildNodes();

                    for (int j = 0; j < info.getLength(); j++) {
                        Node teaInfo = info.item(j);

                        if (teaInfo.getNodeType() == Node.ELEMENT_NODE) {
                            Element e = (Element) teaInfo;
                            System.out.println(e.getTagName() + " : " + e.getTextContent());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
