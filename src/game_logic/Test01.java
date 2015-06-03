package game_logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Test01 extends Play
{
   //Wall wall1;
   private Image map;
   
   public Test01(int state) {
      super(state);
   }
   
   public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
   {
      Play.bowser.setX(40);
      Play.bowser.setY(40);
      map = new Image("res/allBlueTest.jpg");
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
         throws SlickException
   {
      g.drawImage(map,0,0);
      super.render(gc, sbg, g);
   }
   
   public int getID() { return 901; }
}
