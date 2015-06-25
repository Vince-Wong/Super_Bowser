package game_logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class MobUDMoveTest
{

   @Test
   public void testMoveBoolean()
   {
      MobUD toad = new MobUD("test", 5, 5, 7, true);
      assertEquals(5, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
      toad.move(true);
      assertNotEquals(6, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
      toad.move(true);
      assertNotEquals(6, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
      toad.move(true);
      assertNotEquals(6, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
      toad.move(true);
      assertNotEquals(6, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
      toad.move(true);
      assertNotEquals(6, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
      toad.move(true);
      assertNotEquals(6, toad.getY());
      toad.move(true);
      assertEquals(6, toad.getY());
   }

}
