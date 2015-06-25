package Tests;

import static org.junit.Assert.*;
import game_logic.NPC;

import org.junit.Test;

public class NPC_Test_getDialogue {
	
	@Test
	public void test() {
		NPC npc = new NPC();
		
		npc.setID(5);
		
		String dialogue = npc.getDialogue(1);
		int ID = npc.getID();
		assertEquals(5, ID);
	}

}
