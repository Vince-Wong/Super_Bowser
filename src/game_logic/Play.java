package game_logic;

import java.util.ArrayList;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState
{   
    public Play(int State){}
    
    private SpriteSheet bowserSheet, bowserBackSheet, 
                        bowserStillSheet, bowserBackStillSheet;
    private Animation bowserAnimation,bowserBackAnimation,Bowser
    ,bowserStill, bowserBackStill;
    public int xpos,ypos = 100;
    private boolean faceRight = true;
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        bowserStillSheet = new SpriteSheet("res/bowserStill.png",76,75);
        bowserBackStillSheet = new SpriteSheet("res/bowserBackStill.png",77,75);
        bowserSheet = new SpriteSheet("res/Bowser Walks.png",77,75);
        bowserBackSheet = new SpriteSheet("res/Bowser Walks Back.png",79,75);
        
        bowserStill = new Animation(bowserStillSheet,250);
        bowserBackStill = new Animation(bowserBackStillSheet,250);
        
        bowserAnimation = new Animation(bowserSheet,250);
        bowserBackAnimation = new Animation(bowserBackSheet,250);
        
        Bowser = bowserAnimation;
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {  
        Bowser.draw(xpos, ypos);
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
        bowserAnimation.update(delta);
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_W))
        {
           if (faceRight) {
              Bowser = bowserAnimation;
           }
           else {
              Bowser = bowserBackAnimation;
           }            
           ypos -= delta * .5f;
           System.out.println("ypos="+ypos);
        }
        if(input.isKeyDown(Input.KEY_S))
        {
           if (faceRight) {
              Bowser = bowserAnimation;
           }
           else {
              Bowser = bowserBackAnimation;
           }
           ypos += delta * .5f;
           System.out.println("ypos="+ypos);
        }
       if(input.isKeyDown(Input.KEY_A))
        {
           faceRight = false;
           Bowser = bowserBackAnimation;
           xpos -= delta * .5f;
           System.out.println("xpos="+xpos);
        }
       if(input.isKeyDown(Input.KEY_D))
       {
           faceRight = true;
           Bowser = bowserAnimation;
           xpos += delta * .5f;
           System.out.println("xpos="+xpos);
       }
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && Bowser == bowserAnimation)
           Bowser = bowserStill;
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && Bowser == bowserBackAnimation)
           Bowser = bowserBackStill;
    }
    public int getID(){return 1;}     
}