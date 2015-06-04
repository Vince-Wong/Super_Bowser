package game_logic;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Test01 extends Play
{
   private Wall wall1;
   private Image map;
   
   public Test01(int state) {
      super(state);
   }
   
   public void init(GameContainer gc, StateBasedGame sbg)
         throws SlickException
   {
      map = new Image("res/allBlueTest.jpg");
      wall1 = new Wall("wall1",32*10, 32*10, 32*2, 32*2);
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
         throws SlickException
   {
      g.drawImage(map,0,0);
      if (debug) {
         g.setColor(Color.green);
         g.fill(wall1.getShape());
      }
      super.render(gc, sbg, g);
   }
   
   public int getID() { return 901; }
}
