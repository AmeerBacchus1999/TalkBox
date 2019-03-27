package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import main.java.TalkBox.SwapButton;

class SwapButtonTest {
	
	@Test
	void testIterator()
	{
		try
		{
			SwapButton s = new SwapButton(
					new ArrayList<Integer>(
							Arrays.asList(1,2,3)));
			assertEquals(1, (int) s.next());
			assertEquals(2, (int) s.next());
			assertEquals(3, (int) s.next());
			assertEquals(1, (int) s.next());
			assertEquals(2, (int) s.next());
			assertEquals(3, (int) s.next());
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
}
