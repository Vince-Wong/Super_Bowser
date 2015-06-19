package game_logic;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public class Item extends Entity
{
   protected boolean stackable;
   protected int quantity;
   protected int maxQuantity;
   protected String description;
   protected boolean onScreen;
   public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32;
   protected Image image;
   
   public Item(String name, boolean stackable, int quantity, int maxQuantity, 
      String description)
   {
      this.name = name;
      this.stackable = stackable;
      this.description = description;
      this.quantity = quantity;
      this.maxQuantity = maxQuantity;
      this.onScreen = true;
      setShape(new Rectangle(64, 32*6, ITEM_WIDTH, ITEM_HEIGHT));   //TODO must fix later
   }
   
   /**
    * Constructor that takes all parameters to build item
    * @param name
    * @param stackable
    * @param quantity
    * @param maxQuantity
    * @param description
    * @param spawnTileX - X coordinate of map tile
    * @param spawnTileY - Y coordinate of map tile
    * @param itemImage - location of item image
    * @throws SlickException
    */
   public Item(String name, boolean stackable, int quantity, int maxQuantity, 
      String description, int spawnTileX, int spawnTileY, String itemImage) 
      throws SlickException
   {
      this(name, stackable, quantity, maxQuantity, description);
      this.getShape().setX(spawnTileX*32);
      this.getShape().setY(spawnTileY*32);
      this.image = new Image(itemImage);
   }
   

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
   
   public void onCollision(Entity ent)
   {
      Bowser myBow = (Bowser)ent;
      if(myBow.getInventory().addItem(this))
         this.setOnScreen(false);
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
   
   public int getQuantity()
   {
      return quantity;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public boolean getOnScreen() { return onScreen; }
   public void setOnScreen(Boolean state) { onScreen = state;}
   public Image getImage() {
      return image;
   }
   
   public void setImage(Image im) {
      image = im;
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
