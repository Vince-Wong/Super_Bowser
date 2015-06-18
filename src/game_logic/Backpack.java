package game_logic;

import java.awt.Font;

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

	private Image backpack;
	private String mouse = "No Input yet..";
	private String Play = "PRESS I TO GO BACK TO GAME!";
	private SpriteSheet bowserSheet;
	private Animation bowserAnimation;
	private Inventory inventory;
	private TextField description;
	
	public Backpack(int State){}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		backpack = new Image("res/Backpack2.jpg");
		bowserSheet = new SpriteSheet("res/EnterInventory.png",32,32);
		bowserAnimation = new Animation(bowserSheet,250);
		
		inventory = WorldTemplate.bowser.getInventory();
		description = new TextField(gc, new TrueTypeFont(new Font(java.awt.Font.DIALOG, java.awt.Font.PLAIN, 20), true), 390,120,310,100);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException{
		g.drawImage(backpack,0,0);
		g.drawString(mouse,10,30);
		g.drawString(Play,410,70);
		g.drawString(WorldTemplate.bowser.toString(), 85, 180);
		bowserAnimation.draw(100, 100);
		
		description.render(gc, g);
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
		
		if (xpos > INVEN_X && ypos > INVEN_Y && 
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
	   for (i = 0; i*ROW_SIZE < inventory.currentSize; i++) {
         for (j = 0; i*ROW_SIZE+j < inventory.currentSize && j < ROW_SIZE; j++) {
            x = INVEN_X + (PADDING + ITEM_SIZE) * j;
            y = INVEN_Y + (PADDING + ITEM_SIZE) * i;
            if (xpos > x && ypos > y && xpos < x+ITEM_SIZE && ypos < y+ITEM_SIZE) {
               Item[] items = inventory.getItems();
               Item thing = items[i*ROW_SIZE + j];
               description.setText(thing.getDescription());
            }
         }
	   }
	}
	
	//cause "i" is 9 in the alphabet
	public int getID(){
		return 9;
	}	
}
