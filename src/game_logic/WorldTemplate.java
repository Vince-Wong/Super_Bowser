package game_logic;

import java.util.ArrayList;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class WorldTemplate extends BasicGameState
{   
   private static final int DELAY = 200;
   private static final int LONG_DELAY = 750;
   protected static final int WINDOW_WIDTH = 800;
   protected static final int WINDOW_HEIGHT = 640;

   protected boolean quit = false;
   private long counter = 0;
   private long ticks = 0;

   protected static Bowser bowser;
   protected TiledMap map;
   protected int objectLayer;
   protected int itemsLayer;
   protected ArrayList<Item> items;
   protected ArrayList<Mob> mobs;

   public WorldTemplate(int State){}

   // make bowser at the beginning
   public void init(GameContainer gc, StateBasedGame sbg)
         throws SlickException
   {
      bowser = new Bowser();
   }

   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
         throws SlickException
   {
      // renders the map
      map.render(0, 0);
      g.drawString(WorldTemplate.bowser.toString(), 5, 5);
      //renders items
      for (Item thing : items)
      {
         g.drawImage(thing.getImage(), thing.getShape().getX(), thing.getShape().getY());
      }

      // renders Bowser
      bowser.getCurrentAnim().draw(bowser.getX() * Character.SIZE,
            bowser.getY() * Character.SIZE);

      // renders Mobs
      for (Mob toad : mobs) {
         toad.getCurrentAnim().draw(toad.getX() * Character.SIZE, 
               toad.getY() * Character.SIZE);
      }

      // renders the menu
      if(quit)
      {
         g.setColor(Color.red);
         g.fillRect(WINDOW_WIDTH/2 - 40, WINDOW_HEIGHT/2 - 140, 200, 200);

         g.setColor(Color.black);
         g.fillRect(WINDOW_WIDTH/2 - 30, WINDOW_HEIGHT/2 - 130, 180, 180);

         g.setColor(Color.white);
         g.drawString("Resume (R)", WINDOW_WIDTH/2, WINDOW_HEIGHT/2 - 100);
         g.drawString("Main Menu (M)", WINDOW_WIDTH/2, WINDOW_HEIGHT/2 - 50);
         g.drawString("Quit Game (Q)", WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
         if(!quit)
         {
            g.clear();
         }
      }
   }

   // based on input, update bowser's state
   public void update(GameContainer gc, StateBasedGame sbg, int delta)
         throws SlickException
   {
      Input input = gc.getInput();
      // Menu not open
      if (!quit) {
         readMoveInput(input, delta);
         ticks += delta;
         if (ticks >= LONG_DELAY) {
            for (Mob toad : mobs) {
               toad.move();
            }
            ticks = 0;
         }

         // Open Backpack
         if(input.isKeyPressed(Input.KEY_I)) 
         {
            sbg.enterState(9);
            Backpack.prevScreenID = getID();
         }

         // Open Menu
         if(input.isKeyDown(Input.KEY_ESCAPE))
         {
            quit = true;
         }

         checkDamage();
         // Check if Bowser dies
         if (bowser.getHealth() <= 0) {
            sbg.enterState(666);
         }
      }
      // Menu is open
      else {
         readMenuOption(input, sbg);
      }
      //for item collision as of right now
      if(items != null)
         detectCollision();
   }


   private void detectCollision() {
      int k;
      for (k = 0; k < items.size(); k++) {
         if (bowser.getShape().intersects(items.get(k).getShape())) 
         {
            items.get(k).onCollision(bowser);
            //Does this work in arraylists?
            if(!items.get(k).getOnScreen())
            {
               items.remove(k);
               System.out.println(bowser.getInventory().toString()); //TODO For testing
            }
         }
      }
   }

   private void readMenuOption(Input in, StateBasedGame sbg) {
      if(in.isKeyDown(Input.KEY_R))
      {
         quit = false;
      }
      else if(in.isKeyDown(Input.KEY_M))
      {
         sbg.enterState(0);
      }
      else if(in.isKeyDown(Input.KEY_Q))
      {
         System.exit(0);
      }
   }

   private void readMoveInput(Input in, long delta) {
      counter += delta;
      // if Bowser hasn't moved in the last DELAY milliseconds
      if (counter >= DELAY)
      {
         if(in.isKeyDown(Input.KEY_SPACE)) 
         {
            /* Bowser needs 750ms to complete attack animation during which he can't move
             * Fire Claw animation is 3 frames at 250ms per frame
             */
            counter = -550;
            if (bowser.getFace()) {
               bowser.setCurrentAnim(Bowser.FIRE_R);
            }
            else {
               bowser.setCurrentAnim(Bowser.FIRE_L);
            }
         }           
         // if input is UP
         else if(in.isKeyDown(Input.KEY_W))
         {
            // if the tile above Bowser's current position is the floor layer
            if(map.getTileId(bowser.getX(),bowser.getY()-1,objectLayer)==0)
            {
               // move Bowser and reset the counter
               counter = 0;
               if (bowser.getFace()) {
                  bowser.setCurrentAnim(Character.FWD);
               }
               else {
                  bowser.setCurrentAnim(Character.BACK);
               }
               bowser.moveVertical(-1);                  
            }         
         }
         else if(in.isKeyDown(Input.KEY_S))
         {
            if(map.getTileId(bowser.getX(),bowser.getY()+1,objectLayer)==0)
            {
               counter = 0;
               if (bowser.getFace()) {
                  bowser.setCurrentAnim(Character.FWD);
               }
               else {
                  bowser.setCurrentAnim(Character.BACK);
               }
               bowser.moveVertical(1);                  
            }
         }
         else if(in.isKeyDown(Input.KEY_A))
         {
            if(map.getTileId(bowser.getX()-1,bowser.getY(),objectLayer)==0)
            {   
               counter = 0;
               bowser.faceLeft();
               bowser.setCurrentAnim(Character.BACK);
               bowser.moveHorizontal(-1);                  
            }         
         }
         else if(in.isKeyDown(Input.KEY_D))
         {
            if(map.getTileId(bowser.getX()+1,bowser.getY(),objectLayer)==0)
            {   
               counter = 0;
               bowser.faceRight();
               bowser.setCurrentAnim(Character.FWD);
               bowser.moveHorizontal(1);                  
            }          
         }
         else {
            if (bowser.getFace()) {
               bowser.setCurrentAnim(Character.FWD_STILL);
            }
            else {
               bowser.setCurrentAnim(Character.BACK_STILL);
            }
         }
      }
   }

   private void checkDamage() {
      for (int i = 0; i < mobs.size(); i++) {
         Mob guy = mobs.get(i);
         if (bowser.getShape().intersects(guy.getShape())) {
            if (bowser.getCurrentAnim() == bowser.getAnimation(Bowser.FIRE_L)
                  || bowser.getCurrentAnim() == bowser.getAnimation(Bowser.FIRE_R)) {
               mobs.remove(i);
            }
            else {
               bowser.changeHealth(-guy.getDamage());
               // Bowser gets knocked back 1 tiles from the direction he is facing
               if (bowser.getFace()) {
                  bowser.moveHorizontal(-1);
               }
               else {
                  bowser.moveHorizontal(1);
               }
            }
         }
      }
   }

   public int getID() { return 10; }
}
