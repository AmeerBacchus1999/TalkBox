package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import main.java.TalkBox.AudioButton;

class AudioButtonTest {
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
	void test()
	{
		try
		{
			AudioButton a = new AudioButton();
			a.setAudio("ABC");
			assertEquals(a.getAudio(), "ABC");
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
