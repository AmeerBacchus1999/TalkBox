package main.java.TalkBox;

public class TalkBoxButton implements Comparable<TalkBoxButton> {
	private int location;
	
	TalkBoxButton(int location)
	{
		this.setLocation(location);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public int compareTo(TalkBoxButton arg0) {
		return this.location - arg0.location;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TalkBoxButton)) {
			return false;
		}
		TalkBoxButton other = (TalkBoxButton) obj;
		if (location != other.location) {
			return false;
		}
		return true;
	}
	
	
	
}
