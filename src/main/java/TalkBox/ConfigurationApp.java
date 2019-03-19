package main.java.TalkBox;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ConfigurationApp implements TalkBoxConfiguration {

	public static int numAudioSets = 1;
	private int numButtons;
	private int currentAudioSet;
	
	ConfigurationApp()
	{
		
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
