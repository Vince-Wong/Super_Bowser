package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Bowser extends Character
{
   private static final int START_HEALTH = 10;
   protected static final int SIZE = 32;
   protected static final int PADDING = 5;
   
   public Bowser() throws SlickException {
      setName("Bowser");
      /* Assuming Bowser's picture is roughly 77 px x 75 px, making his logical
       * shape a little bit smaller due to whitespace around sprite.
       * Estimated this visually, giving the shape 13px padding on all sides
       */
      setShape(new Rectangle(PADDING, PADDING, SIZE-PADDING*2, SIZE-PADDING*2));
      
      SpriteSheet[] bowserSprites = new SpriteSheet[4];
      bowserSprites[FWD] = new SpriteSheet("res/Bowser Walks.png",32,32);
      bowserSprites[BACK] = new SpriteSheet("res/Bowser Walks Back.png",32,32);
      bowserSprites[FWD_STILL] = new SpriteSheet("res/bowserStill.png",32,32);
      bowserSprites[BACK_STILL] = new SpriteSheet("res/bowserBackStill.png",32,32);
      setSprites(bowserSprites);
      
      Animation[] bowserAnimations = new Animation[4];
      bowserAnimations[FWD] = new Animation(bowserSprites[FWD],250);
      bowserAnimations[BACK] = new Animation(bowserSprites[BACK],250);
      bowserAnimations[FWD_STILL] = new Animation(bowserSprites[FWD_STILL],250);
      bowserAnimations[BACK_STILL] = new Animation(bowserSprites[BACK_STILL],250);
      setAnimations(bowserAnimations);
      
      setCurrentAnim(FWD_STILL);
      setHealth(START_HEALTH);
      setInventory(new Inventory());
      faceRight();
      setOnScreen(true);
   }
   
   public void onCollision(Mob enemy) {
      //TODO
   }
   
   public void onCollision(Item thing) {
      //TODO
   }
   
   public void onCollision(Door entrance) {
      //TODO
   }

   @Override
   public void onCollision(Entity ent)
   {
      // TODO Auto-generated method stub
      
   }
}

//TODO Classes not made yet, declared as non-public class here to avoid red lines 
class Mob {}

class Door {}