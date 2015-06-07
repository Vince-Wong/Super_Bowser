package game_logic;

import org.newdawn.slick.geom.Rectangle;

/**
 * Abstract entity class that defines boundaries for each 
 */
public abstract class Entity
{

   protected String name;
   protected Rectangle shape;
   //TODO remove = true, default true for testing only
   protected boolean onScreen = true; 
   
 	public abstract void onCollision(Entity ent);
   
   public String getName() { return name; }
   public void setName(String myName) { name = myName; }
   
   public Rectangle getShape() { return shape; }
   public void setShape(Rectangle rect) { shape = rect; }
   
   public boolean getOnScreen() { return onScreen; }
   public void setOnScreen(Boolean state) { onScreen = state;}
   
   
}
