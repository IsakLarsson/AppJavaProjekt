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
        System.out.println("Starting...");
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
            System.out.println("VERTICALLY");
            dest = secondTile.getyCoordinate()*firstTile.getSize();
            start = firstTile.getyCoordinate()*firstTile.getSize();
            direction = "vertically";
        }

        while (start < dest) {

            System.out.println("TEST1: " + start + " " + dest);

            if (direction.equals("horizontally")) {
                q.add(start);
                q.add(firstTile.getyCoordinate()*firstTile.getSize());
            }
            else {
                q.add(firstTile.getxCoordinate()*firstTile.getSize());
                q.add(start);
            }

            start++;
            i++;
        }

        System.out.println("Returning: " + q);
        return q;
    }
}
