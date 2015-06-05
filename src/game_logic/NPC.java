package game_logic;

public class NPC extends Character{

	final String FILENAME = "res/dialogue.txt";	// Directory path for Mac OS X
	//final String FILENAME = "res\dialogue.txt";	// Directory path for Windows OS (i.e. Operating System)
	private FileReader reader = new FileReader("res/dialogue.txt");
	public String[][] dialogue = reader.getParsedDialogue();
	
	public int ID;
	//public FileReader fileReader = new FileReader(FILENAME);
	
	
	/**
	 * The NPC will interact on collision and action key pressed
	 */
	public void onCollision(Entity ent) {
		
	}
	
	//use the ID to determine which dialog to receive.
	public String getDialogue(int index)
	{
		//String dialogue = fileReader.getDialogueAt(ID, index);
		return null;
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
