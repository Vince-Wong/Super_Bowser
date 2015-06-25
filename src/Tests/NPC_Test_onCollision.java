package Tests;

import static org.junit.Assert.*;
import game_logic.NPC;

import org.junit.Test;

public class NPC_Test_onCollision {

	@Test
	public void test() {
		NPC bob = new NPC(32,32);   //TODO please fix peter, 2 random ints in constructor as a place holder for now so I can merge the branches
		NPC peter = new NPC(32,32); //TODO please fix peter, 2 random ints in constructor as a place holder for now so I can merge the branches
		
		boolean collision = bob.onCollision(peter);
		assertTrue(collision);
	}

}
