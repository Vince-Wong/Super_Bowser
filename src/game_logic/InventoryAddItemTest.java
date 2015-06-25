package game_logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class InventoryAddItemTest
{

   @Test
   public void testAddItem()
   {
      Inventory bag1 = new Inventory();
      Item testItem1 = new Item("Box1", false, 1, 1, "test item");
      boolean check1 = bag1.addItem(testItem1);
      assertEquals(true, check1);
      boolean check2 = bag1.addItem(testItem1);
      assertEquals(false, check2);
   }
}
