package game_logic;

public class Inventory
{
   public static final int MAX_SIZE = 8, MIN_SIZE = 0;   //max at 2 for testing
   public int currentSize;
   private Item[] items;
   
   public Inventory()
   {
      this.currentSize = 0;
      this.items = new Item[MAX_SIZE];
   }
      
   /**
    * Attempts to add item object to inventory
    * @param newItem - item object being added
    * @return - true if item was added, false if it wasn't
    */
   public boolean addItem(Item newItem)
   {
      //TODO
      int k;
      //for adding items in inventory is empty
      if(currentSize == MIN_SIZE)
      {
         items[currentSize] = newItem;
         currentSize ++;
         return true;
      }   
      else if(currentSize <= MAX_SIZE)
      {
         for(k = 0; k < currentSize; k++)
         {
            //for increasing item quantity
            if(newItem.getName().equals(items[k].getName()))
               {
                  return items[k].increaseQuantity();
                  //TODO maybe put in a flag for inventory is full?
               }
         }
         //for adding a new item to the inventory
         if(currentSize < MAX_SIZE)
         {
            items[k] = newItem;
            currentSize ++;
            return true;
         }    
      }
      //maybe put in a flag for inventory is full?
      return false;
   }
   
   /**
    * removes item from inventory using item name to search inventory
    * @param itemRemoved
    * @return - true if item was removed, false if it was not
    */
   public boolean removeItem(Item itemRemoved)
   {
      //TODO
      //I still don't know how to remove something from an array so this is an
      //equivalent work around for now. 
      int k,j;
      Item temp;
      for(k = 0; k < currentSize; k++)
      {
         if(itemRemoved.getName().equals(items[k].getName()))
         {   
            if(!items[k].decreaseQuantity())
            {
               currentSize --;
               for(j = k; j < currentSize; j++)
               {
                  temp = items[j];
                  items[j] = items[j+1];
                  items[j+1] = temp;
               }
               return true;
            }  
         }   
      }
      return false;    
   }
   
   //returns the whole inventory as a string
   public String toString()
   {
      int k;
      String inventoryList = "\nMY INVENTORY";
      
      for(k = 0; k < currentSize; k++)
      {
         inventoryList += items[k].toString() + "\n";
      }
      return inventoryList;
   }
   
   public static void main(String[] args)
   {
      // CHANGE MAX_SIZE TO 2 WHEN RUNNING MAIN FOR TESTING
      System.out.println("Creating predefined items");
      Item itemOne = new Item("Sword", false, 1, 1, "test item one, non stackable");
      System.out.println(itemOne.toString());      
      Item itemTwo = new Item("Apple", true, 2, 3, "this item is stackable, yay");
      System.out.println(itemTwo.toString());
      Item itemThree = new Item("Shield", false, 1, 1, "a shield");
      
      System.out.println("\nAdd items to inventory");
      Inventory inventoryOne = new Inventory();
      inventoryOne.addItem(itemOne);
      inventoryOne.addItem(itemTwo);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nAdd a sword");
      inventoryOne.addItem(itemOne);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nAdd an apple");
      inventoryOne.addItem(itemTwo);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nAdd an apple over limit");
      inventoryOne.addItem(itemTwo);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nAdd an item over max limit");
      inventoryOne.addItem(itemThree);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nRemove sword");
      inventoryOne.removeItem(itemOne);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nRemove 1 apple");
      inventoryOne.removeItem(itemTwo);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nRemove 2 more apples");
      inventoryOne.removeItem(itemTwo);
      inventoryOne.removeItem(itemTwo);
      System.out.println(inventoryOne.toString());
      
      System.out.println("\nRemove sword which is not in inventory");
      inventoryOne.removeItem(itemOne);
      System.out.println(inventoryOne.toString());
         
   }

}
