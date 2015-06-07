package game_logic;

import org.newdawn.slick.geom.Rectangle;

public class Item extends Entity
{
   protected boolean stackable;
   protected int quantity;
   protected int maxQuantity;
   protected String description;
   
   public Item(String name, boolean stackable, int quantity, int maxQuantity, 
      String description)
   {
      this.name = name;
      this.stackable = stackable;
      this.description = description;
      this.quantity = quantity;
      this.maxQuantity = maxQuantity;
      setShape(new Rectangle(100, 100, 32, 32));   //TODO must fix later
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
   public boolean decreaseQuantity()
   {
      if(quantity > 1)
      {
         this.quantity --;
         return true;
      }
      else
         return false;    
   }
   
   @Override
   public void onCollision(Entity ent)
   {
      // TODO Auto-generated method stub
      String otherObject = ent.getClass().toString();
      System.out.println(otherObject);
      switch(otherObject)
      {
         case "class game_logic.Bowser":
         {
            Bowser myBow = (Bowser)ent;
            myBow.getInventory().addItem(this);
            break;
         }
//         case "class Mob":
//         {
//            Mob myMob = (Mob)ent;
//            myMob.getInventory().addItem(this);
//            break;
//         }
         default: break;
      } 
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
   
   public int getQuantity()
   {
      return quantity;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public String toString()
   {
      String details = "\nItem Name: " + this.name + "\nstackable: " + this.stackable 
         + "\nquantity: " + this.quantity + "\nmax Quantity: " + this.maxQuantity
         + "\ndescription: " + this.description;
      return details;
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
