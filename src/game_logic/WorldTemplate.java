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
    private static final int DELAY = 250;
    protected static final int WINDOW_WIDTH = 800;
    protected static final int WINDOW_HEIGHT = 640;

    protected boolean quit = false;
    private long counter = 0;
    
    protected static Bowser bowser;
    protected TiledMap map;
    protected int objectLayer;
    protected int itemsLayer;

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
       // renders bowser
       g.fill(bowser.getShape()); 
       bowser.getCurrentAnim().draw(bowser.getX() * Character.SIZE,
                                     bowser.getY() * Character.SIZE);
    }
    // based on input, update bowser's state
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
       Input input = gc.getInput();
       readInput(input, delta);
       
//        bowser.getCurrentAnim().update(delta);          
//        // Input is the UP command
//        if(input.isKeyDown(Input.KEY_W))
//        {
//           if (bowser.getFace()) {
//              bowser.setCurrentAnim(Character.FWD);
//           }
//           else {
//              bowser.setCurrentAnim(Character.BACK);
//           }
//           bowser.setY(bowser.getY() - delta * .5f);
//        }
//        // Input is the DOWN command
//        if(input.isKeyDown(Input.KEY_S))
//        {
//           if (bowser.getFace()) {
//              bowser.setCurrentAnim(Character.FWD);
//           }
//           else {
//              bowser.setCurrentAnim(Character.BACK);
//           }
//           bowser.setY(bowser.getY() + delta * .5f);
//        }
//        // Input is the LEFT command
//        if(input.isKeyDown(Input.KEY_A))
//        {
//           bowser.faceLeft();
//           bowser.setCurrentAnim(Character.BACK);
//           bowser.setX(bowser.getX() - delta * .5f);
//        }
//        // Input is the RIGHT command
//       if(input.isKeyDown(Input.KEY_D))
//       {
//           bowser.faceRight();
//           bowser.setCurrentAnim(Character.FWD);
//           bowser.setX(bowser.getX() + delta * .5f);
//       }
//       // No input, but need to show bowser standing still
//       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
//          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
//          && bowser.getCurrentAnim() == bowser.getAnimation(Character.FWD))
//           bowser.setCurrentAnim(Character.FWD_STILL);
//       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
//          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
//          && bowser.getCurrentAnim() == bowser.getAnimation(Character.BACK))
//          bowser.setCurrentAnim(Character.BACK_STILL);
       
     //Inventory.
       if(input.isKeyPressed(Input.KEY_I)) 
       {
             sbg.enterState(9);
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
    }
    
    private void readInput(Input in, long delta) {
       counter += delta;
       if(map.getTileId(bowser.getX(),bowser.getY(),itemsLayer)==2)
       {   
          System.out.println("NEW ITEM!!");        
       }
       if(in.isKeyDown(Input.KEY_W))
       {
          if(map.getTileId(bowser.getX(),bowser.getY()-1,objectLayer)==0)
          {  
             if (counter >= DELAY) {
                counter = 0;
                bowser.moveVertical(-1);                  
             }
          }         
       }
       if(in.isKeyDown(Input.KEY_S))
       {
          if(map.getTileId(bowser.getX(),bowser.getY()+1,objectLayer)==0)
          {   
             if (counter >= DELAY) {
                counter = 0;
                bowser.moveVertical(1);                  
             }           
          }
       }
       if(in.isKeyDown(Input.KEY_A))
       {
          if(map.getTileId(bowser.getX()-1,bowser.getY(),objectLayer)==0)
          {   
             if (counter >= DELAY) {
                counter = 0;
                bowser.moveHorizontal(-1);                  
             }           
          }         
       }
       if(in.isKeyDown(Input.KEY_D))
       {
          if(map.getTileId(bowser.getX()+1,bowser.getY(),objectLayer)==0)
          {   
             if (counter >= DELAY) {
                counter = 0;
                bowser.moveHorizontal(1);                  
             }            
          }          
       }
    }
    
    public int getID() { return 1; }
  
}