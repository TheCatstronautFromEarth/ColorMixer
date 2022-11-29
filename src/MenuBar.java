import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar {

    private static JMenuItem info;
    private static JMenuItem quit;
    private static JMenuItem open;
    private static JMenuItem save;
    private final static String InfoText = "<html>ColorMixer v1.7<br>Released on 29. Nov 2022<br><a href='https://github.com/TheCatstronautFromEarth/ColorMixer'>https://github.com/TheCatstronautFromEarth/ColorMixer</a></html>";

    public static void InitMenuBar(WindowMixer windowMixer) {

        JMenuBar menuBar = new JMenuBar();
        windowMixer.setJMenuBar(menuBar);

        JMenu colorMixer = new JMenu("ColorMixer");
        menuBar.add(colorMixer);

        info = new JMenuItem("Info");
        info.addActionListener(actions);
        colorMixer.add(info);
        colorMixer.addSeparator();

        quit = new JMenuItem("Quit");
        quit.addActionListener(actions);
        colorMixer.add(quit);

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        open = new JMenuItem("Open");
        open.addActionListener(actions);
        menuFile.add(open);
        menuFile.addSeparator();

        save = new JMenuItem("Save as..");
        save.setEnabled(false);
        save.addActionListener(actions);
        menuFile.add(save);

    }

    // Menu ActionListener
    private static final ActionListener actions = e -> {
        if (e.getSource() == info) {
            JOptionPane.showMessageDialog(null, InfoText, "ColorMixer", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == quit) {
            System.exit(0);
        } else if (e.getSource() == open) {
            DiscIO.openFile();
        } else if (e.getSource() == save) {
            DiscIO.saveFile();
        }
    };

    // Getter
    public static JMenuItem getSave() {
        return save;
    }
}
