package test.java.TalkBox;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import main.java.TalkBox.AudioButton;

class AudioButtonTest {

	@Test
	void location()
	{
		int alocation = 15;
		AudioButton a = new AudioButton(alocation);
		assertEquals(a.getLocation(), alocation);
	}
	
	@Test
	void getImage()
	{
		int alocation = 15;
		File image = new File(".");
		AudioButton a = new AudioButton(alocation);
		a.setImage(image);
		assertEquals(image, a.getImage());
	}
	
	@Test
	void getAudio()
	{
		int alocation = 15;
		File audio = new File(".");
		AudioButton a = new AudioButton(alocation);
		a.setAudio(audio);
		assertEquals(audio, a.getAudio());
	}


}
