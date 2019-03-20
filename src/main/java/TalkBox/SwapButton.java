package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;

public class SwapButton extends TalkBoxButton{
	private List<Integer> values;
	private int counter;
	
	
	public SwapButton(int location)
	{
		super(location);
		this.setCounter(0);
		resetValues();
	}

	private void setCounter(int counter) {
		this.counter = counter;
	}

	private int range()
	{
		return values.size();
	}
	
	public void addValue(int value)
	{
		values.add(value);
	}
	
	public void removeValue(int i)
	{
		values.remove(i);
		this.setCounter(0);
	}
	
	public void resetValues()
	{
		this.values = new ArrayList<Integer>();
	}
	
	
	public int next()
	{
		if(counter == range())
		{
			this.setCounter(0);
		}
		return values.get(counter++ % range());
	}
	
}
