package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import main.java.TalkBox.AudioButton;

class AudioButtonTest {
	
	@Test
	void test1()
	{
		try
		{
			AudioButton a = new AudioButton();
			a.setAudio("ABC");
			assertEquals(a.getAudio(), "ABC");
			a.setImage("DEF");
			assertEquals(a.getAudio(), "DEF");
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
	
	@Test
	void test2()
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
