import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class WindowMixer extends JFrame {

	private static final long serialVersionUID = 3065787531046475413L;
	private final Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	private static Container cp;
	private static final JPanel[] panelArray = new JPanel[4];
	
	// Create window
	public WindowMixer(String titel) {
		super(titel);
		initGUI();
		this.setResizable(false);
		this.setVisible(true);
	}

	// Initialize window & panels
	private void initGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.setBackground(new Color(200, 200, 200));

		// Initialize Panels
		String[] panelName = {"Mixer", "Preset", "Cue", "Hex Clipboard"};
		int[] xWidthPanel =  {380, 330, 230,  300};
		int[] yHeightPanel = {475,  56, 158,  56};
		for (int i = 0; i < panelArray.length; i++) {
			panelArray[i] = new JPanel();
			panelArray[i].setPreferredSize(new Dimension(xWidthPanel[i], yHeightPanel[i]));
			panelArray[i].setOpaque(false);
			panelArray[i].setBorder(BorderFactory.createTitledBorder(loweredetched, panelName[i]));
			panelArray[i].setLayout(null);
		}
		cp.add(panelArray[0], BorderLayout.CENTER);
		cp.add(panelArray[1], BorderLayout.NORTH);
		cp.add(panelArray[2], BorderLayout.WEST);
		cp.add(panelArray[3], BorderLayout.SOUTH);
		pack();
		
		// Set content to panels
		PanelMixer.panMix();
		PanelPreset.panPreset();
		PanelCue.panCue();
		PanelClipBoard.panClip();	
	}

	static void SetWindowColor(int red, int green, int blue) {
		String StrHexOut = Functions.makeHexStr(red, green, blue);
		PanelClipBoard.getTxtOutput().setText(StrHexOut);
		Color color = new Color(red, green, blue);
		cp.setBackground(color);
		for (int i = 0; i < PanelMixer.getSliderArray().length; i++) {
			PanelMixer.getSliderArray()[i].setBackground(color);
		}
	}

	// Getter
	public static Container getCp() {
		return cp;
	}
	public static JPanel[] getPanelArray() {
		return panelArray;
	}
}