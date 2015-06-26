package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class MobLR extends Mob
{
   final int x0, x1;
   
   public MobLR(String name, int spawnX, int spawnY, int rangeX, boolean overload) 
   {
      setName(name);
      setShape(new Rectangle(spawnX*SIZE+PADDING, spawnY*SIZE+PADDING, SIZE-PADDING*2, SIZE-PADDING*2));
      setInventory(new Inventory());
      setDamage(2);
      faceRight();
      
      x0 = spawnX;
      x1 = rangeX;
   }

   public MobLR(String name, int spawnX, int spawnY, int rangeX) 
         throws SlickException
   {
      setName(name);
      setShape(new Rectangle(spawnX*SIZE+PADDING, spawnY*SIZE+PADDING, SIZE-PADDING*2, SIZE-PADDING*2));
      SpriteSheet[] mobSprites = new SpriteSheet[4];
      mobSprites[FWD] = new SpriteSheet("res/Toad_Yellow_R.png",SIZE,SIZE);
      mobSprites[BACK] = new SpriteSheet("res/Toad_Yellow_L.png",SIZE,SIZE);
      mobSprites[FWD_STILL] = new SpriteSheet("res/Toad_Yellow_R.png",SIZE,SIZE);
      mobSprites[BACK_STILL] = new SpriteSheet("res/Toad_Yellow_L.png",SIZE,SIZE);
      setSprites(mobSprites);
      
      Animation[] mobAnimations = new Animation[4];
      mobAnimations[FWD] = new Animation(mobSprites[FWD],250);
      mobAnimations[BACK] = new Animation(mobSprites[BACK],250);
      mobAnimations[FWD_STILL] = new Animation(mobSprites[FWD_STILL],250);
      mobAnimations[BACK_STILL] = new Animation(mobSprites[BACK_STILL],250);
      setAnimations(mobAnimations);
      
      setCurrentAnim(FWD_STILL);
      setInventory(new Inventory());
      setDamage(3);
      faceRight();
      
      x0 = spawnX;
      x1 = rangeX;
   }
   
   public void move(boolean overload) {
      int x = getX();
      if (x == x0) {
         moveHorizontal(1);
      } 
      else if (x == x1) {
         moveHorizontal(-1);
      } 
      else {
         if (Math.random() > 0.5) {
            moveHorizontal(1);
         }
         else {
            moveHorizontal(-1);
         }
      }
   }
   
   public void move() {
      int x = getX();
      if (x == x0) {
         moveHorizontal(1);
         setCurrentAnim(FWD);
      } 
      else if (x == x1) {
         moveHorizontal(-1);
         setCurrentAnim(BACK);
      } 
      else {
         if (Math.random() > 0.5) {
            moveHorizontal(1);
            setCurrentAnim(FWD);
         }
         else {
            moveHorizontal(-1);
            setCurrentAnim(BACK);
         }
      }
   }
}
