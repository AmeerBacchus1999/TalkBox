package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;

public class SwapButton implements IteratorNoRemovals<Integer>{
	private List<Integer> values;
	private int counter;
	
	public SwapButton(List<Integer> values)
	{
		this.values = values;
		this.counter = 0;
	}

	private int range()
	{
		return values.size();
	}
		
	@Override
	public Integer next()
	{
		if(counter == range())
		{
			this.counter = 0;
		}
		return values.get(counter++ % range());
	}
	
}
