package game_logic;

import org.newdawn.slick.geom.Rectangle;

/**
 * Abstract entity class that defines boundaries for each 
 */
public abstract class Entity
{

   protected String name;
   protected Rectangle shape;
   
   public String getName() { return name; }
   public void setName(String myName) { name = myName; }
   
   public Rectangle getShape() { return shape; }
   public void setShape(Rectangle rect) { shape = rect; }
   
}
