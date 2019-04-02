
package main.java.TalkBox;


import java.awt.Color;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SetButton implements DropTargetListener {

	JButton pic = new JButton();
	File sound;

	AudioButton button;
	
	
	
public SetButton(JButton pic,AudioButton button) {
	
	
		this.pic = pic;
		this.button = button;
		
		if (this.button.getAudio() !=null) {
			
			this.sound = new File(button.getAudio());
			this.pic.setBorder(BorderFactory.createLineBorder(Color.ORANGE,4));
		}

		
		if (this.button.getImage()!=null) {
		
		BufferedImage picture = null;
		
		try {
			
			
			picture = ImageIO.read(new File(button.getImage()));
			
		}
		
		catch(Exception e) {
			
		}
		
		ImageIcon icon = new ImageIcon(picture);
		
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
		
		icon = new ImageIcon(newImage);
		this.pic.setText("");
		this.pic.setIcon(icon);
		}
		
		
		
	}


	@Override
	public void drop(DropTargetDropEvent arg) {
		// TODO Auto-generated method stub
		
		arg.acceptDrop(DnDConstants.ACTION_COPY);
		
		Transferable t = arg.getTransferable();
		
		DataFlavor[] df = t.getTransferDataFlavors();
		
		for (DataFlavor curr : df) {
			
			try {
				
				if (curr.isFlavorJavaFileListType()) {
					
					List<File> files = (List<File>) t.getTransferData(curr);
					
					for (File i : files) {
						
						if (i.getPath().substring(i.getPath().length()-4, i.getPath().length()).equals(".wav")) {
							
						
							
							changeSound(i.getPath());
						}
						else {
						display(i.getPath());
						}
					}
				}
			}
			catch(Exception e) {
				
				
			}
		}
		
	}
	
	private void display(String arg) {
		
		
		
		Log.getLogger().log(Level.FINE, "Dropped Picture - "+ arg);
		
		
		button.setImage(arg);
		
		BufferedImage pic = null;
		
		try {
			
			
			pic = ImageIO.read(new File(arg));
			
		}
		
		catch(Exception e) {
			
		}
		
		ImageIcon icon = new ImageIcon(pic);
		
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
		
		icon = new ImageIcon(newImage);
		this.pic.setText("");
		this.pic.setIcon(icon);
		

	}
	
	private void changeSound(String arg) {
		
		
		Log.getLogger().log(Level.FINE, "Dropped sound file - "+ arg);
		
		button.setAudio(arg);
		this.sound = new File(arg);
		this.pic.setBorder(BorderFactory.createLineBorder(Color.ORANGE,4));
	
		
	}



	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dragOver(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}