package Model.XML.Area;

import java.util.LinkedList;
import java.util.Queue;

public class Destination {
    private Queue<Integer> q;
    public Destination() {
        q = new LinkedList<>();
    }

    //TODO Path list måste innehålla spawnpoint då det är en den av pathen också
    public Queue<Integer> calculateQueue(LinkedList<Tile> tiles) {

        if (tiles == null) {
            return null;
        }

        Tile firstTile = tiles.pop();
        Tile secondTile = tiles.peek();
        String direction = "";
        int dest = 0;
        int start = 0;
        int i = 0;

        if (secondTile == null) {
            System.out.println("Game over??");
            return null;
        }

        if (firstTile.getxCoordinate() < secondTile.getxCoordinate()) {
            dest = secondTile.getxCoordinate()*firstTile.getSize();
            start = firstTile.getxCoordinate()*firstTile.getSize();
            direction = "horizontally";
        }
        else if (firstTile.getyCoordinate() < secondTile.getyCoordinate()) {
            dest = secondTile.getyCoordinate(); // Samma sak här
            start = firstTile.getyCoordinate();
            direction = "vertically";
        }

        while (start < dest) {

            System.out.println("TEST1: " + start + " " + dest);

            if (direction.equals("horizontally")) {
                q.add(start);
                q.add(firstTile.getyCoordinate());
            }
            else {
                q.add(firstTile.getxCoordinate());
                q.add(start);
            }

            start++;
            i++;
        }

        System.out.println("Returning: " + q);
        return q;
    }
}
