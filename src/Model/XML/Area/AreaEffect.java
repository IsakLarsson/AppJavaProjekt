package Model.XML.Area;

import java.util.LinkedList;
import java.util.Queue;

public interface AreaEffect {


    Queue<Integer> landOn(LinkedList<Tile> tileList);
}
