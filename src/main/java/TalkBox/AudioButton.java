package main.java.TalkBox;

import java.io.File;


public class AudioButton extends TalkBoxButton{

	private File audio;
	private File image;
	

	public AudioButton(int location)
	{
		super(location);
		this.reset();
	}
	
	public AudioButton(AudioButton a)
	{
		super(a.getLocation());
		this.audio = a.audio;
		this.image = a.image;
	}
	


	public File getAudio() {
		return audio;
	}


	public void setAudio(File audio) {
		this.audio = audio;
	}


	public File getImage() {
		return image;
	}


	public void setImage(File image) {
		this.image = image;
	}
	
	public void reset()
	{
		this.audio = null;
		this.image = null;
	}
}
