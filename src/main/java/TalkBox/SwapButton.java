package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;


public class SwapButton extends TalkBoxButton{
	private List<Integer> possibleAudioSets;
	private int currentAudioSet;
	
	public SwapButton()
	{
		super(0);
	}
	
	public SwapButton(int location, int currentAudioSet, List<Integer> possibleAudioSets)
	{
		super(location);
		this.setAudioSets(currentAudioSet, possibleAudioSets);
	}
	
	public List<Integer> getPossibleAudioSets() {
		return possibleAudioSets;
	}

	public int getCurrentAudioSet() {
		return currentAudioSet;
	}

	
	
	public void setAudioSets(int currentAudioSet, List<Integer> possibleAudioSets)
	{
		this.currentAudioSet = currentAudioSet;
		this.possibleAudioSets = possibleAudioSets;
	}
	
	public void alternateAudioSets()
	{
		int get = this.possibleAudioSets.indexOf(this.currentAudioSet);
		this.currentAudioSet = this.possibleAudioSets.get(
				(get+1) % this.possibleAudioSets.size()
				);
	}
	
	
}
