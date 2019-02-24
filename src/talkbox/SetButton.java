package talkbox;


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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SetButton implements DropTargetListener {

	JButton pic = new JButton();
	File sound;
	int num;
	public ArrayList<String> AudioFileNames = new ArrayList<String>();

	
	public SetButton(JButton pic, int num) {
		
		this.pic = pic;
		this.num = num;
		
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
							
							AudioFileNames.add(i.getName());
							
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
	
		BufferedImage pic = null;
		
		try {
			
			
			pic = ImageIO.read(new File(arg));
			
		}
		
		catch(Exception e) {
			
		}
		
		ImageIcon icon = new ImageIcon(pic);
		if (TalkBoxFrame.check == false) {
		this.pic.setIcon(icon);
		}
	}
	
	private void changeSound(String arg) {
		
		if (TalkBoxFrame.check == false) {
			
		File Source = new File(arg);
		File Destination = new File(TalkBoxFrame.Audio_Sets[this.num].getPath()+"/"+Source.getName());
		File Destination2 = new File(TalkBoxFrame.Audio.getPath()+"/"+Source.getName());
		
		try {
			Files.copy(Source.toPath(), Destination.toPath(),StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Source.toPath(), Destination2.toPath(),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		
		
		
		this.sound = new File(arg);
		}
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