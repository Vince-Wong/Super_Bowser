package game_logic;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Backpack extends BasicGameState
{
   public static int prevScreenID;
   private final int ROW_SIZE = 7;
   private final int ITEM_SIZE = 64;
   private final int INVEN_X = 85;
   private final int INVEN_Y = 315;
   private final int PADDING = 30;
   private final String DESC_TAG = "ITEM DESCRIPTION:\n";

	private Image backpack;
	private String mouse = "No Input yet..";
	private String Play = "PRESS I TO GO BACK TO GAME!";
	private SpriteSheet bowserSheet;
	private Animation bowserAnimation;
	private Inventory inventory;
	private String description;
	
	
	public Backpack(int State){}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		backpack = new Image("res/Backpack2.jpg");
		bowserSheet = new SpriteSheet("res/EnterInventory.png",32,32);
		bowserAnimation = new Animation(bowserSheet,250);
		
		inventory = WorldTemplate.bowser.getInventory();
		description = DESC_TAG;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException{
		g.drawImage(backpack,0,0);
		g.drawString(mouse,10,30);
		g.drawString(Play,410,70);
		g.drawString(WorldTemplate.bowser.toString(), 85, 180);
		bowserAnimation.draw(100, 100);
		
		g.drawString(description, 410, 110);
		displayInventory(g);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException{
		bowserAnimation.update(delta);
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = WorldTemplate.WINDOW_HEIGHT-Mouse.getY();
		mouse = ("x: " + xpos + " y: "+ ypos);
		if(input.isKeyPressed(Input.KEY_I))
		{
			sbg.enterState(prevScreenID);	
		}
		
		if (xpos > INVEN_X-10 && ypos > INVEN_Y-10 && 
		      xpos < INVEN_X + 690 && ypos < INVEN_Y + 285)
		   displayItemInfo(xpos, ypos);
	}
	
	private void displayInventory(Graphics g) {
	   inventory = WorldTemplate.bowser.getInventory();  //TODO quick fix, remove later if there is a better way to implement this
	   Item[] items = inventory.getItems();
	   int i, j, x, y;
	   for (i = 0; i*ROW_SIZE < inventory.currentSize; i++) {
	      for (j = 0; i*ROW_SIZE+j < inventory.currentSize && j < ROW_SIZE; j++) {
	         Item thing = items[i*ROW_SIZE + j];
	         x = INVEN_X + (PADDING + ITEM_SIZE) * j;
	         y = INVEN_Y + (PADDING + ITEM_SIZE) * i;
	         g.drawImage(thing.getImage().getScaledCopy(2f), x, y);
	      }
	   }
	}
	
	private void displayItemInfo(int xpos, int ypos) {
	   int i, j, x, y;
	   description = DESC_TAG;
	   for (i = 0; i*ROW_SIZE < inventory.currentSize; i++) {
         for (j = 0; i*ROW_SIZE+j < inventory.currentSize && j < ROW_SIZE; j++) {
            x = INVEN_X + (PADDING + ITEM_SIZE) * j;
            y = INVEN_Y + (PADDING + ITEM_SIZE) * i;
            if (xpos > x && ypos > y && xpos < x+ITEM_SIZE && ypos < y+ITEM_SIZE) {
               Item[] items = inventory.getItems();
               Item thing = items[i*ROW_SIZE + j];
               List<String> wrappedDesc = wrap(thing.getDescription(), 30);
               for (String piece : wrappedDesc) {
                  description += piece + "\n";
               }
               return;
            }
         }
	   }
	}
	
	//Wraps the given string into a list of split lines based on the width
   private List<String> wrap(String text, int width) {
       //A less accurate but more efficient wrap would be to specify the max 
       //number of columns (e.g. using the width of the 'M' character or something). 
       //The below method will look nicer in the end, though.
       
       List<String> list = new ArrayList<String>();
       String str = text;
       String line = "";
       
       //we will go through adding characters, once we hit the max width
       //we will either split the line at the last space OR split the line
       //at the given char if no last space exists
       
       //while we still have text to check
       int i = 0;
       int lastSpace = -1;
       while (i<str.length()) {
           char c = str.charAt(i);
           if (java.lang.Character.isWhitespace(c))
               lastSpace = i;
           
           //time to wrap 
           if ((line + c).length() > width) {
               //if we've hit a space recently, use that
               int split = lastSpace!=-1 ? lastSpace : i;
               int splitTrimmed = split;
               
               //if we are splitting by space, trim it off for the start of the
               //next line
               if (lastSpace!=-1 && split<str.length()-1) {
                  splitTrimmed++;
               }
               
               line = str.substring(0, split);
               str = str.substring(splitTrimmed);
               
               //add the line and reset our values
               list.add(line);
               line = "";
               i = 0;
               lastSpace = -1;
           } 
           //not time to wrap, just keep moving along
           else {
               line += c;
               i++;
           }
       }
       if (str.length()!=0)
           list.add(str);
       return list;
   }
	
	//cause "i" is 9 in the alphabet
	public int getID(){
		return 9;
	}	
}
