package main.java.TalkBox;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SwapButton implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5432020367114207445L;
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
		
	
	public Integer next()
	{
		if(counter == range())
		{
			this.counter = 0;
		}
		return values.get(counter++ % range());
	}
	
}
