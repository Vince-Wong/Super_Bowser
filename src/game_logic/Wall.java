package game_logic;

import org.newdawn.slick.geom.Rectangle;

public class Wall extends Entity
{
   public Wall(String name, int x, int y, int width, int length) {
      setName(name);
      setShape(new Rectangle(x, y, width, length));
   }

   @Override
   public void onCollision(Entity ent)
   {
      // TODO Auto-generated method stub

   }

}
