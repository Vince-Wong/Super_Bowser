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

public class WorldOne extends WorldTemplate
{   
    public WorldOne(int state){
       super(state);
    }
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       //tiled map with 3 layers: background, flowers, and buildings
       map = new TiledMap("res/worldOneMap.tmx");
       objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       itemsLayer = map.getLayerIndex("Items");
       map.getTileId(0,0, itemsLayer);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
       map.render(0,0);
<<<<<<< HEAD
       
       g.fillRect(x*32,y*32,32,32);
       
       
    }
    // based on input, update bowser's state
    private long counter = 0;
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
       int objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       int itemsLayer = map.getLayerIndex("Items");
       map.getTileId(0,0, itemsLayer);
       
       Input input = gc.getInput();
       
       counter+=delta;
       if(counter>=5000)
       {
     
           if(input.isKeyDown(Input.KEY_W))
           {
              if(map.getTileId(x,y-1,objectLayer)==0)
              {  
                 y--;                     
              }         
           }
           if(input.isKeyDown(Input.KEY_S))
           {
              if(map.getTileId(x,y+1,objectLayer)==0)
              {   
                 y++;              
              }
           }
           if(input.isKeyDown(Input.KEY_A))
           {
              if(map.getTileId(x-1,y,objectLayer)==0)
              {   
                 x--;             
              }         
           }
           if(input.isKeyDown(Input.KEY_D))
           {
              if(map.getTileId(x+1,y,objectLayer)==0)
              {   
                 x++;            
              }          
           }
           //Entrance for house and changes state to Play() for now. This will be 
           //entering a new state that represents the room.
           if(x==11 && y==7)
           {   
              sbg.enterState(4);
           }
          counter = 0;
       }
       
                               
    } 
=======
       super.render(gc, sbg, g);
    }    
     
>>>>>>> branch 'Brians_Branch' of https://github.com/FH-CS40A/project02_team02.git
    public int getID() { return 3; }
  
}
