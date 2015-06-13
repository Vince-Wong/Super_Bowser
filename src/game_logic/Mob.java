package game_logic;

import org.newdawn.slick.Animation;

public interface Mob
{
   public void move();

   public Animation getCurrentAnim();

   public int getX();

   public int getY();
}
