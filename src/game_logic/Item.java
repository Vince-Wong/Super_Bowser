package game_logic;

public class Item extends Entity
{
   protected boolean stackable;
   protected int quantity;
   protected int maxQuantity;
   protected String description;
   
   public Item(boolean stackable, int quantity, int maxQuantity, String description)
   {
      this.stackable = stackable;
      this.description = description;
      this.quantity = quantity;
      this.maxQuantity = maxQuantity;
   }
   
   
   //Do we need this method???
   /**
    * retrieves the item object
    * @return item object
    */
   public Item getItem()
   {
      return this;
   }
   
   /**
    * increase item quantity if stackable
    */
   public boolean increaseQuantity()
   {
      if(stackable == true && quantity < maxQuantity)
      {
         this.quantity ++;
         return true;
      }
      else
         return false;
   }
   
   /**
    * decrease item quantity if stackable
    */
   public boolean decreaesQuantity()
   {
      if(quantity > 0)
      {
         this.quantity --;
         return true;
      }
      else
         return false;    
   }
   
   public String toString()
   {
      String details = "\nstackable: " + this.stackable 
         + "\nquantity: " + this.quantity + "\nmax Quantity: " + this.maxQuantity
         + "\ndescription: " + this.description;
      return details;
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public boolean isStackable()
   {
      return this.stackable;
   }
   
   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      
      System.out.println("Non-stackable item test");
      Item itemOne = new Item(false, 1, 10, "test item one");
      System.out.println(itemOne.toString());
      itemOne.increaseQuantity();
      System.out.println(itemOne.toString());
      
      System.out.println("Stackable item test");
      Item itemTwo = new Item(true, 1, 3, "this item is stackable, yay");
      System.out.println(itemTwo.toString());
      itemTwo.increaseQuantity();
      itemTwo.increaseQuantity();
      System.out.println(itemTwo.toString());
      itemTwo.increaseQuantity();
      System.out.println(itemTwo.toString());

   }
   
   
}
