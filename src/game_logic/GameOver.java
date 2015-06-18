package game_logic;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameOver extends BasicGameState{
//	Image death;
	String mouse = "No Input yet..";
	String instructions = "PRESS ENTER TO START OVER!";
	String replay = "YOU ARE DEAD! HOW SAD. :'(";
	private SpriteSheet bowserSheet;
	private Animation bowserAnimation;
	public GameOver(int State){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
//		death = new Image("res/menuMario.png");
		bowserSheet = new SpriteSheet("res/BowserExcited.png",80,80);
		bowserAnimation = new Animation(bowserSheet,250);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException{
//		g.drawImage(death,0,0);
		g.drawString(mouse,10,30);
		g.drawString(replay,260,210);
		g.drawString(instructions, 260, 410);
		bowserAnimation.draw(350, 300);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException
	{
		bowserAnimation.update(delta);
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = WorldTemplate.WINDOW_HEIGHT-Mouse.getY();
		mouse = ("x: " + xpos + " y: "+ ypos);
		if(input.isKeyDown(Input.KEY_ENTER))
		{
		   sbg.initStatesList(gc);
		   sbg.enterState(0);	
		}
	}	
	public int getID(){return 666;}	
}
