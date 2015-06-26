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

public class Room extends WorldTemplate
{   
    public Room(int state){
       super(state);
    }
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {

       map = new TiledMap("res/RoomMap.tmx");
       objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);

       mobs = new ArrayList<>();
       
       items = new ArrayList<>();
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) 
           throws SlickException
    {
       super.update(gc, sbg, delta);
       BasicGameState prevState = Game.prevState;
       //Bowser enters previous world
       if(bowser.getX()==12 && bowser.getY()==19)
       {
         System.out.println("ID: "+prevState.getID());
          if(prevState.getID()==2)
          {
             sbg.enterState(prevState.getID());
             WorldTemplate.bowser.setX(6);
             WorldTemplate.bowser.setY(6);
          }
          if(prevState.getID()==3)
          { sbg.enterState(prevState.getID());
             WorldTemplate.bowser.setX(11);
             WorldTemplate.bowser.setY(3);
          }
       }    
    }
    public int getID() { return 7; }
  
}
