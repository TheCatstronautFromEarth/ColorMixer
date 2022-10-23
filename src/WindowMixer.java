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
		setCp(getContentPane());
		getCp().setLayout(null);
		getCp().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));

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
		getCp().add(pnlMixer);

		
		// Fader Red
		setSldRed(new JSlider(JSlider.VERTICAL, 0, 255, getValueRed()));
		getSldRed().setBounds(5, 35, 70, 360);
		getSldRed().setMajorTickSpacing(10);
		getSldRed().setMinorTickSpacing(5);
		getSldRed().setPaintTicks(true);
		getSldRed().setPaintLabels(true);
		getSldRed().setSnapToTicks(false);
		getSldRed().setName("Red");
		getSldRed().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldRed().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldRed());	
		
		// Fader Green
		setSldGreen(new JSlider(JSlider.VERTICAL, 0, 255, getValueGreen()));
		getSldGreen().setBounds(90, 35, 70, 360);
		getSldGreen().setMajorTickSpacing(10);
		getSldGreen().setMinorTickSpacing(5);
		getSldGreen().setPaintTicks(true);
		getSldGreen().setPaintLabels(true);
		getSldGreen().setSnapToTicks(false);
		getSldGreen().setName("Green");
		getSldGreen().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldGreen().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldGreen());
		
		// Fader Blue
		setSldBlue(new JSlider(JSlider.VERTICAL, 0, 255, getValueBlue()));
		getSldBlue().setBounds(175, 35, 70, 360);
		getSldBlue().setMajorTickSpacing(10);
		getSldBlue().setMinorTickSpacing(5);
		getSldBlue().setPaintTicks(true);
		getSldBlue().setPaintLabels(true);
		getSldBlue().setSnapToTicks(false);
		getSldBlue().setName("Blue");
		getSldBlue().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldBlue().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldBlue());
		
		// Master Fader
		setSldMaster(new JSlider(JSlider.VERTICAL, 0, 255, 128));
		getSldMaster().setBounds(280, 35, 70, 360);
		getSldMaster().setMajorTickSpacing(10);
		getSldMaster().setMinorTickSpacing(5);
		getSldMaster().setPaintTicks(true);
		getSldMaster().setPaintLabels(false);
		getSldMaster().setSnapToTicks(false);
		getSldMaster().setName("Master");
		getSldMaster().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldMaster().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldMaster());
		

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
		txtRed = new JLabel(getValueRed() + " ");
		txtRed.setBounds(30, 400+20, 100, 24);
		pnlMixer.add(txtRed);
		
		// Text Green DEC Value
		txtGreen = new JLabel(getValueGreen() + " ");
		txtGreen.setBounds(115, 400+20, 100, 24);
		pnlMixer.add(txtGreen);
		
		// Text Blue DEC Value
		txtBlue = new JLabel(getValueBlue() + " ");
		txtBlue.setBounds(200, 400+20, 100, 24);
		pnlMixer.add(txtBlue);
	

		// Text Red HEX Value
		hexRed = new JLabel(Integer.toHexString(getValueRed()) + " ");
		hexRed.setBounds(30, 400+40, 100, 24);
		pnlMixer.add(hexRed);
		
		// Text Green HEX Value
		hexGreen = new JLabel(Integer.toHexString(getValueGreen()) + " ");
		hexGreen.setBounds(115, 400+40, 100, 24);
		pnlMixer.add(hexGreen);

		// Text Blue HEX Value
		hexBlue = new JLabel(Integer.toHexString(getValueBlue()) + " ");
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
			oldMasterValue = getMasterValue();
			setMasterValue(source.getValue());

			// If Masterfader moves up
			if (oldMasterValue < getMasterValue()) { 
				
				// If Linear Mode
				if (MasterLog == false) { 
					if (getValueRed() >= 0 && getValueRed() <= 255) {
						getSldRed().setValue(getSldRed().getValue() + 1);
					}
					if (getValueGreen() >= 0 && getValueGreen() <= 255) {
						getSldGreen().setValue(getSldGreen().getValue() + 1);
					}
					if (getValueBlue() >= 0 && getValueBlue() <= 255) {
						getSldBlue().setValue(getSldBlue().getValue() + 1);
					}
				}

				// If Log Mode
				if (MasterLog == true) { 
					if (getValueRed() >= 0 && getValueRed() <= 255) {
						getSldRed().setValue((int) (getSldRed().getValue() * 1.01 + 1));
					}
					if (getValueGreen() >= 0 && getValueGreen() <= 255) {
						getSldGreen().setValue((int) (getSldGreen().getValue() * 1.01 + 1));
					}
					if (getValueBlue() >= 0 && getValueBlue() <= 255) {
						getSldBlue().setValue((int) (getSldBlue().getValue() * 1.01 + 1));
					}
				}

			}
			
			
			// If Masterfader moves down
			if (oldMasterValue > getMasterValue()) { 

				// If Linear Mode
				if (MasterLog == false) { 
					if (getValueRed() >= 0 && getValueRed() <= 255) {
						getSldRed().setValue(getSldRed().getValue() - 1);
					}
					if (getValueGreen() >= 0 && getValueGreen() <= 255) {
						getSldGreen().setValue(getSldGreen().getValue() - 1);
					}
					if (getValueBlue() >= 0 && getValueBlue() <= 255) {
						getSldBlue().setValue(getSldBlue().getValue() - 1);
					}
				}
				
				// If Log Mode
				if (MasterLog == true) { 
					if (getValueRed() >= 0 && getValueRed() <= 255) {
						getSldRed().setValue((int) (getSldRed().getValue() / 1.01));
					}
					if (getValueGreen() >= 0 && getValueGreen() <= 255) {
						getSldGreen().setValue((int) (getSldGreen().getValue() / 1.01));
					}
					if (getValueBlue() >= 0 && getValueBlue() <= 255) {
						getSldBlue().setValue((int) (getSldBlue().getValue() / 1.01));
					}
				}
			}
		
		} 
		
		
		// If change RGB sliders
		else {
			
			// If changed red slider
			if (source.getName().equals("Red")) {
				setValueRed(source.getValue());
				txtRed.setText(source.getValue() + " ");
				hexRed.setText((getValueRed() < 16 ? "0" : "") + Integer.toHexString(source.getValue()) + " ");
				lblRedPercent.setText(Integer.toString((getValueRed() * 100) / 255) + " %");
			}
			
			// If changed green slider
			if (source.getName().equals("Green")) {
				setValueGreen(source.getValue());
				txtGreen.setText(source.getValue() + " ");
				hexGreen.setText((getValueGreen() < 16 ? "0" : "") + Integer.toHexString(source.getValue()) + " ");
				lblGreenPercent.setText(Integer.toString((getValueGreen() * 100) / 255) 	+ " %");
			}
			
			// If changed blue slider
			if (source.getName().equals("Blue")) {
				setValueBlue(source.getValue());
				txtBlue.setText(source.getValue() + " ");
				hexBlue.setText((getValueBlue() < 16 ? "0" : "") + Integer.toHexString(source.getValue()) + " ");
				lblBluePercent.setText(Integer.toString((getValueBlue() * 100) / 255) + " %");
			}
			
			// Set text and slider value
			setTxtOut((getValueRed() < 16 ? "0" : "") + Integer.toHexString(getValueRed())
					+ (getValueGreen() < 16 ? "0" : "") + Integer.toHexString(getValueGreen())
					+ (getValueBlue() < 16 ? "0" : "") + Integer.toHexString(getValueBlue()));
			
			PanelOutput.getTxtOutput().setText(String.valueOf(getTxtOut()));
			getCp().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldRed().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldGreen().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldBlue().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldMaster().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));		
		}
	}

	
	// Handle Linear - Log-Linear switch
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

	public static void setValueRed(int valueRed) {
		WindowMixer.valueRed = valueRed;
	}

	public static int getValueGreen() {
		return valueGreen;
	}

	public static void setValueGreen(int valueGreen) {
		WindowMixer.valueGreen = valueGreen;
	}

	public static int getValueBlue() {
		return valueBlue;
	}

	public static void setValueBlue(int valueBlue) {
		WindowMixer.valueBlue = valueBlue;
	}

	public static JSlider getSldRed() {
		return sldRed;
	}

	public static void setSldRed(JSlider sldRed) {
		WindowMixer.sldRed = sldRed;
	}

	public static JSlider getSldGreen() {
		return sldGreen;
	}

	public static void setSldGreen(JSlider sldGreen) {
		WindowMixer.sldGreen = sldGreen;
	}

	public static JSlider getSldBlue() {
		return sldBlue;
	}

	public static void setSldBlue(JSlider sldBlue) {
		WindowMixer.sldBlue = sldBlue;
	}

	public static JSlider getSldMaster() {
		return sldMaster;
	}

	public static void setSldMaster(JSlider sldMaster) {
		WindowMixer.sldMaster = sldMaster;
	}

	public static int getMasterValue() {
		return masterValue;
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

	public static void setCp(Container cp) {
		WindowMixer.cp = cp;
	}

}