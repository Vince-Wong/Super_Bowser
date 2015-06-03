package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public abstract class Character extends Entity
{
   protected static final int FWD = 0;
   protected static final int BACK = 1;
   protected static final int FWD_STILL = 2;
   protected static final int BACK_STILL = 3;
   /* container for all SpriteSheets, so far should have them in this order:
    * [fwdSheet, backSheet, fwdStill, backStill]  */
   private SpriteSheet[] sprites;
   /* container for all Animations, so far should have them in this order:
    * [fwdAnim, backAnim, fwdStill, backStill]    */
   private Animation[] animations;
   private Animation currentAnim;
   // facing right is true; facing left is false
   private boolean face;
   private int health;
   private Inventory inventory;
   
   public void setSprites(SpriteSheet[] mySprites) { sprites = mySprites; }
   
   public Animation getAnimation(int code) { return animations[code]; }
   public void setAnimations(Animation[] anim) { animations = anim; }
   
   public Animation getCurrentAnim() { return currentAnim; }
   public void setCurrentAnim(int next) { currentAnim = animations[next]; }
   
   public boolean getFace() { return face; }
   public void faceLeft() { face = false; }
   public void faceRight() { face = true; }
   
   public int getHealth() { return health; }
   public void setHealth(int myHealth) { health = myHealth; }
   public void changeHealth(int change) { health += change; }
   
   public Inventory getInventory() { return inventory; }
   public void setInventory(Inventory myInventory) { inventory = myInventory; }
   
   public void moveHorizontal(int pixels) { getShape().setX(getShape().getX() + pixels); }
   public void moveVertical(int pixels) { getShape().setY(getShape().getY() + pixels); }
   
   public float getX() { return getShape().getX(); }
   public void setX(float deltaX) { getShape().setX(deltaX); }
   public float getY() { return getShape().getY(); }
   public void setY(float deltaY) { getShape().setY(deltaY); }
}

class Inventory {}