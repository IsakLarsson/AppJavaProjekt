package XML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XMLEditorTester {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        XMLEditor XMlEditor = new XMLEditor();
        XMlEditor.createLevel1();
    }
}
