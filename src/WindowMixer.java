import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WindowMixer extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 3065787531046475413L;

	private static JButton btnSwitch;
	private static JSlider sldRed, sldGreen, sldBlue, sldMaster;
	private static JLabel lblTitleRed, lblTitleGreen, lblTitleBlue, lblTitleMaster;
	private static JLabel lblDecRed, lblDecGreen, lblDecBlue, lblTitleDec;
	private static JLabel lblHexRed, lblHexGreen, lblHexBlue, lblTitleHex;
	private static JLabel lblPercentRed, lblPercentGreen, lblPercentBlue;
	private static int intValueRed = 200, intValueGreen = 200, intValueBlue = 200;
	private static int intOldMasterValue = 200, intMasterValue = 200;
	private static String StrHexOut;
	private static boolean MasterLinear = true;
	private static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	private static Container cp;

	// Create window
	public WindowMixer(String titel) {
		super(titel);
		init();
		this.setResizable(false);
		this.setVisible(true);
	}

	// Initialize window & panels
	private void init() {
		
		Color color = new Color(intValueRed, intValueGreen, intValueBlue);

		// Create Content Pane
		setBounds(300, 310, 655, 532);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(color);

		// Set Panels
		PanelInput.panClip();
		PanelCue.panCue();
		PanelPreset.panPreset();
		PanelOutput.panOut();

		// Panel Mixer
		JPanel pnlMixer = new JPanel();
		pnlMixer.setLayout(null);
		pnlMixer.setBounds(260, 15, 380, 475);
		pnlMixer.setOpaque(false);
		pnlMixer.setBorder(BorderFactory.createTitledBorder(loweredetched, "Mixer"));
		cp.add(pnlMixer);

		// Fader Red
		sldRed = new JSlider(JSlider.VERTICAL, 0, 255, intValueRed);
		sldRed.setBounds(5, 35, 70, 360);
		sldRed.setMajorTickSpacing(10);
		sldRed.setMinorTickSpacing(5);
		sldRed.setPaintTicks(true);
		sldRed.setPaintLabels(true);
		sldRed.setSnapToTicks(false);
		sldRed.setName("Red");
		sldRed.setBackground(color);
		sldRed.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldRed);

		// Fader Green
		sldGreen = new JSlider(JSlider.VERTICAL, 0, 255, intValueGreen);
		sldGreen.setBounds(90, 35, 70, 360);
		sldGreen.setMajorTickSpacing(10);
		sldGreen.setMinorTickSpacing(5);
		sldGreen.setPaintTicks(true);
		sldGreen.setPaintLabels(true);
		sldGreen.setSnapToTicks(false);
		sldGreen.setName("Green");
		sldGreen.setBackground(color);
		sldGreen.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldGreen);

		// Fader Blue
		sldBlue = new JSlider(JSlider.VERTICAL, 0, 255, intValueBlue);
		sldBlue.setBounds(175, 35, 70, 360);
		sldBlue.setMajorTickSpacing(10);
		sldBlue.setMinorTickSpacing(5);
		sldBlue.setPaintTicks(true);
		sldBlue.setPaintLabels(true);
		sldBlue.setSnapToTicks(false);
		sldBlue.setName("Blue");
		sldBlue.setBackground(color);
		sldBlue.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldBlue);

		// Master Fader
		sldMaster = new JSlider(JSlider.VERTICAL, 0, 255, 128);
		sldMaster.setBounds(280, 35, 70, 360);
		sldMaster.setMajorTickSpacing(10);
		sldMaster.setMinorTickSpacing(5);
		sldMaster.setPaintTicks(true);
		sldMaster.setPaintLabels(false);
		sldMaster.setSnapToTicks(false);
		sldMaster.setName("Master");
		sldMaster.setBackground(color);
		sldMaster.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldMaster);

		// Label Red Name
		lblTitleRed = new JLabel("Red");
		lblTitleRed.setBounds(30, 15, 100, 24);
		pnlMixer.add(lblTitleRed);
		// Label Green Name
		lblTitleGreen = new JLabel("Green");
		lblTitleGreen.setBounds(115, 15, 100, 24);
		pnlMixer.add(lblTitleGreen);
		// Label Blue Name
		lblTitleBlue = new JLabel("Blue");
		lblTitleBlue.setBounds(200, 15, 100, 24);
		pnlMixer.add(lblTitleBlue);
		// Label Master Name
		lblTitleMaster = new JLabel("Master");
		lblTitleMaster.setBounds(295, 15, 100, 24);
		pnlMixer.add(lblTitleMaster);

		// Label Red Percent
		lblPercentRed = new JLabel("78 %");
		lblPercentRed.setBounds(30, 400, 100, 24);
		pnlMixer.add(lblPercentRed);
		// Label Green Percent
		lblPercentGreen = new JLabel("78 %");
		lblPercentGreen.setBounds(115, 400, 100, 24);
		pnlMixer.add(lblPercentGreen);
		// Label Blue Percent
		lblPercentBlue = new JLabel("78 %");
		lblPercentBlue.setBounds(200, 400, 100, 24);
		pnlMixer.add(lblPercentBlue);

		// Text Red DEC Value
		lblDecRed = new JLabel(Integer.toString(intValueRed));
		lblDecRed.setBounds(30, 420, 100, 24);
		pnlMixer.add(lblDecRed);
		// Text Green DEC Value
		lblDecGreen = new JLabel(Integer.toString(intValueGreen));
		lblDecGreen.setBounds(115, 420, 100, 24);
		pnlMixer.add(lblDecGreen);
		// Text Blue DEC Value
		lblDecBlue = new JLabel(Integer.toString(intValueBlue));
		lblDecBlue.setBounds(200, 420, 100, 24);
		pnlMixer.add(lblDecBlue);

		// Text Red HEX Value
		lblHexRed = new JLabel(Integer.toHexString(intValueRed));
		lblHexRed.setBounds(30, 440, 100, 24);
		pnlMixer.add(lblHexRed);
		// Text Green HEX Value
		lblHexGreen = new JLabel(Integer.toHexString(intValueGreen));
		lblHexGreen.setBounds(115, 440, 100, 24);
		pnlMixer.add(lblHexGreen);
		// Text Blue HEX Value
		lblHexBlue = new JLabel(Integer.toHexString(intValueBlue));
		lblHexBlue.setBounds(200, 440, 100, 24);
		pnlMixer.add(lblHexBlue);
		
		// Label DEC Value
		lblTitleDec = new JLabel("Dec");
		lblTitleDec.setBounds(300, 420, 100, 24);
		pnlMixer.add(lblTitleDec);
		// Label HEX Value
		lblTitleHex = new JLabel("Hex");
		lblTitleHex.setBounds(300, 440, 100, 24);
		pnlMixer.add(lblTitleHex);

		// Button Switch Master Log-Linear
		String btnText = "Linear";
		btnSwitch = new JButton(btnText);
		btnSwitch.setBounds(270, 400, 90, 24);
		btnSwitch.setToolTipText("Switch Masterfader");
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Linear")) {
					btnSwitch.setText("Log");
					MasterLinear = false;
				} else {
					btnSwitch.setText("Linear");
					MasterLinear = true;
				}
			}
		});
		pnlMixer.add(btnSwitch);
	}

	// If Fader Event Change
	public void stateChanged(ChangeEvent evt) {
		JSlider source;
		source = (JSlider) evt.getSource();

		// If change Master Slider
		if (source.getName().equals("Master")) {
			intOldMasterValue = intMasterValue;
			intMasterValue = source.getValue();
			
			// If Masterfader moves up
			if (intOldMasterValue < intMasterValue) {
				masterSliderUp(MasterLinear);
			}
			
			// If Masterfader moves down
			if (intOldMasterValue > intMasterValue) {
				masterSliderDown(MasterLinear);
			}
		}

		// If change RGB sliders
		else {
			// If changed red slider
			if (source.getName().equals("Red")) {
				intValueRed = source.getValue();
				redSliderChange();
			}
			// If changed green slider
			if (source.getName().equals("Green")) {
				intValueGreen = source.getValue();
				greenSliderChange();
			}
			// If changed blue slider
			if (source.getName().equals("Blue")) {
				intValueBlue = source.getValue();
				blueSliderChange();
			}
		}
		
		// Set text and slider value 6 Digits !!! 00
		StrHexOut = (intValueRed < 16 ? "0" : "")
				+ Integer.toHexString(intValueRed)
				+ (intValueGreen < 16 ? "0" : "")
				+ Integer.toHexString(intValueGreen)
				+ (intValueBlue < 16 ? "0" : "")
				+ Integer.toHexString(intValueBlue);
		
		PanelOutput.getTxtOutput().setText(StrHexOut);
		Color color = new Color(intValueRed, intValueGreen, intValueBlue);
		cp.setBackground(color);
		sldRed.setBackground(color);
		sldGreen.setBackground(color);
		sldBlue.setBackground(color);
		sldMaster.setBackground(color);
	}

	private void redSliderChange() {
		lblDecRed.setText(Integer.toString(intValueRed)); 					// Label Red Dec value
		lblHexRed.setText(Integer.toHexString(intValueRed)); 				// Label Red Hex
		lblPercentRed.setText(Functions.DecToPercent(intValueRed)); 		// Label Red Percent
	}

	private void greenSliderChange() {
		lblDecGreen.setText(Integer.toString(intValueGreen)); 				// Label Green Dec
		lblHexGreen.setText(Integer.toHexString(intValueGreen)); 			// Label Green Hex
		lblPercentGreen.setText(Functions.DecToPercent(intValueGreen)); 	// Label Green Percent
	}

	private void blueSliderChange() {
		lblDecBlue.setText(Integer.toString(intValueBlue)); 				// Label Blue Dec
		lblHexBlue.setText(Integer.toHexString(intValueBlue));				// Label Blue Hex										
		lblPercentBlue.setText(Functions.DecToPercent(intValueBlue));		// Label Blue Percent
	}

	// Set RGB-Slider when MasterSlider moves up
	private void masterSliderUp(boolean masterLinear) {
		if (masterLinear) {
			sldRed.setValue(sldRed.getValue() + 1);
			sldGreen.setValue(sldGreen.getValue() + 1);
			sldBlue.setValue(sldBlue.getValue() + 1);
		} else {
			sldRed.setValue((int) (sldRed.getValue() * 1.01 + 1));
			sldGreen.setValue((int) (sldGreen.getValue() * 1.01 + 1));
			sldBlue.setValue((int) (sldBlue.getValue() * 1.01 + 1));
		}
	}

	// Set RGB-Slider when MasterSlider moves down
	private void masterSliderDown(boolean masterLinear) {
		if (masterLinear) {
			sldRed.setValue(sldRed.getValue() - 1);
			sldGreen.setValue(sldGreen.getValue() - 1);
			sldBlue.setValue(sldBlue.getValue() - 1);
		} else {
			sldRed.setValue((int) (sldRed.getValue() / 1.01));
			sldGreen.setValue((int) (sldGreen.getValue() / 1.01));
			sldBlue.setValue((int) (sldBlue.getValue() / 1.01));
		}
	}

	// Setter Getter
	public static int getIntValueRed() {
		return intValueRed;
	}
	public static int getIntValueGreen() {
		return intValueGreen;
	}
	public static int getIntValueBlue() {
		return intValueBlue;
	}
	public static JSlider getSldRed() {
		return sldRed;
	}
	public static JSlider getSldGreen() {
		return sldGreen;
	}
	public static JSlider getSldBlue() {
		return sldBlue;
	}
	public static void setIntMasterValue(int intMasterValue) {
		WindowMixer.intMasterValue = intMasterValue;
	}
	public static String getStrHexOut() {
		return StrHexOut;
	}
	public static void setStrHexOut(String StrHexOut) {
		WindowMixer.StrHexOut = StrHexOut;
	}
	public static Container getCp() {
		return cp;
	}
}