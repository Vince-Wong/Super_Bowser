package game_logic;

import java.util.ArrayList;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState
{   
    public Play(int State){}
    Bowser bowser;
    
    // make bowser at the beginning
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
       bowser = new Bowser();
    }
    // render changes to bowser
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
           throws SlickException
    {  
       ShapeRenderer.fill(bowser.getShape()); 
       bowser.getCurrentAnim().draw(bowser.getX()-Bowser.PADDING,
                                     bowser.getY()-Bowser.PADDING);
    }
    // based on input, update bowser's state
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
           throws SlickException
    {
        bowser.getCurrentAnim().update(delta);
        Input input = gc.getInput();
        // Input is the UP command
        if(input.isKeyDown(Input.KEY_W))
        {
           if (bowser.getFace()) {
              bowser.setCurrentAnim(Character.FWD);
           }
           else {
              bowser.setCurrentAnim(Character.BACK);
           }
           bowser.setY(bowser.getY() - delta * .5f);
        }
        // Input is the DOWN command
        if(input.isKeyDown(Input.KEY_S))
        {
           if (bowser.getFace()) {
              bowser.setCurrentAnim(Character.FWD);
           }
           else {
              bowser.setCurrentAnim(Character.BACK);
           }
           bowser.setY(bowser.getY() + delta * .5f);
        }
        // Input is the LEFT command
        if(input.isKeyDown(Input.KEY_A))
        {
           bowser.faceLeft();
           bowser.setCurrentAnim(Character.BACK);
           bowser.setX(bowser.getX() - delta * .5f);
        }
        // Input is the RIGHT command
       if(input.isKeyDown(Input.KEY_D))
       {
           bowser.faceRight();
           bowser.setCurrentAnim(Character.FWD);
           bowser.setX(bowser.getX() + delta * .5f);
       }
       // No input, but need to show bowser standing still
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && bowser.getCurrentAnim() == bowser.getAnimation(Character.FWD))
           bowser.setCurrentAnim(Character.FWD_STILL);
       if(!input.isKeyDown(Input.KEY_A) &&!input.isKeyDown(Input.KEY_D)
          &&!input.isKeyDown(Input.KEY_S) &&!input.isKeyDown(Input.KEY_W)
          && bowser.getCurrentAnim() == bowser.getAnimation(Character.BACK))
          bowser.setCurrentAnim(Character.BACK_STILL);
    }
    public int getID(){return 1;}     
}