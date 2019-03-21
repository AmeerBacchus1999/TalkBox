package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.TalkBox.SwapButton;

class SwapButtonTest {

	@Test
	void test()
	{
		int slocation = 15;
		SwapButton sw = new SwapButton(slocation);
		sw.addValue(3);
		sw.addValue(4);
		sw.addValue(5);
		assertEquals(sw.next(), 3);
		assertEquals(sw.next(), 4);
		assertEquals(sw.next(), 5);
		assertEquals(sw.next(), 3);
		assertEquals(sw.next(), 4);
		assertEquals(sw.next(), 5);
		assertEquals(sw.next(), 3);
		assertEquals(sw.next(), 4);
		assertEquals(sw.next(), 5);
		
	}
	

}
