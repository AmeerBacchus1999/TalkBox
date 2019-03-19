package main.java.TalkBox;

import java.util.List;

public class SwapButton extends TalkBoxButton{
	private List<Integer> values;
	private int counter;
	
	
	public SwapButton()
	{
		super();
	}
	
	
	
	private int getCounter() {
		return counter;
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
	
	
	public int next()
	{
		return values.get(counter++ % range());
	}
	
}
