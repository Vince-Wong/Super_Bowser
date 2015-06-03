package game_logic;

import java.awt.Rectangle;

public class Entity
{

   protected String name;
   protected Rectangle entityBox;
   
   public Entity(String name)
   {
      this.name = name;
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }

}
