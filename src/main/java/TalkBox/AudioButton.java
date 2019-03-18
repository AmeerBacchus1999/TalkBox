package main.java.TalkBox;

import java.nio.file.Path;

public class AudioButton implements TalkBoxButton{

	private Path audio;
	private Path image;
	
	public AudioButton()
	{
		
	}
	
	public AudioButton(Path audio, Path image)
	{
		this.setAudio(audio);
		this.setImage(image);
	}


	public Path getAudio() {
		return audio;
	}


	public void setAudio(Path audio) {
		this.audio = audio;
	}


	public Path getImage() {
		return image;
	}


	public void setImage(Path image) {
		this.image = image;
	}

}
