package GUI;

import java.util.Comparator;

public class HighScore {
    private String name = "";
    private int level = 0;
    private int score = 0;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Score comparator
     */
    public static Comparator<HighScore> scoreComparator = new Comparator<HighScore>() {
        @Override
        public int compare(HighScore o1, HighScore o2) {
            return Integer.compare(o2.getScore(), o1.getScore());
        }
    };
}
