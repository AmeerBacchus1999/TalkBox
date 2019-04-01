
package main.java.TalkBox;
import java.io.Serializable;

public class AudioButton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2565554560368818811L;
	/**
	 * 
	 */
	private String audio;
	private String image;
	

	public AudioButton()
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
