package main.java.TalkBox;

import java.nio.file.Path;

public class AudioButton extends TalkBoxButton{

	private Path audio;
	private Path image;
	
	public AudioButton()
	{
		super(0);
	}
	
	public AudioButton(int location, int audioSet, Path audio, Path image)
	{
		super(location);
		super.addAudioSet(audioSet);
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
