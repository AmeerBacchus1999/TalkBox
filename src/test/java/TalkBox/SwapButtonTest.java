package test.java.TalkBox;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import main.java.TalkBox.SwapButton;


public class SwapButtonTest {
	
	@Test
	public void testIterator()
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
