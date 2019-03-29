package main.java.TalkBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import java.util.List;


public class ConfigurationApp implements TalkBoxConfiguration {

	private AudioButton[][] audioSets;
	private SwapButton[] swapButtons;
	private int currentAudioSet;
	public static String dir = "TalkBoxData";
	
	public ConfigurationApp(int numAudioSetsOfButtons, int numAudioButtons, int numSwapButtons)
	{
		if(numAudioSetsOfButtons < 1 || numAudioButtons < 1)
		{
			throw new IllegalArgumentException();
		}
		
		this.audioSets = new AudioButton[numAudioSetsOfButtons][numAudioButtons];
		this.swapButtons = new SwapButton[numSwapButtons];
		this.currentAudioSet = 1;
		this.instantiateAudioButtons();
		this.instantiateSwapButtons();
		
	}
	
	public AudioButton[] getAudioButtons()
	{
		return audioSets[currentAudioSet - 1];
	}
	
	public void updateAudioSetSwapButton(int i)
	{
		this.setCurrentAudioSet(this.swapButtons[i-1].next());
	}
	
	public void setCurrentAudioSet(int currentAudioSet)
	{
		if(currentAudioSet > this.getNumberOfAudioSets() ||
				currentAudioSet < 1)
		{
			throw new IllegalArgumentException();
		}
		this.currentAudioSet = currentAudioSet;
	}
	
	
	
	public int getCurrentAudioSet()
	{
		return this.currentAudioSet;
	}
	
	private void instantiateSwapButtons()
	{
		for(int i = 0; i < swapButtons.length; i++)
		{
			int iTopLoop = i;
			List<Integer> values = new ArrayList<Integer>();
			if(swapButtons.length > this.getNumberOfAudioSets())
			{
				values.add(i % this.getNumberOfAudioSets() + 1);	
			}
			else
			{
				if(i == swapButtons.length - 1)
				{
					for(; i < this.getNumberOfAudioSets() - 1; i++)
					{
						values.add(i+1);
					}
				}
				values.add(i + 1);
			}
			this.swapButtons[iTopLoop] = new SwapButton(values);
		}
	}
	
	private void instantiateAudioButtons()
	{
		for(int i = 0; i < this.getNumberOfAudioSets(); i++)
		{
			for(int j = 0; j < this.getNumberOfAudioButtons(); j++)
			{
				this.audioSets[i][j] = new AudioButton();
			}
		}
	}
	
	public void serialize(String filename)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(ConfigurationApp.dir + "\\" + filename +".tbc");
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static ConfigurationApp unserializeMainClass(String filename)
	{
		ConfigurationApp c = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(ConfigurationApp.dir + "\\" + filename);
			in = new ObjectInputStream(fis);
			c = (ConfigurationApp)in.readObject();
			in.close();
			return c;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public static TalkBoxConfiguration unserializeInterface(String filename)
	{
		TalkBoxConfiguration t = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(ConfigurationApp.dir + "\\" + filename);
			in = new ObjectInputStream(fis);
			t = (TalkBoxConfiguration)in.readObject();
			in.close();
			return t;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public int getNumberOfAudioButtons() {
		return this.audioSets[0].length;
	}

	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return this.audioSets.length;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return this.getNumberOfAudioButtons() + this.swapButtons.length; 
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return Paths.get(ConfigurationApp.dir);
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		String[][] audioSetFileNames = new String[audioSets.length]
				[audioSets[0].length];
		for(int i = 0; i < audioSets.length; i++)
		{
			for(int j = 0; j < audioSets[0].length; j++)
			{
				audioSetFileNames[i][j] = 
						this.audioSets[i][j].getAudio();			
			}
		}
		return audioSetFileNames;
	}

	
	
}
