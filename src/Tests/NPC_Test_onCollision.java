package Tests;

import static org.junit.Assert.*;
import game_logic.NPC;

import org.junit.Test;

public class NPC_Test_onCollision {

	@Test
	public void test() {
		NPC bob = new NPC();
		NPC peter = new NPC();
		
		boolean collision = bob.onCollision(peter);
		assertTrue(collision);
	}

}
