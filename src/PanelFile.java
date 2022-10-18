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

public class PanelFile {
	
	static JButton btnOpen, btnSave;
	static JTextField _fileNameTF  = new JTextField(15);
	static JFileChooser open = new JFileChooser();
	static JFileChooser save = new JFileChooser();
	static Border loweredetched;
	static int xoffset = 12, yoffset = 18, buttonWidth = 90, textHight = 24;
	
	static void panFile() {

		// Panel Preset
		JPanel pnlPresets = new JPanel();
		pnlPresets.setLayout(null);
		pnlPresets.setBounds(15, 15, 230, 80);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		pnlPresets.setBorder(BorderFactory.createTitledBorder(loweredetched, "Preset"));
		pnlPresets.setOpaque(false);
		ColorMixer.cp.add(pnlPresets);

		// Open File Button
		btnOpen = new JButton("Open");
		btnOpen.setBounds(xoffset, yoffset, buttonWidth, textHight);
		btnOpen.setToolTipText("Open file");
		btnOpen.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnOpen(event);
			}
		});
		pnlPresets.add(btnOpen);
	
		// Save File Button
		btnSave = new JButton("Save");
		btnSave.setBounds(110, yoffset, buttonWidth, textHight);
		btnSave.setToolTipText("Save file");
		btnSave.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnSave(event);
			}
		});
		pnlPresets.add(btnSave);
		
		// Textfield Filename
		_fileNameTF = new JTextField();
		_fileNameTF.setBounds(xoffset, yoffset+26, 200, textHight);
		pnlPresets.add(_fileNameTF);
	}
	
	// Open file
	protected static void handlebtnOpen(ActionEvent event) {
		open.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		open.addChoosableFileFilter(new MyFilter());
        int retval = open.showOpenDialog(open);
        if (retval == JFileChooser.APPROVE_OPTION) {
            //... The user selected a file, get it, use it.
        	File file = open.getSelectedFile();

            //... Update user interface.
            _fileNameTF.setText(file.getName());
            Presets.read(file);
	    }
	}
																
	// Save file
	protected static void handlebtnSave(ActionEvent event) {
		String SaveName;
		SaveName = _fileNameTF.getText();
		
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
        save.addChoosableFileFilter(new MyFilter());
        if (retval == JFileChooser.APPROVE_OPTION) {
        	String Name = save.getSelectedFile().getName();
        	Presets.write(Name);
	    }
	}
	
	static class MyFilter extends javax.swing.filechooser.FileFilter {
	    public boolean accept(File file) {
	        String filename = file.getName();
	        return file.isDirectory() || filename.toLowerCase().endsWith(".cmf");
	    }
	    public String getDescription() {
	        return "*.cmf";
	    }
	}
}
