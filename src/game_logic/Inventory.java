package game_logic;

public class Inventory
{
   public static final int MAX_SIZE = 21, MIN_SIZE = 0;
   public int currentSize;
   private Item[] items;
   
   public Inventory()
   {
      this.currentSize = 0;
      this.items = new Item[MAX_SIZE + 1];
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
    * overloaded version of removeItem which takes an Item as a parameter 
    * @param itemRemoved
    * @return - true if item was removed, false if it was not
    */
   public boolean removeItem(Item itemRemoved)
   {
      String itemName = itemRemoved.getName();
      return this.removeItem(itemName);
   }
   
   /**
    * removes item from inventory using item name to search inventory
    * @param itemName
    * @return
    */
   public boolean removeItem(String itemName)
   {
      int k,j;
      Item temp;
      for(k = 0; k < currentSize; k++)
      {
         if(itemName.equals(items[k].getName()))
         {   
            if(!items[k].decreaesQuantity())
            {
               for(j = k; j < currentSize; j++)
               {
                  temp = items[j];
                  items[j] = items[j+1];
                  items[j+1] = temp;
               }
               currentSize --;
               return true;
            }  
         }   
      }
      return false;    
   }
   
   public Item[] getItems() {
      return items;
   }
   
   public Item getIndexItem(int index)
   {
      return items[index];
   }
   
   public int getCurrentSize()
   {
      return currentSize;
   }
   
   public Item findItem(String itemName)
   {
      int k;
      for(k = 0; k < currentSize; k++)
         if(itemName.equals(items[k].getName()))
            return items[k]; 
      return null;
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
      //Vincent Use case test
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
