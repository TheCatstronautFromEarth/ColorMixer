import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class PanelPreset {
	
	private static JButton[] buttonArray = new JButton[2];
	private static JTextField fileNameTF  = new JTextField(15);
	private static JFileChooser open = new JFileChooser();
	static JFileChooser save = new JFileChooser();
	
	static void panPreset() {
		
		// Buttons
		String[] buttonNames = {"Open", "Save"};
		int[] xOffsetButton = {15, 110};
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton(buttonNames[i]);
			buttonArray[i].setBounds(xOffsetButton[i], 18, 90, 26);
			buttonArray[i].addActionListener(actions);
			WindowMixer.getPanelArray()[1].add(buttonArray[i]);
		}
		
		// Textfield Filename
		fileNameTF = new JTextField();
		fileNameTF.setBounds(230, 18, 200, 24);
		WindowMixer.getPanelArray()[1].add(fileNameTF);
	}
	
	// Buttons ActionListener 
	private static ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonArray[0]) {
				openFile();		
			} else if (e.getSource() == buttonArray[1]) {
				saveFile();
			}
		}
	};

	// Open file
	protected static void openFile() {
		open.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		open.addChoosableFileFilter(new colorFileFilter());
        int retval = open.showOpenDialog(open);
        if (retval == JFileChooser.APPROVE_OPTION) {
        	File file = open.getSelectedFile();
            fileNameTF.setText(file.getName());
            DiscIO.read(file);
	    }
	}

	// Save file
	protected static void saveFile() {
		String SaveName;
		SaveName = fileNameTF.getText();
		// If TextField empty -> Dafault Name
		if (SaveName.equals("")) {
			SaveName = "New_Preset.cmf";
		}
		// Set .cmf  (ColerMixerFile)
		if (!SaveName.endsWith(".cmf")) {
			SaveName = SaveName + ".cmf";
		}
		File file = new File(SaveName);
		save.setSelectedFile(file);
		int retval = save.showSaveDialog(save);
        save.setFileSelectionMode(JFileChooser.FILES_ONLY);
        save.addChoosableFileFilter(new colorFileFilter());
        if (retval == JFileChooser.APPROVE_OPTION) {
        	String Name = save.getSelectedFile().getName();
        	DiscIO.write(Name);
	    }
	}

	// FileFilter
	static class colorFileFilter extends javax.swing.filechooser.FileFilter {
	    public boolean accept(File file) {
	        String filename = file.getName();
	        return file.isDirectory() || filename.toLowerCase().endsWith(".cmf");
	    }
	    public String getDescription() {
	        return "*.cmf";
	    }
	}
	
	// Getter
	public static JTextField getfileNameTF() {
		return fileNameTF;
	}
}
