import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenueBar {

	private static JMenuItem info;
	private static JMenuItem quit;
	private static JMenuItem open;
	private static JMenuItem save;
	private final static String InfoText =  "<html>ColorMixer v1.5<br>Released on 14. Nov 2022<br><a href='https://github.com/TheCatstronautFromEarth/ColorMixer'>https://github.com/TheCatstronautFromEarth/ColorMixer</a></html>";

	public static void InitMenueBar(WindowMixer windowMixer) {
		
		JMenuBar menueBar = new JMenuBar();
		windowMixer.setJMenuBar(menueBar);
		
		JMenu colorMixer = new JMenu("ColorMixer");
		menueBar.add(colorMixer);
		
		info = new JMenuItem("Info");
		info.addActionListener(actions);
		colorMixer.add(info);
		colorMixer.addSeparator();
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(actions);
		colorMixer.add(quit);
		
		JMenu menueFile = new JMenu("File");
		menueBar.add(menueFile);
		
		open = new JMenuItem("Open");
		open.addActionListener(actions);
		menueFile.add(open);
		
		save = new JMenuItem("Save as..");
		save.addActionListener(actions);
		menueFile.add(save);
	}
	
	// Menue ActionListener 
	private static ActionListener actions = e -> {
		if (e.getSource() == info ) {
			JOptionPane.showMessageDialog(null, InfoText, "ColorMixer",
			        JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == quit) {
			System.exit(0);
		} else if (e.getSource() == open) {
			DiscIO.openFile();
		} else if (e.getSource() == save) {
			DiscIO.saveFile();
		}
	};
}
