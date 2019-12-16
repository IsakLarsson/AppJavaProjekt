package Controller;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/Model/XML/Levels.xml";
        if (args.length > 0) {
            filePath = args[0];
        }
        Controller controller = new Controller(filePath);
        //ParseXML xmlparser = new ParseXML();
        //xmlparser.parser();
    }
}
