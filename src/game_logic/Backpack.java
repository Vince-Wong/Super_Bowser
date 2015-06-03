package src.game_logic;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Backpack extends BasicGameState{
	Image backpack;
	public String mouse = "No Input yet..";
	public String Play = "PRESS I TO TO GO BACK TO GAME!";
	private SpriteSheet bowserSheet;
	private Animation bowserAnimation;
	public Backpack(int State){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		backpack = new Image("res/Backpack.png");
		bowserSheet = new SpriteSheet("res/BowserExcited.png",87,80);
		bowserAnimation = new Animation(bowserSheet,250);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException{
		g.drawImage(backpack,0,0);
		g.drawString(mouse,10,30);
		g.drawString(Play,400,640);
		bowserAnimation.draw(100, 100);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException{
		bowserAnimation.update(delta);
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 750-Mouse.getY();
		mouse = ("x: " + xpos + " y: "+ ypos);
		if(input.isKeyDown(Input.KEY_I))
		{
			sbg.enterState(1);	
		}
	}
	
	//cause "i" is 9 in the alphabet
	public int getID(){
		return 9;
	}	
}
