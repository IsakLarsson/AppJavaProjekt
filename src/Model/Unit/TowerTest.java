package Model.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TowerTest {
    private Tower tower;

    @BeforeEach
    public void beforeEach(){
        tower = new Tower();
    }

    @Test
    public void testThatFarmerIsCreated(){
        Assertions.assertNotNull(tower);
    }

    @Test
    public void testDmg(){
        Assertions.assertEquals(10, tower.getDmg());
    }

    @Test
    public void testHp(){
        Assertions.assertEquals(0, tower.getHp());
    }

}
