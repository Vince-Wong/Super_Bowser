package Tests;

import static org.junit.Assert.*;
import game_logic.Inventory;
import game_logic.Item;

import org.junit.Test;

public class InventoryRemoveItemTest
{

   @Test
   public void testRemoveItem()
   {
      Inventory bag1 = new Inventory();
      Item testItem1 = new Item("Box1", false, 1, 1, "test item");
      bag1.addItem(testItem1);
      boolean check1 = bag1.removeItem(testItem1);
      assertEquals(true, check1);
      boolean check2 = bag1.removeItem(testItem1);
      assertEquals(false, check2);
   }
}
