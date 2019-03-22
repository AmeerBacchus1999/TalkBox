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
	void normalize()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp();
			c.addAudioSet();
			c.addAudioSet();
			c.addAudioSet();
			c.addAudioSet();
			c.addAudioSet();
			c.setCurrentAudioSet(1);
			c.addAudioButton();
			c.addAudioButton();
			c.addSwapButton();
			c.addSwapButton();
			c.addSwapButton();
			//c.removeSwapButton(4);
			c.removeAudioButton(1);
			c.swapButtonPress(4);
			c.swapButtonPress(4);
			c.swapButtonPress(3);
			c.swapButtonPress(4);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	@Test
	void normalize2()
	{
		
		
		//audioSet.normalizeSwapButtons();
		
	}
	
	@Test
	void removeButton()
	{
		
	}
	
	
	
	
}
