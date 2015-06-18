package game_logic;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	Image menu;
	String mouse = "No Input yet..";
	String Play = "PRESS ENTER TO PLAY!";
	String instructions = "USE WASD TO MOVE, "
	      + "PRESS I TO VIEW INVENTORY, "
	      + "PRESS ESC TO PAUSE";
	private SpriteSheet bowserSheet;
	private Animation bowserAnimation;
	public Menu(int State){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		menu = new Image("res/menuMario.png");
		bowserSheet = new SpriteSheet("res/BowserExcited.png",80,80);
		bowserAnimation = new Animation(bowserSheet,250);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException{
		g.drawImage(menu,0,0);
		g.drawString(mouse,10,30);
		g.drawString(Play,330,560);
		g.drawString(instructions, 110, 610);
		bowserAnimation.draw(250, 520);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException
	{
		bowserAnimation.update(delta);
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = WorldTemplate.WINDOW_HEIGHT-Mouse.getY();
		mouse = ("x: " + xpos + " y: "+ ypos);
		if(input.isKeyDown(Input.KEY_1) || input.isKeyDown(Input.KEY_ENTER))
		{
		   sbg.enterState(1);
         WorldTemplate.bowser.setX(8);
         WorldTemplate.bowser.setY(8);	
		}
		if(input.isKeyDown(Input.KEY_2))
		{
		   sbg.enterState(2);
		   WorldTemplate.bowser.setX(0);
		   WorldTemplate.bowser.setY(10);
		}
	}	
	public int getID(){return 0;}	
}
