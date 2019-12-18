package Model.XML.Area;

import java.util.LinkedList;
import java.util.Queue;

public interface AreaEffect {

    public Queue<Integer> landOn(LinkedList<Tile> tileList);
}
