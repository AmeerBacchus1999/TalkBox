package test.java.TalkBox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import main.java.TalkBox.AudioButton;

public class AudioButtonTest {
	
	@Test
	public void test()
	{
		fail();
	}
	
	@Test
	public void test1()
	{
		try
		{
			AudioButton a = new AudioButton();
			a.setAudio("ABC");
			assertEquals(a.getAudio(), "ABC");
			a.setImage("DEF");
			assertEquals(a.getAudio(), "DEF");
			fail();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test2()
	{
		try
		{
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
}
