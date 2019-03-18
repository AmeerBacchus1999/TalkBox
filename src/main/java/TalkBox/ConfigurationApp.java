package main.java.TalkBox;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ConfigurationApp implements TalkBoxConfiguration {

	private int numButtons;
	private int currentAudioSet;
	private List<TalkBoxButton> buttons;
	
	public ConfigurationApp(int numButtons)
	{
		this.setNumButtons(numButtons);
		this.setCurrentAudioSet(1);
		this.buttons = new ArrayList<TalkBoxButton>();
	}
	
	public int getCurrentAudioSet() {
		return currentAudioSet;
	}

	public void setCurrentAudioSet(int currentAudioSet) {
		this.currentAudioSet = currentAudioSet;
	}

	public int getNumButtons() {
		return numButtons;
	}
	
	private void setNumButtons(int numButtons)
	{
		this.numButtons = numButtons;
	}
	
	
	
	
	@Override
	public int getNumberOfAudioButtons() {
		return 0;
	}

	@Override
	public int getNumberOfAudioSets() {
		return 0;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return this.numButtons;
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
