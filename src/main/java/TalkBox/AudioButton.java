package main.java.TalkBox;

import java.io.File;
import java.nio.file.Path;

public class AudioButton extends TalkBoxButton{

	private File audio;
	private File image;
	

	public AudioButton(int location)
	{
		super(location);
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

}
