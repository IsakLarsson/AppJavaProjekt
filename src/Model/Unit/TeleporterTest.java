package Model.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeleporterTest {
    private Teleporter teleporter;

    @BeforeEach
    public void beforeEach(){
        teleporter = new Teleporter();
    }

    @Test
    public void testThatFarmerIsCreated(){
        Assertions.assertNotNull(teleporter);
    }

    @Test
    public void testDmg(){
        Assertions.assertEquals(5, teleporter.getDmg());
    }

    @Test
    public void testHp(){
        Assertions.assertEquals(50, teleporter.getHp());
    }

    @Test
    public void testTakeDmg(){
        teleporter.takeDMG(10);
        Assertions.assertEquals(40, teleporter.getHp());
    }


}
