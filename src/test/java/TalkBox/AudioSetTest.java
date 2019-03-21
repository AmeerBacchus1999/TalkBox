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
		
		audioSet.addAudioButton(10);
		audioSet.addAudioButton(30);
		audioSet.addAudioButton(20);
		
		assertEquals(audioSet.numAudioButtons(), 3);
	}
	
	@Test
	void getAudioButton()
	{
		AudioSet audioSet = new AudioSet(1);
		
		audioSet.addAudioButton(10);
		audioSet.addAudioButton(30);
		audioSet.addAudioButton(20);
		
	}
	
	@Test
	void normalize()
	{
		ConfigurationApp.totalNumAudioSets = 5;
		AudioSet audioSet = new AudioSet(1);
		audioSet.addSwapButton(1);
		audioSet.addSwapButton(4);
		audioSet.addSwapButton(2);
		audioSet.addSwapButton(3);
		
		//audioSet.normalizeSwapButtons();
		
	}
	
	@Test
	void normalize2()
	{
		ConfigurationApp.totalNumAudioSets = 3;
		AudioSet audioSet = new AudioSet(1);
		audioSet.addSwapButton(1);
		audioSet.addSwapButton(4);
		audioSet.addSwapButton(2);
		audioSet.addSwapButton(3);
		audioSet.addSwapButton(6);
		
		
		//audioSet.normalizeSwapButtons();
		
	}
	
	@Test
	void removeButton()
	{
		ConfigurationApp.totalNumAudioSets = 5;
		AudioSet a = new AudioSet(1);
		a.addAudioButton(1);
		a.addAudioButton(2);
		a.addSwapButton(3);
		a.addAudioButton(4);
		a.addSwapButton(5);
		a.addAudioButton(6);
		
		a.removeTalkBoxButton(3);
	}
	
	
	
	
}
