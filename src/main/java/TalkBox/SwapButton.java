package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ranges.Range;


public class SwapButton implements TalkBoxButton{
	private int range;
	private int currentAudioSet;
	
	public SwapButton(int range, int currentAudioSet)
	{
		this.setCurrentAudioSet(currentAudioSet);
		this.setRange(1);
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getCurrentAudioSet() {
		return currentAudioSet;
	}

	public void setCurrentAudioSet(int currentAudioSet) {
		this.currentAudioSet = currentAudioSet;
	}
	
	public void alternateAudioSet()
	{
		this.setCurrentAudioSet((currentAudioSet + 1)% range);
	}

	

}
