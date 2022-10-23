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
	private static JTextField _fileNameTF  = new JTextField(15);
	private static JFileChooser open = new JFileChooser();
	static JFileChooser save = new JFileChooser();
	private static Border loweredetched;
	private static int xoffset = 12, yoffset = 18, buttonWidth = 90, textHeight = 24;
	
	static void panPreset() {

		// Panel Preset
		JPanel pnlPresets = new JPanel();
		pnlPresets.setLayout(null);
		pnlPresets.setBounds(15, 15, 230, 80);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		pnlPresets.setBorder(BorderFactory.createTitledBorder(loweredetched, "Preset"));
		pnlPresets.setOpaque(false);
		WindowMixer.getCp().add(pnlPresets);

		// Open File Button
		btnOpen = new JButton("Open");
		btnOpen.setBounds(xoffset, yoffset, buttonWidth, textHeight);
		btnOpen.setToolTipText("Open file");
		btnOpen.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnOpen(event);
			}
		});
		pnlPresets.add(btnOpen);
	
		// Save File Button
		btnSave = new JButton("Save");
		btnSave.setBounds(110, yoffset, buttonWidth, textHeight);
		btnSave.setToolTipText("Save file");
		btnSave.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnSave(event);
			}
		});
		pnlPresets.add(btnSave);
		
		// Textfield Filename
		set_fileNameTF(new JTextField());
		get_fileNameTF().setBounds(xoffset, yoffset+26, 200, textHeight);
		pnlPresets.add(get_fileNameTF());
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
            get_fileNameTF().setText(file.getName());
            Presets.read(file);
	    }
	}
																
	// Save file
	protected static void handlebtnSave(ActionEvent event) {
		String SaveName;
		SaveName = get_fileNameTF().getText();
		
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
	
	public static JTextField get_fileNameTF() {
		return _fileNameTF;
	}

	public static void set_fileNameTF(JTextField _fileNameTF) {
		PanelPreset._fileNameTF = _fileNameTF;
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
