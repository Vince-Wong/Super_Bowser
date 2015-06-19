package game_logic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class MobLRMoveTest
{

   @Test
   public void testMoveBoolean()
   {
      MobLR toad = new MobLR("test", 5, 5, 7, true);
      assertEquals(5, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
      toad.move(true);
      assertNotEquals(6, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
      toad.move(true);
      assertNotEquals(6, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
      toad.move(true);
      assertNotEquals(6, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
      toad.move(true);
      assertNotEquals(6, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
      toad.move(true);
      assertNotEquals(6, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
      toad.move(true);
      assertNotEquals(6, toad.getX());
      toad.move(true);
      assertEquals(6, toad.getX());
   }
}
