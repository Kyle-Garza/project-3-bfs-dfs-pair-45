package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ladderTest {

	@Test(timeout = 30000) //30s timeout
	public void testDFS() {
		String start = "start";
		String end = "wifey";
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS(start, end);
		Main.printLadder(ladder);
		assertTrue(true);
	}
	
	@Test(timeout = 30000) //30s timeout
	public void testBFS() {
		String start = "start";
		String end = "wifey";
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS(start, end);
		Main.printLadder(ladder);
		assertTrue(true);
	}

}
