package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class MobUD extends Mob
{
   final int y0, y1;

   public MobUD(String name, int spawnX, int spawnY, int rangeY) 
         throws SlickException
   {
      setName(name);
      setShape(new Rectangle(spawnX*SIZE, spawnY*SIZE, SIZE-PADDING*2, SIZE-PADDING*2));
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
      
      y0 = spawnY;
      y1 = rangeY;
   }
   
   public void move() {
      int y = getY();
      if (y == y0) {
         moveVertical(1);
      } 
      else if (y == y1) {
         moveVertical(-1);
      } 
      else {
         if (Math.random() > 0.5) {
            moveVertical(1);
         }
         else {
            moveVertical(-1);
         }
      }
      if (WorldTemplate.bowser.getX() > getX()) {
         setCurrentAnim(FWD);
      }
      else {
         setCurrentAnim(BACK);
      }
   }
}
