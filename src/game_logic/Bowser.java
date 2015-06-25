package game_logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Bowser extends Character
{
   public static final int START_HEALTH = 10;
   protected static final int PADDING = 2;
   protected static final int FIRE_R = 4;
   protected static final int FIRE_L = 5;
   
   public Bowser() throws SlickException {
      setName("Bowser");
      
      setShape(new Rectangle(PADDING, PADDING, SIZE-PADDING*2, SIZE-PADDING*2));
      SpriteSheet[] bowserSprites = new SpriteSheet[6];
      bowserSprites[FWD] = new SpriteSheet("res/Bowser Walks.png",SIZE,SIZE);
      bowserSprites[BACK] = new SpriteSheet("res/Bowser Walks Back.png",SIZE,SIZE);
      bowserSprites[FWD_STILL] = new SpriteSheet("res/bowserStill.png",SIZE,SIZE);
      bowserSprites[BACK_STILL] = new SpriteSheet("res/bowserBackStill.png",SIZE,SIZE);
      bowserSprites[FIRE_R] = new SpriteSheet("res/Bowser FireClaw2.png",SIZE,SIZE);
      bowserSprites[FIRE_L] = new SpriteSheet("res/Bowser FireClaw2 L.png",SIZE,SIZE);      
      setSprites(bowserSprites);
      
      Animation[] bowserAnimations = new Animation[6];
      bowserAnimations[FWD] = new Animation(bowserSprites[FWD],250);
      bowserAnimations[BACK] = new Animation(bowserSprites[BACK],250);
      bowserAnimations[FWD_STILL] = new Animation(bowserSprites[FWD_STILL],250);
      bowserAnimations[BACK_STILL] = new Animation(bowserSprites[BACK_STILL],250);
      bowserAnimations[FIRE_R] = new Animation(bowserSprites[FIRE_R],250);
      bowserAnimations[FIRE_L] = new Animation(bowserSprites[FIRE_L],250);
      setAnimations(bowserAnimations);
      
      setCurrentAnim(FWD_STILL);
      setHealth(START_HEALTH);
      setInventory(new Inventory());
      faceRight();
   }
   
   public String toString() {
      return "Name: " + getName() + "\nHealth: " + getHealth(); 
   }
}
