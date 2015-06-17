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
    
    //TODO test items, remove later
    private Item testItem1, testItem2, testItem3;
    
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       //tiled map with 3 layers: background, flowers, and buildings
       map = new TiledMap("res/worldOneMap.tmx");
       objectLayer = map.getLayerIndex("Buildings");
       map.getTileId(0,0, objectLayer);
       itemsLayer = map.getLayerIndex("Items");
       map.getTileId(0,0, itemsLayer);
       
       //TODO test items, remove later
       items = new ArrayList<>();
       testItem1 = new Item("Box1", false, 1, 1, "test item");
//       testItem1.setImage
       testItem2 = new Item("Box", false, 1, 2, "test item2");
       testItem2.getShape().setLocation(Character.SIZE * 3, Character.SIZE * 3);
       testItem3 = new Item("Box", false, 1, 1, "test item3");
       testItem3.getShape().setLocation(Character.SIZE * 5, Character.SIZE * 5);
       items.add(testItem1);
       items.add(testItem2);
       items.add(testItem3);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {
       map.render(0,0);
       super.render(gc, sbg, g);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
          throws SlickException
   {
       super.update(gc, sbg, delta);
   }
     
    public int getID() { return 3; }
  
}