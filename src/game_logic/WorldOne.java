package game_logic;

import java.util.ArrayList;
import java.util.List;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class WorldOne extends WorldTemplate
{   
	private NPC oldLady = new NPC(Character.SIZE*12, Character.SIZE*10);
	private Image oldLadyI;
	private Image dialogueBox;
	private boolean chat = false;
	private boolean chatBubble = false;
	private boolean chatBar = false;
	private boolean YellToPlayer = false;
	private int dialogueNumber = -1;
	
	//TODO test items, remove later
   private Item testItem1, testItem2, testItem3, fireFlower;


	public WorldOne(int state){
		super(state);
	}
	// make bowser at the beginning
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
	   //tiled map with 3 layers: background, flowers, and buildings
      map = new TiledMap("res/worldOneMap.tmx");
      objectLayer = map.getLayerIndex("Buildings");
      map.getTileId(0,0, objectLayer);
      
      //TODO test items, remove later
      items = new ArrayList<>();
      testItem2 = new Item("ChainsawTest", false, 1, 2, "It's a chainsaw!!!");
      testItem2.getShape().setLocation(Character.SIZE * 8, Character.SIZE * 9);
      testItem2.setImage(new Image("res/ChainSaw.png"));
      testItem3 = new Item("The Hamma", false, 1, 1, "Basic Hammer, find me some nails?");
      testItem3.getShape().setLocation(Character.SIZE * 10, Character.SIZE * 11);
      testItem3.setImage(new Image("res/Hammer.png"));
      fireFlower = new Item("Fire Flower", false, 1, 1, "FIRE!!!!", 11, 12, "res/Fire Flower.png");
      items.add(testItem2);
      items.add(testItem3);

      mobs = new ArrayList<>();
      mobs.add(new MobFollow("testBoo", 3, 9, testItem3));

		oldLady.setID(0);
		oldLadyI = new Image("res/OldLady.png");
		dialogueBox = new Image("res/dialogueBox.png");
		YellToPlayer = true;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException
	{
		super.render(gc, sbg, g);
		
		if(YellToPlayer)
		{
			dialogueBox.draw(Character.SIZE*9 + 16, Character.SIZE*8);
			g.setColor(Color.black);
			int x = Character.SIZE*9 + 30;
			int y = Character.SIZE*8 + 15;
			g.setColor(Color.black);
			g.drawString("Quick, over here!", x, y);
		}
		
		
		oldLadyI.draw(Character.SIZE*12, Character.SIZE*10, Character.SIZE, Character.SIZE);

		if(chat == true)
		{
			if(dialogueNumber < oldLady.getSizeDialogue()){
				String text = oldLady.getDialogue(dialogueNumber);

				if(chatBubble)
				{
					dialogueBox.draw(Character.SIZE*9 + 16, Character.SIZE*8);
					g.setColor(Color.black);
					int x = Character.SIZE*9 + 30;
					int y = Character.SIZE*8 + 15;
					g.setColor(Color.black);

					g.drawString(text, x, y);

				}
				if(chatBar)
				{
					g.setColor(Color.white);
					g.fillRoundRect(20, 550, WINDOW_WIDTH-40, 80, 40);
					g.setColor(Color.black);
					int x = 40;
					int y = 580;
					if(text.length() < 50)
						x = (WINDOW_WIDTH / 2) - text.length() - 80;
					g.setColor(Color.black);
					g.drawString(text, x, y);
				}
			}

		}
	}

	int tick = 0;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
			throws SlickException
	{
	   Game.prevState = this;
		tick += delta;
		super.update(gc, sbg, delta);


		Input input = gc.getInput();
		int size = oldLady.getSizeDialogue()-1;

		if(tick >= 5000)
		{
			tick = 0;
			chat = false;
			chatBubble = false;
			chatBar = false;
			dialogueNumber = -1;
		} 

		if(input.isKeyPressed(Input.KEY_R))
		{
			tick = 0;
			chat = false;
			chatBubble = false;
			chatBar = false;
			dialogueNumber = -1;
			System.out.println(bowser.getX() + " " + bowser.getY());
		}

		if(bowser.getShape().intersects(oldLady.getShape()) && input.isKeyPressed(Input.KEY_E))
		{
			YellToPlayer = false;
			chatBubble = false;
			chatBar = false;
			if(dialogueNumber < size)
			{
				dialogueNumber++;
				tick = 0;
				System.out.println(dialogueNumber);
			}
			chat = true;
			int characterLeng = oldLady.getSizeDialogue(dialogueNumber);
			if(characterLeng < 20)
				chatBubble = true;
			else
				chatBar = true;
		}

		//Bowser enters Room map from first map
				if(bowser.getX()==11 && bowser.getY()==10)
				{
		         sbg.enterState(20);
					WorldTemplate.bowser.setX(12);
					WorldTemplate.bowser.setY(18);
				}				
		//Bowser enters second map if he exits the first map
		if(bowser.getX()==24 && bowser.getY()==11)
		{
		   sbg.enterState(2);
			WorldTemplate.bowser.setX(1);
			WorldTemplate.bowser.setY(10);
		}	
	}    

	public int getID() { return 1; }
   

}
