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
	private static JLabel lblRed, lblGreen, lblBlue;
	private static JLabel txtRed, txtGreen, txtBlue;
	private static JLabel hexRed, hexGreen, hexBlue;
	private static JLabel lblRedPercent, lblGreenPercent, lblBluePercent;
	private static JLabel txtMaster, txtDec, txtHex;
	private static int valueRed = 200, valueGreen = 200, valueBlue = 200;
	private static int oldMasterValue = 200, masterValue = 200;
	private static String txtOut;
	private static boolean MasterLog = false;
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
		
		// Create Content Pane
		setBounds(300, 310, 655, 532);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(valueRed, valueGreen, valueBlue));

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
		sldRed = new JSlider(JSlider.VERTICAL, 0, 255, valueRed);
		sldRed.setBounds(5, 35, 70, 360);
		sldRed.setMajorTickSpacing(10);
		sldRed.setMinorTickSpacing(5);
		sldRed.setPaintTicks(true);
		sldRed.setPaintLabels(true);
		sldRed.setSnapToTicks(false);
		sldRed.setName("Red");
		sldRed.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldRed.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldRed);	
		
		// Fader Green
		sldGreen = new JSlider(JSlider.VERTICAL, 0, 255, valueGreen);
		sldGreen.setBounds(90, 35, 70, 360);
		sldGreen.setMajorTickSpacing(10);
		sldGreen.setMinorTickSpacing(5);
		sldGreen.setPaintTicks(true);
		sldGreen.setPaintLabels(true);
		sldGreen.setSnapToTicks(false);
		sldGreen.setName("Green");
		sldGreen.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldGreen.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldGreen);
		
		// Fader Blue
		sldBlue = new JSlider(JSlider.VERTICAL, 0, 255, valueBlue);
		sldBlue.setBounds(175, 35, 70, 360);
		sldBlue.setMajorTickSpacing(10);
		sldBlue.setMinorTickSpacing(5);
		sldBlue.setPaintTicks(true);
		sldBlue.setPaintLabels(true);
		sldBlue.setSnapToTicks(false);
		sldBlue.setName("Blue");
		sldBlue.setBackground(new Color(valueRed, valueGreen, valueBlue));
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
		sldMaster.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldMaster.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldMaster);
		

		// Label Red Name
		lblRed = new JLabel("Red");
		lblRed.setBounds(30, 15, 100, 24);
		pnlMixer.add(lblRed);

		// Label Green Name
		lblGreen = new JLabel("Green");
		lblGreen.setBounds(115, 15, 100, 24);
		pnlMixer.add(lblGreen);
		
		// Label Blue Name
		lblBlue = new JLabel("Blue");
		lblBlue.setBounds(200, 15, 100, 24);
		pnlMixer.add(lblBlue);
		
		// Label Master Name
		txtMaster = new JLabel("Master");
		txtMaster.setBounds(295, 15, 100, 24);
		pnlMixer.add(txtMaster);
		

		// Label Red Percent
		lblRedPercent = new JLabel("78 %");
		lblRedPercent.setBounds(30, 400, 100, 24);
		pnlMixer.add(lblRedPercent);
		
		// Label Green Percent
		lblGreenPercent = new JLabel("78 %");
		lblGreenPercent.setBounds(115, 400, 100, 24);
		pnlMixer.add(lblGreenPercent);
		
		// Label Blue Percent
		lblBluePercent = new JLabel("78 %");
		lblBluePercent.setBounds(200, 400, 100, 24);
		pnlMixer.add(lblBluePercent);
		
		
		// Text Red DEC Value
		txtRed = new JLabel(valueRed + " ");
		txtRed.setBounds(30, 400+20, 100, 24);
		pnlMixer.add(txtRed);
		
		// Text Green DEC Value
		txtGreen = new JLabel(valueGreen + " ");
		txtGreen.setBounds(115, 400+20, 100, 24);
		pnlMixer.add(txtGreen);
		
		// Text Blue DEC Value
		txtBlue = new JLabel(valueBlue + " ");
		txtBlue.setBounds(200, 400+20, 100, 24);
		pnlMixer.add(txtBlue);
	

		// Text Red HEX Value
		hexRed = new JLabel(Integer.toHexString(valueRed) + " ");
		hexRed.setBounds(30, 400+40, 100, 24);
		pnlMixer.add(hexRed);
		
		// Text Green HEX Value
		hexGreen = new JLabel(Integer.toHexString(valueGreen) + " ");
		hexGreen.setBounds(115, 400+40, 100, 24);
		pnlMixer.add(hexGreen);

		// Text Blue HEX Value
		hexBlue = new JLabel(Integer.toHexString(valueBlue) + " ");
		hexBlue.setBounds(200, 400+40, 100, 24);
		pnlMixer.add(hexBlue);

		
		// Label DEC Value
		txtDec = new JLabel("Dec");
		txtDec.setBounds(300, 400+20, 100, 24);
		pnlMixer.add(txtDec);

		// Label HEX Value
		txtHex = new JLabel("Hex");
		txtHex.setBounds(300, 400+40, 100, 24);
		pnlMixer.add(txtHex);	

		// Button Switch Master Log-Linear
		String btnText = "Linear";
		btnSwitch = new JButton(btnText);
		btnSwitch.setBounds(270, 400, 90, 24);
		btnSwitch.setToolTipText("Switch Masterfader");
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				handlebtnSwitch(event);
			}
		});
		pnlMixer.add(btnSwitch);
	}
	

	public void stateChanged(ChangeEvent evt) {
		JSlider source;
		source = (JSlider) evt.getSource();
		
		// If change Master Slider
		if (source.getName().equals("Master")) {
			oldMasterValue = masterValue;
			setMasterValue(source.getValue());

			// If Masterfader moves up
			if (oldMasterValue < masterValue) { 
				
				// If Linear Mode
				if (MasterLog == false) { 
					if (valueRed >= 0 && valueRed <= 255) {
						sldRed.setValue(sldRed.getValue() + 1);
					}
					if (valueGreen >= 0 && valueGreen <= 255) {
						sldGreen.setValue(sldGreen.getValue() + 1);
					}
					if (valueBlue >= 0 && valueBlue <= 255) {
						sldBlue.setValue(sldBlue.getValue() + 1);
					}
				}

				// If Log Mode
				if (MasterLog == true) { 
					if (valueRed >= 0 && valueRed <= 255) {
						sldRed.setValue((int) (sldRed.getValue() * 1.01 + 1));
					}
					if (valueGreen >= 0 && valueGreen <= 255) {
						sldGreen.setValue((int) (sldGreen.getValue() * 1.01 + 1));
					}
					if (valueBlue >= 0 && valueBlue <= 255) {
						sldBlue.setValue((int) (sldBlue.getValue() * 1.01 + 1));
					}
				}

			}
			
			
			// If Masterfader moves down
			if (oldMasterValue > masterValue) { 

				// If Linear Mode
				if (MasterLog == false) { 
					if (valueRed >= 0 && valueRed <= 255) {
						sldRed.setValue(sldRed.getValue() - 1);
					}
					if (valueGreen >= 0 && valueGreen <= 255) {
						sldGreen.setValue(sldGreen.getValue() - 1);
					}
					if (valueBlue >= 0 && valueBlue <= 255) {
						sldBlue.setValue(sldBlue.getValue() - 1);
					}
				}
				
				// If Log Mode
				if (MasterLog == true) { 
					if (valueRed >= 0 && valueRed <= 255) {
						sldRed.setValue((int) (sldRed.getValue() / 1.01));
					}
					if (valueGreen >= 0 && valueGreen <= 255) {
						sldGreen.setValue((int) (sldGreen.getValue() / 1.01));
					}
					if (valueBlue >= 0 && valueBlue <= 255) {
						sldBlue.setValue((int) (sldBlue.getValue() / 1.01));
					}
				}
			}
		
		} 
		
		
		// If change RGB sliders
		else {
			
			// If changed red slider
			if (source.getName().equals("Red")) {
				valueRed = source.getValue();
				txtRed.setText(source.getValue() + " ");
				hexRed.setText((valueRed < 16 ? "0" : "") + Integer.toHexString(source.getValue()) + " ");
				lblRedPercent.setText(Integer.toString((valueRed * 100) / 255) + " %");
			}
			
			// If changed green slider
			if (source.getName().equals("Green")) {
				valueGreen = source.getValue();
				txtGreen.setText(source.getValue() + " ");
				hexGreen.setText((valueGreen < 16 ? "0" : "") + Integer.toHexString(source.getValue()) + " ");
				lblGreenPercent.setText(Integer.toString((valueGreen * 100) / 255) 	+ " %");
			}
			
			// If changed blue slider
			if (source.getName().equals("Blue")) {
				valueBlue = source.getValue();
				txtBlue.setText(source.getValue() + " ");
				hexBlue.setText((valueBlue < 16 ? "0" : "") + Integer.toHexString(source.getValue()) + " ");
				lblBluePercent.setText(Integer.toString((valueBlue * 100) / 255) + " %");
			}
			
			// Set text and slider value
			txtOut = (valueRed < 16 ? "0" : "") + Integer.toHexString(valueRed)
					+ (valueGreen < 16 ? "0" : "") + Integer.toHexString(valueGreen)
					+ (valueBlue < 16 ? "0" : "") + Integer.toHexString(valueBlue);
			
			PanelOutput.getTxtOutput().setText(String.valueOf(txtOut));
			cp.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldRed.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldGreen.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldBlue.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldMaster.setBackground(new Color(valueRed, valueGreen, valueBlue));		
		}
	}

	
	// Handle Button Log-Linear switch
	protected static void handlebtnSwitch(ActionEvent event) { 

		if (event.getActionCommand().equals("Linear")) {
			btnSwitch.setText("Log");
			MasterLog = true;
		} else {
			btnSwitch.setText("Linear");
			MasterLog = false;
		}
	}

	
	// Setter Getter
	public static int getValueRed() {
		return valueRed;
	}

	public static int getValueGreen() {
		return valueGreen;
	}

	public static int getValueBlue() {
		return valueBlue;
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

	public static void setMasterValue(int masterValue) {
		WindowMixer.masterValue = masterValue;
	}

	public static String getTxtOut() {
		return txtOut;
	}

	public static void setTxtOut(String txtOut) {
		WindowMixer.txtOut = txtOut;
	}

	public static Container getCp() {
		return cp;
	}


}