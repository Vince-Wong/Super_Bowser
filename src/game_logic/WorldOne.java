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
import org.newdawn.slick.tiled.TiledMap;

public class WorldOne extends BasicGameState
{   
    //private Image Map;
    boolean quit = false;
    private TiledMap map;
    private Bowser bowser;
    public WorldOne(int State){}
    int x =5;
    int y =5;
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       //tiled map with 3 layers: background, flowers, and buildings
       map = new TiledMap("res/worldOneMap.tmx");
      
       //sets the max frames per second
       int maxFPS = 60;
       gc.setTargetFrameRate(maxFPS);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
       map.render(0,0);
       
       g.fillRect(x*32,y*32,32,32);
       
       
    }
    // based on input, update bowser's state
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
       int objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       int itemsLayer = map.getLayerIndex("Items");
       map.getTileId(0,0, itemsLayer);
       
        // Input is the UP command
       if(map.getTileId(x,y,itemsLayer)==2)
       {   
           System.out.println("NEW ITEM!!");        
       }
        if(gc.getInput().isKeyPressed(Input.KEY_W))
        {
           if(map.getTileId(x,y-1,objectLayer)==0)
           {  
              y--;                     
           }         
        }
        if(gc.getInput().isKeyPressed(Input.KEY_S))
        {
           if(map.getTileId(x,y+1,objectLayer)==0)
           {   
              y++;              
           }
        }
        if(gc.getInput().isKeyPressed(Input.KEY_A))
        {
           if(map.getTileId(x-1,y,objectLayer)==0)
           {   
              x--;             
           }         
        }
        if(gc.getInput().isKeyPressed(Input.KEY_D))
        {
           if(map.getTileId(x+1,y,objectLayer)==0)
           {   
              x++;            
           }          
        }
    } 
    public int getID() { return 3; }
  
}