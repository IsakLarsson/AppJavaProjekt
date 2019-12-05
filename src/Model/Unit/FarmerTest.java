package Model.Unit;


import org.junit.jupiter.api.*;

public class FarmerTest {

    private Farmer farmer;

    @BeforeEach
    public void beforeEach(){
        farmer = new Farmer();
    }

    @Test
    public void testThatFarmerIsCreated(){
        Assertions.assertNotNull(farmer);
    }

    @Test
    public void testDmg(){
        Assertions.assertEquals(1, farmer.getDmg());
    }

    @Test
    public void testHp(){
        Assertions.assertEquals(50, farmer.getHp());
    }

    @Test
    public void testSpeed(){
        Assertions.assertEquals(20, farmer.getSpeed());
    }

   @Test
   public void testTakeDmg(){
        farmer.takeDMG(10);
        Assertions.assertEquals(40, farmer.getHp());
   }
}
