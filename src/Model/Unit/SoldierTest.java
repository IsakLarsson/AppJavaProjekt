package Model.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SoldierTest {

    private Soldier soldier;

    @BeforeEach
    public void beforeEach(){
        soldier = new Soldier();
    }

    @Test
    public void testThatFarmerIsCreated(){
        Assertions.assertNotNull(soldier);
    }

    @Test
    public void testDmg(){
        Assertions.assertEquals(5, soldier.getDmg());
    }

    @Test
    public void testHp(){
        Assertions.assertEquals(100, soldier.getHp());
    }

    @Test
    public void testTakeDmg(){
        soldier.takeDMG(10);
        Assertions.assertEquals(90, soldier.getHp());
    }
}
