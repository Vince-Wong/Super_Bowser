package src.game_logic;

import java.util.ArrayList;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState
{   
	Image Map;
	boolean quit = false;
	
    public Play(int State){
    	
    }
    
    private SpriteSheet bowserSheet, bowserBackSheet, 
                        bowserStillSheet, bowserBackStillSheet;
    private Animation bowserAnimation,bowserBackAnimation,Bowser
    ,bowserStill, bowserBackStill;
    public int xpos,ypos = 100;
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        bowserStillSheet = new SpriteSheet("res/bowserStill.png",76,75);
        bowserBackStillSheet = new SpriteSheet("res/bowserBackStill.png",80,75);
        bowserSheet = new SpriteSheet("res/Bowser Walks.png",77,75);
        bowserBackSheet = new SpriteSheet("res/Bowser Walks Back.png",79,75);
        
        bowserStill = new Animation(bowserStillSheet,250);
        bowserBackStill = new Animation(bowserBackStillSheet,250);
        
        bowserAnimation = new Animation(bowserSheet,250);
        bowserBackAnimation = new Animation(bowserBackSheet,250);
        
        Bowser = bowserAnimation;
        
        //TEMP MAP
        Map = new Image("res/TempBackground.jpg");
        
      //sets the max frames per second
      		int maxFPS = 60;
      	    gc.setTargetFrameRate(maxFPS);
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {  
    	g.drawImage(Map,0,0);
    	
        Bowser.draw(xpos, ypos);
        
    	if(quit == true)
		{
			g.setColor(Color.black);
			g.fillRect(200, 250, 200, -200);
			
			g.setColor(Color.white);
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit == false)
			{
				g.clear();
			}
		}
        
        
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
        bowserAnimation.update(delta);
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_W))
        {
            ypos -= delta * .5f;
        }
        if(input.isKeyDown(Input.KEY_S))
        {
            ypos += delta * .5f;
        }
       if(input.isKeyDown(Input.KEY_A))
        {
           Bowser = bowserBackAnimation;
            xpos -= delta * .5f;
        }
       if(input.isKeyDown(Input.KEY_D))
       {
           Bowser = bowserAnimation;
           xpos += delta * .5f;
       }
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && Bowser == bowserAnimation)
           Bowser = bowserStill;
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && Bowser == bowserBackAnimation)
           Bowser = bowserBackStill;
 
   ///// menu	//////////////
		
   		//escape 
   		if(input.isKeyDown(Input.KEY_ESCAPE))
   		{
   			quit = true;
   		}
   		
   		//when menu is up
   		if(quit == true)
   		{
   			if(input.isKeyDown(Input.KEY_R))
   			{
   				quit = false;
   			}
   			if(input.isKeyDown(Input.KEY_M))
   			{
   				sbg.enterState(0);
   			}
   			if(input.isKeyDown(Input.KEY_Q))
   			{
   				System.exit(0);
   			}
   		}
     
    }
    public int getID(){return 1;}     
}
    

    
    
