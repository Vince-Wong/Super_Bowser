package game_logic;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	Image menu;
	public String mouse = "No Input yet..";
	public String Play = "PRESS ENTER TO PLAY!";
	private SpriteSheet bowserSheet;
	private Animation bowserAnimation;
	public Menu(int State){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		menu = new Image("res/menuMario.png");
		bowserSheet = new SpriteSheet("res/Bowser HammerTime.png",32,64);
		bowserAnimation = new Animation(bowserSheet,250);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException{
		g.drawImage(menu,0,0);
		g.drawString(mouse,10,30);
		g.drawString(Play,400,640);
		bowserAnimation.draw(300, 610);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException
	{
		bowserAnimation.update(delta);
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 750-Mouse.getY();
		mouse = ("x: " + xpos + " y: "+ ypos);
		if(input.isKeyDown(Input.KEY_ENTER))
		{
			sbg.enterState(1);	
		}
		 if(input.isKeyDown(Input.KEY_3))
	      {
	         sbg.enterState(3);   
	      }
	    if(input.isKeyDown(Input.KEY_4))
       {
          sbg.enterState(4);   
       }
	}
	
	public int getID(){
		return 0;
	}	
}
