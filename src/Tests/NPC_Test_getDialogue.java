package Tests;

import static org.junit.Assert.*;
import game_logic.NPC;

import org.junit.Test;

public class NPC_Test_getDialogue {
	
	@Test
	public void test() {
		NPC npc = new NPC(32,32);  //TODO please fix peter, 2 random ints in constructor as a place holder for now so I can merge the branches
		
		npc.setID(5);
		
		String dialogue = npc.getDialogue(1);
		int ID = npc.getID();
		assertEquals(5, ID);
	}

}
