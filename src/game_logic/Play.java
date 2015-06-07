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
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Play extends BasicGameState
{   
//    private Image Map;
    private boolean quit = false;
    protected static Bowser bowser;
    protected boolean debug = false;
    protected ArrayList<Entity> entities;
    //window size 1000, 750
    protected final int WINDOW_WIDTH = 1000;
    protected final int WINDOW_HEIGHT = 750;
    
    
    public Play(int State){}
    
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       bowser = new Bowser();
     //TEMP MAP

//     Map = new Image("res/TempBackground.jpg");
       
       entities = new ArrayList<>();
       

       //sets the max frames per second
       int maxFPS = 60;
       gc.setTargetFrameRate(maxFPS);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
       //g.drawImage(Map,0,0);

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
    
       if (debug) {
          g.setColor(Color.red);
          g.fill(bowser.getShape());
       }
          
       //TODO draw all entities and remove them?
       if(!(entities == null))
       for (Entity thing : entities)
       {
          g.fill(thing.getShape());
       }
       
       // renders bowser
       bowser.getCurrentAnim().draw(bowser.getX()-Bowser.PADDING,
                                     bowser.getY()-Bowser.PADDING);
    }
    // based on input, update bowser's state
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
        bowser.getCurrentAnim().update(delta);
        bowser.setPrevXY();
        Input input = gc.getInput();
        //EVERYTHING BELOW THIS IS HANDLING USER INPUT EXCEPT THE LAST LINE
        // Input is the UP command
        if(input.isKeyDown(Input.KEY_W))
        {
           if (bowser.getFace()) {
              bowser.setCurrentAnim(Character.FWD);
           }
           else {
              bowser.setCurrentAnim(Character.BACK);
           }
           bowser.setY(bowser.getY() - delta * .25f);
        }
        // Input is the DOWN command
        if(input.isKeyDown(Input.KEY_S))
        {
           if (bowser.getFace()) {
              bowser.setCurrentAnim(Character.FWD);
           }
           else {
              bowser.setCurrentAnim(Character.BACK);
           }
           bowser.setY(bowser.getY() + delta * .25f);
        }
        // Input is the LEFT command
        if(input.isKeyDown(Input.KEY_A))
        {
           bowser.faceLeft();
           bowser.setCurrentAnim(Character.BACK);
           bowser.setX(bowser.getX() - delta * .25f);
        }
        // Input is the RIGHT command
       if(input.isKeyDown(Input.KEY_D))
       {
           bowser.faceRight();
           bowser.setCurrentAnim(Character.FWD);
           bowser.setX(bowser.getX() + delta * .25f);
       }
       // No input, but need to show bowser standing still
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && bowser.getCurrentAnim() == bowser.getAnimation(Character.FWD))
           bowser.setCurrentAnim(Character.FWD_STILL);
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && bowser.getCurrentAnim() == bowser.getAnimation(Character.BACK))
          bowser.setCurrentAnim(Character.BACK_STILL);
       
     //Inventory.
       if(input.isKeyPressed(Input.KEY_I)) 
       {
          sbg.enterState(9, new FadeOutTransition(), new FadeInTransition());
          sbg.enterState(Game.backpack);
       }
       
       ///// menu  //////////////
       //escape 
       if(input.isKeyDown(Input.KEY_ESCAPE))
       {
          quit = true;
       }

       //when menu is up
       if(quit)
       {
          if(input.isKeyDown(Input.KEY_R))
          {
             quit = false;
          }
          if(input.isKeyDown(Input.KEY_M))
          {
             sbg.enterState(0);
          }
          if(input.isKeyDown(Input.KEY_Q))
          {
             System.exit(0);
          }
       }
       if(input.isKeyPressed(Input.KEY_F11)) {
          // Spawn location is set here for the stage transition!
          bowser.setX(40);
          bowser.setY(40);
          sbg.enterState(Game.test01);
          System.out.println("go to test map");
       }
       
       if (input.isKeyPressed(Input.KEY_F12)) {
          debug = !debug;
          System.out.println("debug mode");
       }              
       //EVERYTHING ABOVE THIS IS HANDLING USER INPUT
       detectCollision();
    }
    
//    private void detectCollision() {
//       for (Entity thing : entities) {
//          if (bowser.getShape().intersects(thing.getShape())) {
//             thing.onCollision(bowser);
//          }
//       }
//    }
    
    //modified collision method to account for item removal
    private void detectCollision() {
       int k;
       for (k = 0; k < entities.size(); k++) {
          if (bowser.getShape().intersects(entities.get(k).getShape())) {
             entities.get(k).onCollision(bowser);
          //Does this work in arraylists?
          if(!entities.get(k).getOnScreen())
          {
             entities.remove(k);
             System.out.println(bowser.getInventory().toString()); //TODO For testing
          }
          }
       }
    }
    
    public int getID() { return 1; }
  
}