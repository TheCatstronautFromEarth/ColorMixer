import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelPreset {
	
	private static JButton btnOpen, btnSave;
	private static JTextField fileNameTF  = new JTextField(15);
	private static JFileChooser open = new JFileChooser();
	static JFileChooser save = new JFileChooser();
	private static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	static void panPreset() {

		// Panel Presets
		JPanel pnlPresets = new JPanel();
		pnlPresets.setLayout(null);
		pnlPresets.setBounds(15, 15, 230, 80);
		pnlPresets.setBorder(BorderFactory.createTitledBorder(loweredetched, "Preset"));
		pnlPresets.setOpaque(false);
		WindowMixer.getCp().add(pnlPresets);

		// Button Open File 
		btnOpen = new JButton("Open");
		btnOpen.setBounds(12, 18, 90, 24);
		btnOpen.addActionListener(actions);
		pnlPresets.add(btnOpen);
	
		// Button Save File
		btnSave = new JButton("Save");
		btnSave.setBounds(110, 18, 90, 24);
		btnSave.addActionListener(actions);
		pnlPresets.add(btnSave);
		
		// Textfield Filename
		fileNameTF = new JTextField();
		fileNameTF.setBounds(12, 18+26, 200, 24);
		pnlPresets.add(fileNameTF);
	}
	
	// Buttons ActionListener 
	private static ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnOpen) {
				openFile();		
			} else if (e.getSource() == btnSave) {
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
            Presets.read(file);
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
        	Presets.write(Name);
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
