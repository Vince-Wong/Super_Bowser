package game_logic;

import java.util.ArrayList;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class WorldTwo extends WorldTemplate
{   
	private int shiftX = 4;
	private int shiftY = -5;
	private NPC oldLady = new NPC(Character.SIZE*(12 + shiftX), Character.SIZE*(10 + shiftY));
	private Image oldLadyI;
	private Image dialogueBox;
	private boolean chat = false;
	private boolean chatBubble = false;
	private boolean chatBar = false;
	private boolean YellToPlayer = false;
	private boolean bothAlive = true;
	private int dialogueNumber = -1;


	public WorldTwo(int state){
		super(state);
	}
	// make bowser at the beginning
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		//tiled map with 3 layers: background, flowers, and buildings
		map = new TiledMap("res/WorldTwoMap.tmx");
		objectLayer = map.getLayerIndex("Buildings");
		map.getTileId(0,0, objectLayer);

		mobs = new ArrayList<>();
		mobs.add(new MobLR("testYToad", 17, 5, 23));
		mobs.add(new MobUD("testYToad2", 13, 5, 10));

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
			dialogueBox.draw(Character.SIZE*(9 + shiftX) + 16, Character.SIZE*(8 + shiftY));
			g.setColor(Color.black);
			int x = Character.SIZE*(9 + shiftX) + 30;
			int y = Character.SIZE*(8 + shiftY) + 15;
			g.setColor(Color.black);
			if(bothAlive)
				g.drawString("Help me & kill'em", x, y);
			if(bothAlive == false)
				g.drawString("Thank you so much.", x, y);

		}


		oldLadyI.draw(Character.SIZE*(12 + shiftX), Character.SIZE*(10 + shiftY), Character.SIZE, Character.SIZE);

		if(chat == true)
		{
			if(dialogueNumber < oldLady.getSizeDialogue()){
				String text = oldLady.getDialogue(dialogueNumber);

				if(chatBubble)
				{
					dialogueBox.draw(Character.SIZE*(9 + shiftX) + 16, Character.SIZE*(8 + shiftY));
					g.setColor(Color.black);
					int x = Character.SIZE*(9 + shiftX) + 30;
					int y = Character.SIZE*(8 + shiftY) + 15;
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
		
		//bowser re-enters previous world
		if(bowser.getX()==0 && bowser.getY()==10)
		{
			sbg.enterState(1);
			WorldTemplate.bowser.setX(23);
			WorldTemplate.bowser.setY(11);
		}
		//bowser enters new world.
		if(bowser.getX()==24 && bowser.getY()==3)
		{
			sbg.enterState(3);
			WorldTemplate.bowser.setX(1);
			WorldTemplate.bowser.setY(3);
		}   
	}    

	public int getID() { return 2; }

}