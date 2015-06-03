package game_logic;

public class Inventory
{

   public static final int MAX_SIZE = 30, MIN_SIZE = 0;
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
      if(currentSize <= MAX_SIZE)
      {
         for(k = 0; k < items.length; k++)
         {
            if(newItem.getName().equals(items[k].getItem()))
               {
                  if(items[k].increaseQuantity())
                  {
                     currentSize ++;
                     return true;
                  }
                  //maybe put in a flag for inventory is full?
                  return false;
               }
            else
               continue;
         }
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
   
   public boolean removeItem(Item itemRemoved)
   {
      //TODO
      //I still don't know how to remove something from an array so this is an
      //equivalent work around for now. 
      int k,j;
      Item temp;
      for(k = 0; k < items.length; k++)
      {
         if(itemRemoved.getName().equals(items[k].getItem()))
         {   
            if(!items[k].decreaesQuantity())
            {
               for(j = k; j <= currentSize; j++)
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
   
   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }

}
