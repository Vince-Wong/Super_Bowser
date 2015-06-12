package game_logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class NPC_Test {
	
	@Test
	public void test() {
		NPC npc = new NPC();
		
		npc.setID(5);
		
		String dialogue = npc.getDialogue(1);
		int ID = npc.getID();
		assertEquals(5, ID);
		//dialogueID = npc.getDialogue(-1);
	}

}
