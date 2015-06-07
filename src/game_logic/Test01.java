package game_logic;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Test01 extends Play
{
   private Wall wall1, topWall, bottomWall, leftWall, rightWall;
   private Image map;
   
   public Test01(int state) {
      super(state);
   }
   
   public void init(GameContainer gc, StateBasedGame sbg)
         throws SlickException
   {
      map = new Image("res/allBlueTest.jpg");
      wall1 = new Wall("wall1",32*10, 32*10, 32*2, 32*2);
      topWall = new Wall("wallT",0, 0, WINDOW_WIDTH, 32);
      bottomWall = new Wall("wallB",0, WINDOW_HEIGHT-32, WINDOW_WIDTH, 32);
      leftWall = new Wall("wallL", 0, 0, 32, WINDOW_HEIGHT);
      rightWall = new Wall("wallR",WINDOW_WIDTH-32, 0, 32, WINDOW_HEIGHT);
      entities = new ArrayList<>();
      entities.add(wall1);
      entities.add(topWall);
      entities.add(bottomWall);
      entities.add(leftWall);
      entities.add(rightWall);
      
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
         throws SlickException
   {
      g.drawImage(map,0,0);
      if (debug) {
         g.setColor(Color.green);
         g.fill(wall1.getShape());
         g.fill(topWall.getShape());
         g.fill(bottomWall.getShape());
         g.fill(leftWall.getShape());
         g.fill(rightWall.getShape());
      }
      super.render(gc, sbg, g);
   }
   
   public int getID() { return 901; }
}
