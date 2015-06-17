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
       map = new TiledMap("res/worldOneMap.tmx");
       objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       
       mobs = new ArrayList<>();
       mobs.add(new MobFollow("testBoo", 3, 9));
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) 
           throws SlickException
    {
       super.update(gc, sbg, delta);
   
       //Bowser enters second map if he exits the first map
       if(bowser.getX()==24 && bowser.getY()==11)
       {
          sbg.enterState(2);
          WorldTemplate.bowser.setX(1);
          WorldTemplate.bowser.setY(10);
       }
    }    
     
    public int getID() { return 1; }
  
}
