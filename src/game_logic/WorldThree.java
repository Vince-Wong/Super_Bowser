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

public class WorldThree extends WorldTemplate
{   
    public WorldThree(int state){
       super(state);
    }
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {

       map = new TiledMap("res/worldThree.tmx");
       objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       
       mobs = new ArrayList<>();      
       items = new ArrayList<>();
    }    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) 
           throws SlickException
    {
       super.update(gc, sbg, delta);
       Game.prevState = this;
	    //Bowser enters previous world
       if(bowser.getX()==0 && bowser.getY()==3)
       {        
          sbg.enterState(2);
          WorldTemplate.bowser.setX(23);
          WorldTemplate.bowser.setY(5);
       }
       //Bowser enters next world
       if(bowser.getX()==23 && bowser.getY()==0)
       {        
          sbg.enterState(4);
          WorldTemplate.bowser.setX(1);
          WorldTemplate.bowser.setY(10);
       }
       //bowser enters a room when over a door
       if(((bowser.getX()==3 ||bowser.getX()==6||bowser.getX()==9
         ||bowser.getX()==13||bowser.getX()==16||bowser.getX()==18||bowser.getX()==21)
         && bowser.getY()==2)||
         (bowser.getX()==1 && bowser.getY()==7)||
         (bowser.getX()==18 && bowser.getY()==6)||
         (bowser.getX()==23 && bowser.getY()==9)||
         (bowser.getX()==19 && bowser.getY()==14)||
         ((bowser.getX()==8||bowser.getX()==10||bowser.getX()==12)&&bowser.getY()==10)||
         ((bowser.getX()==1 ||bowser.getX()==4)&& bowser.getY()==11)||
         ((bowser.getX()==17||bowser.getX()==20||bowser.getX()==23)&& bowser.getY()==17))         
       {
          sbg.enterState(7);
          WorldTemplate.bowser.setX(12);
          WorldTemplate.bowser.setY(18);
       }
       
       
    }       
    public int getID() { return 3; }  
}
