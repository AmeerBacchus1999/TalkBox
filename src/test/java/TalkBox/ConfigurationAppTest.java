
package test.java.TalkBox;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.java.TalkBox.ConfigurationApp;
import main.java.TalkBox.TalkBoxConfiguration;


public class ConfigurationAppTest {
	
	@Test
	public void test()
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
	public void test2()
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
	public void test3()
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
	
	@Test
	public void test4()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp(1,2,3);
			c.serialize("ABC");
			ConfigurationApp mainclass = ConfigurationApp.unserializeMainClass("ABC" + ConfigurationApp.extension);
			TalkBoxConfiguration interface_ = ConfigurationApp.unserializeInterface("ABC" + ConfigurationApp.extension);
			mainclass.getCurrentAudioSet();
			interface_.getAudioFileNames();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			fail();
		}
	}
	
	
	
}
