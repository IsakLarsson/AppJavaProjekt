package Controller;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/Model/XML/evels.xml";
        if (args.length > 0) {
            filePath = args[0];
        }
        new Controller(filePath);
    }
}
