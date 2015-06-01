package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public abstract class Character extends Entity
{
   private SpriteSheet sprites;
   private Animation animation;
   
   public SpriteSheet getSprites() { return sprites; }
   public void setSprites(SpriteSheet mySprites) { sprites = mySprites; }
   
   public Animation getAnimation() { return animation; }
   public void setAnimation(Animation anim) { animation = anim; }
}
