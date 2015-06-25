/**
 * This class is to read from file.
 */
package game_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {
	
	final private File fileName;
	public int[] NPC_ID;// = new int[6];
	private Scanner scanner;
	private String[][] parsedDialogue;
	private int numDialogue;
	
	public FileReader(String fileNameIn)
	{
		fileName = new File(fileNameIn);
		
	}

	private void skipLines(int numLinesToSkip) {
		try {
			scanner = new Scanner(fileName);
			for (int i = 0; i <= numLinesToSkip; i++) {
				scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	
	public int getNumID() {
		int numID = 0;
		skipLines(0);
		while (scanner.hasNext()) {
			String data = scanner.nextLine();
			numID++;
		}
		scanner.close();
		return numID;
	}
	
	
	
	/**
	 * Gets the  
	 * @return
	 */
	public int[] getNPC_ID() 
	{
		int counter = 0;
		int numOfLines = getNumID();
		
		NPC_ID = new int[numOfLines];
		skipLines(0);
		while (scanner.hasNext()) {
			String data = scanner.nextLine();
			String[] token = data.split("&");
			NPC_ID[counter] = Integer.parseInt(token[0]);
			counter++;
		}
		scanner.close();
		return NPC_ID;
	}

		
	
	public String[][] getParsedDialogue()
	{
		parsedDialogue = new String[getNPC_ID().length][];
		skipLines(0);
		
		while(scanner.hasNextLine())
		{
			for(int counter = 0; counter < parsedDialogue.length;counter++)
			{
				String data = scanner.nextLine();
				String[] token = data.split("&");
				numDialogue = token.length-1;
				
				parsedDialogue[counter] = new String[numDialogue];
				for(int i = 0; i < numDialogue; i++)
				{
					parsedDialogue[counter][i] = token[i+1];
				}
			}
		}
		
		return parsedDialogue;
	}
	
	
	
	
	public String toString()
	{
		String str = "";
		for(int counter = 0; counter < parsedDialogue.length;counter++)
		{
			for(int i = 1; i < parsedDialogue[counter].length; i++)
			{
				System.out.print(parsedDialogue[counter][i]);
			}
			System.out.println("");
		}
		
		return str;
	}
	
	
	/**
	 * This main is used to test the file reader.
	 * @param str
	 */
	public static void main(String[] str)
	{
		FileReader reader = new FileReader("res/dialogue.txt");
		
		int[] NPC_ID = reader.getNPC_ID();
		String[][] dialogue = reader.getParsedDialogue();
		
		NPC oldLady = new NPC(1,1);
	    oldLady.setID(1);
	    
	   // oldLady.getDialogue(0);
	   // oldLady.getDialogue(1);
	    
	    System.out.println("----------TO STRING----------");
		reader.toString();
		
	}
	
}
