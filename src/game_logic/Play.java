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

public class Play extends BasicGameState
{   
    //private Image Map;
    private boolean quit = false;
    protected static Bowser bowser;
    protected boolean debug = false;
    
    public Play(int State){}
    
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       bowser = new Bowser();
     //TEMP MAP
       //Map = new Image("res/TempBackground.jpg");

       //sets the max frames per second
       int maxFPS = 60;
       gc.setTargetFrameRate(maxFPS);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
     //window size 1000, 750
       int width = 1000;
       int height = 750;

       //g.drawImage(Map,0,0);

       // renders the menu
       if(quit)
       {
          g.setColor(Color.red);
          g.fillRect(width/2 - 40, height/2 - 140, 200, 200);

          g.setColor(Color.black);
          g.fillRect(width/2 - 30, height/2 - 130, 180, 180);


          g.setColor(Color.white);
          g.drawString("Resume (R)", width/2, height/2 - 100);
          g.drawString("Main Menu (M)", width/2, height/2 - 50);
          g.drawString("Quit Game (Q)", width/2, height/2);
          if(!quit)
          {
             g.clear();
          }
       }
    // renders bowser
       bowser.getCurrentAnim().draw(bowser.getX()-Bowser.PADDING,
                                     bowser.getY()-Bowser.PADDING);
       if (debug) {
          g.setColor(Color.red);
          g.fill(bowser.getShape()); 
       }
    }
    // based on input, update bowser's state
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
        bowser.getCurrentAnim().update(delta);
        Input input = gc.getInput();
        // Input is the UP command
        if(input.isKeyDown(Input.KEY_W))
        {
           if (bowser.getFace()) {
              bowser.setCurrentAnim(Character.FWD);
           }
           else {
              bowser.setCurrentAnim(Character.BACK);
           }
           bowser.setY(bowser.getY() - delta * .5f);
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
           bowser.setY(bowser.getY() + delta * .5f);
        }
        // Input is the LEFT command
        if(input.isKeyDown(Input.KEY_A))
        {
           bowser.faceLeft();
           bowser.setCurrentAnim(Character.BACK);
           bowser.setX(bowser.getX() - delta * .5f);
        }
        // Input is the RIGHT command
       if(input.isKeyDown(Input.KEY_D))
       {
           bowser.faceRight();
           bowser.setCurrentAnim(Character.FWD);
           bowser.setX(bowser.getX() + delta * .5f);
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
    }
    
    public int getID() { return 1; }
  
}