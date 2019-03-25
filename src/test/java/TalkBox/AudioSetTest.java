package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.TalkBox.AudioButton;
import main.java.TalkBox.AudioSet;
import main.java.TalkBox.ConfigurationApp;

class AudioSetTest {
	
	

	@Test
	void addAudioButton()
	{
		AudioSet audioSet = new AudioSet();
		
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		
		assertEquals(audioSet.numAudioButtons(), 3);
	}
	
	
	@Test
	void getAudioButton()
	{
		AudioSet audioSet = new AudioSet();
		
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		
	}
	
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
	
	@Test
	void testMultiple()
	{
		
	}
	
	@Test
	void removeButton()
	{
		
	}
	
	
	
	
}
