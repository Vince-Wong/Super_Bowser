package game_logic;

import org.newdawn.slick.geom.Rectangle;

public class NPC extends Character{

	final String FILENAME = "res/dialogue.txt";	// Directory path for Mac OS X
	//final String FILENAME = "res\dialogue.txt";	// Directory path for Windows OS (i.e. Operating System)
	private FileReader reader = new FileReader("res/dialogue.txt");
	public String[][] dialogue = reader.getParsedDialogue();
	
	public int ID;
	//public FileReader fileReader = new FileReader(FILENAME);
	
	public NPC()
	{
		setShape(new Rectangle(300, 300, 41, 38));
	}
	
	/**
	 * The NPC will interact on collision and action key pressed
	 */
	public boolean onCollision(NPC npc) {
		if(npc.getX() == this.getX() && npc.getY() == this.getY())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * use the ID to determine which dialog to receive.
	 * @param index at which dialogue want to retrive
	 * @return 
	 */
	public String getDialogue(int index)
	{
		
		String dialogueStr = dialogue[ID][index];
		return dialogueStr;
	}
	
	
	public int getSizeDialogue()
	{
		int length = dialogue[ID].length;
		return length;
	}
	
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public int getID()
	{
		return ID;
	}
	
	
	public static void main(String[] str)
	{
		
		NPC oldLady = new NPC();
	    oldLady.setID(1);
	    
	    System.out.println(oldLady.dialogue[0][1]);
	    System.out.println(oldLady.dialogue[1][1]);
	    System.out.println(oldLady.dialogue[2][1]);
	    //oldLady.getDialogue(0);
	    //oldLady.getDialogue(1);
	    
	    System.out.println("----------TO STRING----------");
		oldLady.reader.toString();
		
	}
	
}
