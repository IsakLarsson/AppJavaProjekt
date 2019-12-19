package Controller;

import Model.XML.XMLCreator;

public class Main {
    public static void main(String[] args) {
        XMLCreator creator = new XMLCreator();
        creator.createLevels();
        String filePath = null;
        if (args.length > 0) {
            filePath = args[0];
        }
        new Controller(filePath);
    }
}
