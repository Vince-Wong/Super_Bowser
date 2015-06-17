package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class MobFollow extends Mob
{
   public MobFollow(String name, int spawnX, int spawnY)
          throws SlickException 
   {
      setName(name);
      setShape(new Rectangle(spawnX*SIZE+PADDING, spawnY*SIZE+PADDING, SIZE-PADDING*2, SIZE-PADDING*2));
      SpriteSheet[] mobSprites = new SpriteSheet[4];
      mobSprites[FWD] = new SpriteSheet("res/Boo_Sprites_R.png",SIZE,SIZE);
      mobSprites[BACK] = new SpriteSheet("res/Boo_Sprites_L.png",SIZE,SIZE);
      mobSprites[FWD_STILL] = new SpriteSheet("res/Boo_Sprites_R.png",SIZE,SIZE);
      mobSprites[BACK_STILL] = new SpriteSheet("res/Boo_Sprites_L.png",SIZE,SIZE);
      setSprites(mobSprites);
      
      Animation[] mobAnimations = new Animation[4];
      mobAnimations[FWD] = new Animation(mobSprites[FWD],250);
      mobAnimations[BACK] = new Animation(mobSprites[BACK],250);
      mobAnimations[FWD_STILL] = new Animation(mobSprites[FWD_STILL],250);
      mobAnimations[BACK_STILL] = new Animation(mobSprites[BACK_STILL],250);
      setAnimations(mobAnimations);
      
      setCurrentAnim(FWD_STILL);
      setInventory(new Inventory());
      setDamage(1);
      faceRight();
   }
   
   // default movement of the mob: move toward bowser
   // After Mobs are instantiated and placed in an ArrayList
   // need to call in a loop within WorldTemplate
   public void move() {
      int direction;
      if (getX() != WorldTemplate.bowser.getX() && getY() != WorldTemplate.bowser.getY()) {
         // randomly move vertically or horizontally towards Bowser
         if (Math.random() > 0.5) {
            direction = WorldTemplate.bowser.getX() - getX();
            moveHorizontal(direction/Math.abs(direction));
            if (direction > 0) {
               setCurrentAnim(FWD);
            }
            else {
               setCurrentAnim(BACK);
            }
         }
         else {
            direction = WorldTemplate.bowser.getY() - getY();
            moveVertical(direction/Math.abs(direction));
         }
      }
      else if (getX() != WorldTemplate.bowser.getX()) {
         direction = WorldTemplate.bowser.getX() - getX();
         moveHorizontal(direction/Math.abs(direction));
         if (direction > 0) {
            setCurrentAnim(FWD);
         }
         else {
            setCurrentAnim(BACK);
         }
      }
      else if (getY() != WorldTemplate.bowser.getY()) {
         direction = WorldTemplate.bowser.getY() - getY();
         moveVertical(direction/Math.abs(direction));
      }
   }

   //for use later in a Mob subclass, x0 and x1 will be set by the constructor
   final int x0 = 0;
   final int x1 = 5;
   public void moveLR() {
      switch (getX()) {
         case x0:
            moveHorizontal(1);
            break;
         case x1:
            moveHorizontal(-1);
            break;
         default:
            if (Math.random() > 0.5) {
               moveHorizontal(1);
            }
            else {
               moveHorizontal(-1);
            }
            break;
      }
   }

   // for use later in another mob subclass
   final int y0 = 0;
   final int y1 = 5;
   public void moveUD() {
      switch (getY()) {
         case y0:
            moveVertical(1);
            break;
         case y1:
            moveVertical(-1);
            break;
         default:
            if (Math.random() > 0.5) {
               moveVertical(1);
            }
            else {
               moveVertical(-1);
            }
            break;
      }
   }
}
