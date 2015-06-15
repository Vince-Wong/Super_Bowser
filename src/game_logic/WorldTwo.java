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

public class WorldTwo extends WorldTemplate
{   
    public WorldTwo(int state){
       super(state);
    }
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       //tiled map with 3 layers: background, flowers, and buildings
       map = new TiledMap("res/worldTwoMap.tmx");
       objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
       map.render(0,0);
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

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
       int objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       Input input = gc.getInput();
        
     
          // Menu not open
          if (!quit) {
             readMoveInput(input, delta);
          }
          // Menu is open
          else {
             readMenuOption(input, sbg);
          }
          
          // Open Backpack
          if(input.isKeyPressed(Input.KEY_I)) 
          {
             sbg.enterState(9);
          }
          
          // Open Menu
          if(input.isKeyDown(Input.KEY_ESCAPE))
          {
             quit = true;
          }
          if(WorldTemplate.bowser.getX()==12 && WorldTemplate.bowser.getY()==20)
          {
             WorldTemplate.bowser.setX(8);
             WorldTemplate.bowser.setY(8); 
             sbg.enterState(3);  
          }
    }    

    public int getID() { return 1; }
  
}
