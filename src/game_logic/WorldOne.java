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
       
       mobs = new ArrayList<>();
       mobs.add(new MobFollow("testBoo", 16, 3));
       mobs.add(new MobLR("testYToad", 14, 13, 17));
       mobs.add(new MobUD("testYToad2", 21, 9, 18));
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
       map.render(0,0);
       for (Mob toad : mobs) {
          toad.getCurrentAnim().draw(toad.getX() * Character.SIZE, 
                                     toad.getY() * Character.SIZE);
       }
       super.render(gc, sbg, g);
    }    
     
    public int getID() { return 3; }
  
}