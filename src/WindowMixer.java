import java.awt.Color;
import java.awt.Container;

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
		this.setResizable(false);
		initGUI();
		MenueBar.InitMenueBar(this);
		this.setVisible(true);
	}

	// Initialize window & panels
	private void initGUI() {
		setBounds(500, 200, 640, 630);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(200, 200, 200));

		// Initialize Panels
		String[] panelName = { "Cue", "Mixer", "Output", "Input" };
		int[] xOffestPanel = { 10, 245, 10, 245 };
		int[] yOffsetPanel = { 5, 5, 485, 485 };
		int[] xWidthPanel = { 230, 370, 230, 370 };
		int[] yHeightPanel = { 480, 480, 70, 70 };
		for (int i = 0; i < panelArray.length; i++) {
			panelArray[i] = new JPanel();
			panelArray[i].setBounds(xOffestPanel[i], yOffsetPanel[i], xWidthPanel[i], yHeightPanel[i]);
			panelArray[i].setOpaque(false);
			panelArray[i].setBorder(BorderFactory.createTitledBorder(loweredetched, panelName[i]));
			panelArray[i].setLayout(null);
			cp.add(panelArray[i]);
		}
		// Set content to panels
		PanelCue.panCue();
		PanelMixer.panMix();
		PanelOutput.panOutput();
		PanelInput.panInput();
	}

	static void SetWindowColor(int red, int green, int blue) {
		String StrHexOut = Functions.makeHexStr(red, green, blue);
		PanelOutput.getTxtOutput().setText(StrHexOut);
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