package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class TalkBoxButton {
	private int location;
	private Set<Integer> inAudioSets = new TreeSet<Integer>();
	
	protected TalkBoxButton(int location)
	{
		this.location = location;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public void addAudioSet(int audioSet)
	{
		this.inAudioSets.add(audioSet);
	}
	
	public Set<Integer> getAudioSets()
	{
		return this.inAudioSets;
	}
	
}
