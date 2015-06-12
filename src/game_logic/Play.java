package game_logic;

import java.awt.Font;
import java.util.ArrayList;

import javax.management.timer.Timer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	private Image Map;
	private Image oldWomanImage;
	private SpriteSheet OWI;
	boolean quit = false;
	private Bowser bowser;
	private boolean drawTEXT = false;
	private boolean checkForMaxDial = false;
	int dialogueNumber = 0;

	public Play(int State) {
	}

	public NPC oldWoman;

	// make bowser at the beginning
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bowser = new Bowser();
		oldWoman = new NPC();
		oldWoman.setID(1);
		// TEMP MAP
		Map = new Image("res/TempBackground.jpg");
		
		oldWomanImage = new Image("res/kamekCust.png");
		OWI = new SpriteSheet(oldWomanImage, 41, 38);

		// sets the max frames per second
		int maxFPS = 60;
		gc.setTargetFrameRate(maxFPS);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// window size 1000, 750
		int width = 1000;
		int height = 750;

		//g.drawImage(Map, 0, 0);
		
		//g.drawImage(oldWomanImage, 50, 50);
		oldWomanImage.getSubImage(218, 68, 41, 38).draw(300, 300);
		//OWI.getSubImage(218, 68, 41, 38).draw(400, 400);
		if(drawTEXT)
		{
			
				g.drawString(oldWoman.getDialogue(dialogueNumber), 400, 350);
		}
		
		// renders bowser
		ShapeRenderer.fill(bowser.getShape());
		bowser.getCurrentAnim().draw(bowser.getX() - Bowser.PADDING, bowser.getY() - Bowser.PADDING);
		
		// renders the menu
		if (quit) {
			g.setColor(Color.red);
			g.fillRect(width / 2 - 40, height / 2 - 140, 200, 200);

			g.setColor(Color.black);
			g.fillRect(width / 2 - 30, height / 2 - 130, 180, 180);

			g.setColor(Color.white);
			g.drawString("Resume (R)", width / 2, height / 2 - 100);
			g.drawString("Main Menu (M)", width / 2, height / 2 - 50);
			g.drawString("Quit Game (Q)", width / 2, height / 2);
			if (!quit) {
				g.clear();
			}
		}
	}
private long counter = 0;
	// based on input, update bowser's state
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		bowser.getCurrentAnim().update(delta);
		Input input = gc.getInput();
		
		
		int size = oldWoman.getSizeDialogue() - 1;
		///System.out.println(size);
		counter += delta;
		if(counter >= 10000)
		{
			counter = 0;
			drawTEXT = false;
			dialogueNumber = 0;
			//System.out.println("OMG 5 Second Counter");
		} 
		
		
		if(bowser.getShape().intersects(oldWoman.getShape()) && input.isKeyPressed(Input.KEY_E))
		{
			drawTEXT = true;
			if(size > dialogueNumber)
			{
				dialogueNumber++;
				System.out.println(dialogueNumber);
			}
		}	
		
		// Input is the UP command
		if (input.isKeyDown(Input.KEY_W)) {
			if (bowser.getFace()) {
				bowser.setCurrentAnim(Character.FWD);
			} else {
				bowser.setCurrentAnim(Character.BACK);
			}
			bowser.setY(bowser.getY() - delta * .5f);
		}
		// Input is the DOWN command
		if (input.isKeyDown(Input.KEY_S)) {
			if (bowser.getFace()) {
				bowser.setCurrentAnim(Character.FWD);
			} else {
				bowser.setCurrentAnim(Character.BACK);
			}
			bowser.setY(bowser.getY() + delta * .5f);
		}
		// Input is the LEFT command
		if (input.isKeyDown(Input.KEY_A)) {
			bowser.faceLeft();
			bowser.setCurrentAnim(Character.BACK);
			bowser.setX(bowser.getX() - delta * .5f);
		}
		// Input is the RIGHT command
		if (input.isKeyDown(Input.KEY_D)) {
			bowser.faceRight();
			bowser.setCurrentAnim(Character.FWD);
			bowser.setX(bowser.getX() + delta * .5f);
		}
		// No input, but need to show bowser standing still
		if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W) && bowser.getCurrentAnim() == bowser.getAnimation(Character.FWD))
			bowser.setCurrentAnim(Character.FWD_STILL);
		if (!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W) && bowser.getCurrentAnim() == bowser.getAnimation(Character.BACK))
			bowser.setCurrentAnim(Character.BACK_STILL);

		// Inventory.
		if (input.isKeyPressed(Input.KEY_I)) {
			sbg.enterState(9);
		}

		// /// menu //////////////
		// escape
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}

		// when menu is up
		if (quit) {
			if (input.isKeyDown(Input.KEY_R)) {
				quit = false;
			}
			if (input.isKeyDown(Input.KEY_M)) {
				sbg.enterState(0);
			}
			if (input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}
	}

	public int getID() {
		return 1;
	}

}