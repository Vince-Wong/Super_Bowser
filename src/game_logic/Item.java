package game_logic;

public class Item extends Entity
{
   protected boolean stackable;
   protected int quantity;
   protected int maxQuantity;
   protected String description;
   
   public Item(String name, boolean stackable, int quantity, int maxQuantity, String description)
   {
      super(name);
      this.stackable = stackable;
      this.description = description;
      this.quantity = quantity;
      this.maxQuantity = maxQuantity;
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
    * decrease item quantity
    */
   public boolean decreaesQuantity()
   {
      if(quantity > 1)
      {
         this.quantity --;
         return true;
      }
      else
         return false;    
   }
   
   public String toString()
   {
      String details = "\nItem Name: " + this.name + "\nstackable: " + this.stackable 
         + "\nquantity: " + this.quantity + "\nmax Quantity: " + this.maxQuantity
         + "\ndescription: " + this.description;
      return details;
   }
   
   public boolean isStackable()
   {
      return this.stackable;
   }
   
   //Do we need this method???
   public Item getItem()
   {
      return this;
   }
   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      
      System.out.println("Non-stackable item test");
      Item itemOne = new Item("item one", false, 1, 10, "test item one");
      System.out.println(itemOne.toString());
      itemOne.increaseQuantity();
      System.out.println(itemOne.toString());
      
      System.out.println("Stackable item test");
      Item itemTwo = new Item("BF Sword", true, 1, 3, "this item is stackable, yay");
      System.out.println(itemTwo.toString());
      itemTwo.increaseQuantity();
      itemTwo.increaseQuantity();
      System.out.println(itemTwo.toString());
      itemTwo.increaseQuantity();
      System.out.println(itemTwo.toString());

   }
   
   
}
