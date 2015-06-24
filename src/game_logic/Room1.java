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

public class Room1 extends WorldTemplate
{   
	TypewriterTest tw = new TypewriterTest();
	private NPC oldLady = new NPC(Character.SIZE*12, Character.SIZE*10);
	private Image oldLadyI;
	private Image dialogueBox;
	private Image temp;
	private boolean chat = false;
	private boolean chatBubble = false;
	private boolean chatBar = false;
	private boolean YellToPlayer = false;
	private int dialogueNumber = -1;


	public Room1(int state){
		super(state);
	}
	// make bowser at the beginning
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		map = new TiledMap("res/worldOneMap.tmx");
		temp = new Image("res/TempBackground.jpg");
		objectLayer = map.getLayerIndex("Buildings");
		map.getTileId(0,0, objectLayer);

		mobs = new ArrayList<>();
		mobs.add(new MobFollow("Dying mob", 3, 9));

		oldLady.setID(2);
		oldLadyI = new Image("res/OldLady.png");
		dialogueBox = new Image("res/dialogueBox.png");

		
		YellToPlayer = true;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) 
			throws SlickException
	{
		temp.draw(0, 0);
		super.render(gc, sbg, g);
		
		if(YellToPlayer)
		{
			dialogueBox.draw(Character.SIZE*9 + 16, Character.SIZE*8);
			g.setColor(Color.black);
			int x = Character.SIZE*9 + 20;
			int y = Character.SIZE*8 + 5;
			g.setColor(Color.black);
			g.drawString("Come on, over here!", x, y);
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
					int x = Character.SIZE*9 + 20;
					int y = Character.SIZE*8 + 5;
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

		
		//Bowser exits Room map to first map
		if(bowser.getX()==5 && bowser.getY()==17)
		{
			sbg.enterState(1);
			WorldTemplate.bowser.setX(11);
			WorldTemplate.bowser.setY(11);
		}

	}    

	public int getID() { return 20; }

}
