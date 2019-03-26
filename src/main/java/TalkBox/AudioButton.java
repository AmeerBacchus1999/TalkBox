package main.java.TalkBox;

import java.io.File;


public class AudioButton {

	private String audio;
	private String image;
	

	public AudioButton(int location)
	{
		this.reset();
	}
	
	
	
	public String getAudio() {
		return audio;
	}



	public void setAudio(String audio) {
		this.audio = audio;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public void reset()
	{
		this.audio = null;
		this.image = null;
	}
}
