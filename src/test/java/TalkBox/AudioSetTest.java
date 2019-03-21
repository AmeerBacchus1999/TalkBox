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
		AudioSet audioSet = new AudioSet(1);
		
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		
		assertEquals(audioSet.numAudioButtons(), 3);
	}
	
	@Test
	void getAudioButton()
	{
		AudioSet audioSet = new AudioSet(1);
		
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		audioSet.addAudioButton();
		
	}
	
	@Test
	void normalize()
	{
		ConfigurationApp c = new ConfigurationApp();
		c.addAudioSet();
		c.addAudioSet();
		c.addAudioSet();
		c.addAudioSet();
		c.addAudioSet();
		AudioSet audioSet = new AudioSet(1);
		
		
		//audioSet.normalizeSwapButtons();
		
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
