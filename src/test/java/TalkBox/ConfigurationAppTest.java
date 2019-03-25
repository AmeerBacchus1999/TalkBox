package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.TalkBox.AudioButton;
import main.java.TalkBox.ConfigurationApp;

class ConfigurationAppTest {

	@BeforeEach
	void setUp()
	{
		
	}
	
	@Test
	void test()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp();
			c.addAudioSetWithSameStructure();
			c.addAudioButtonToAllSets();
			c.addAudioButtonToAllSets();
			c.addSwapButtonToAllSets();
			c.addSwapButtonToAllSets();
			c.addAudioButtonToAllSets();
			
		}
		catch(Exception ex)
		{
			
		}
	}
	
	@Test
	void debugtest1()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp();
			c.addAudioSetWithSameStructure();
			c.addAudioButtonToAllSets();
			c.addAudioButtonToAllSets();
			c.addSwapButtonToAllSets();
			c.addSwapButtonToAllSets();
			c.addAudioSetWithSameStructure();
			c.addAudioSetWithSameStructure();
			c.removeAudioButtonAllSets(1);
			c.removeSwapButtonAllSets(3);
			c.removeAudioSet(1);
			c.setCurrentAudioSet(2);
			List<AudioButton> l = c.getAudioButtons();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	void test2()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp();
			c.addAudioSetWithSameStructure();
			c.addAudioButtonToAllSets();
			c.addAudioButtonToAllSets();
			c.addSwapButtonToAllSets();
			c.addAudioSetWithSameStructure();
			c.addSwapButtonToAllSets();
			
			c.swapButtonPress(3);
			c.swapButtonPress(4);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	void test3()
	{
		
	}
}
