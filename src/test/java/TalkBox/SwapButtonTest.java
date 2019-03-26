package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.java.TalkBox.IteratorNoRemovals;
import main.java.TalkBox.SwapButton;

class SwapButtonTest {
/*
	
	
	
	
	@Test
	void test()
	{
		try
		{
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	
	 */
	
	@Test
	void testIterator()
	{
		try
		{
			SwapButton s = new SwapButton(
					new ArrayList<Integer>(
							Arrays.asList(1,2,3)));
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
