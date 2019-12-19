package Model;

import java.util.Comparator;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * HighScore class where all data is stored for each entry in G2Game table
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class HighScore {
    /** Name of person **/
    private String name = "";
    /** Level number **/
    private String level = "";
    /** Score number **/
    private int score = 0;

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for level
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Getter for score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for name
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for level
     * @param level level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * setter for score
     * @param score score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Score comparator. Returns 0,-1 or 1 depending on if o1
     * is are greater than, less than or equal to o2.
     */
    public static Comparator<HighScore> scoreComparator = new Comparator<HighScore>() {
        @Override
        public int compare(HighScore o1, HighScore o2) {
            return Integer.compare(o2.getScore(), o1.getScore());
        }
    };
}
