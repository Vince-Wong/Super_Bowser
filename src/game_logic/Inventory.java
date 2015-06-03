package game_logic;

public class Inventory
{

   public static final int MAX_SIZE = 30;
   public int currentSize;
   private Item[] items;
   
   public Inventory()
   {
      this.currentSize = 0;
      this.items = new Item[MAX_SIZE];
   }
   
   public boolean addItem(Item newItem)
   {
      //TODO
      int k;
      for(k = 0; k < items.length; k++)
      {
         if(newItem.getName().equals(items[k].getItem()) 
            && newItem.isStackable() == true)
         {
            items[currentSize + 1] = newItem;
            return true;
         }
         else if(currentSize >= MAX_SIZE)
         {
            return false;
         }
         else if(currentSize < MAX_SIZE)
         {
            items[currentSize + 1] = newItem;
            return true;
         }
         else
            continue;
      } 
      return false;
   }
   
   
   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }

}
