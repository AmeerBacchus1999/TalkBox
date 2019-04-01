package test.java.TalkBox;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.java.TalkBox.ConfigurationApp;
import main.java.TalkBox.ControllerSimulator;
import main.java.TalkBox.TalkBoxConfiguration;
public class ControllerSimulatorTest {
	
	@Test
	public void test1()
	{
		try
		{
			ConfigurationApp c = new ConfigurationApp(2, 5, 3);
			c.serialize("Test1ControllerSimulator");
			TalkBoxConfiguration t = ConfigurationApp.unserializeInterface("Test1ControllerSimulator.tbc");
			ControllerSimulator co = new ControllerSimulator(t);
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			fail();
			
		}
	}
}
