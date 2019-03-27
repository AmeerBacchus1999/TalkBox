package test.java.TalkBox;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.TalkBox.AudioButton;
import main.java.TalkBox.ConfigurationApp;
import main.java.TalkBox.SwapButton;

class ConfigurationAppTest {
	
	@Test
	void test()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp(2, 5, 3);
			
			c.updateAudioSetSwapButton(3);
			assertEquals(1, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(2);
			assertEquals(2, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(2);
			assertEquals(2, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(3);
			assertEquals(1, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(1);
			assertEquals(1, c.getCurrentAudioSet());
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
			ConfigurationApp c = new ConfigurationApp(8, 5, 5);
			
			c.updateAudioSetSwapButton(2);
			assertEquals(2, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(5);
			assertEquals(5, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(5);
			assertEquals(6, c.getCurrentAudioSet());

			c.updateAudioSetSwapButton(5);
			assertEquals(7, c.getCurrentAudioSet());

			c.updateAudioSetSwapButton(5);
			assertEquals(8, c.getCurrentAudioSet());
			
			c.updateAudioSetSwapButton(5);
			assertEquals(5, c.getCurrentAudioSet());
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
	
	@Test
	void test3()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp(6, 3, 5);
			
			c.updateAudioSetSwapButton(5);
			c.getAudioButtons()[0].setAudio("A");
			c.updateAudioSetSwapButton(5);
			c.updateAudioSetSwapButton(5);
			assertEquals(c.getAudioButtons()[0].getAudio(), "A");
		}		
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
	
	
	
}
