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

	private static int valueRed = 200, valueGreen = 200, valueBlue = 200;
	private static JButton btnSwitch;
	private static JSlider sldRed, sldGreen, sldBlue, sldMaster;
	private static JLabel lblRed, lblGreen, lblBlue;
	private static JLabel txtRed, txtGreen, txtBlue;
	private static JLabel hexRed, hexGreen, hexBlue;
	private static JLabel lblRedPercent, lblGreenPercent, lblBluePercent;
	private static JLabel txtMaster, txtDec, txtHex;
	private static int oldMasterValue = 200, masterValue = 200;
	private static String txtOut;
	private static boolean MasterLog = false;
	private static Border loweredetched;
	private static Container cp;
	private static int yLblName = 15, ySlider = 35, buttonWidth = 90, textHight = 24, sldHight = 360, sldWidth = 70, lblValueYoffset = 400;


	public WindowMixer(String titel) {
		super(titel);
		init();
		this.setResizable(false);
		this.setVisible(true);
	}

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
		// pnlMixer.setBackground(new Color(0,0,0));
		pnlMixer.setOpaque(false);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		pnlMixer.setBorder(BorderFactory.createTitledBorder(loweredetched,
				"Mixer"));
		getCp().add(pnlMixer);

		
		// Faderline Red
		// Label Red Name
		lblRed = new JLabel("Red");
		lblRed.setBounds(30, yLblName, 100, textHight);
		pnlMixer.add(lblRed);

		// Fader Red
		setSldRed(new JSlider(JSlider.VERTICAL, 0, 255, getValueRed()));
		getSldRed().setBounds(5, ySlider, sldWidth, sldHight);
		getSldRed().setMajorTickSpacing(10);
		getSldRed().setMinorTickSpacing(5);
		getSldRed().setPaintTicks(true);
		getSldRed().setPaintLabels(true);
		getSldRed().setSnapToTicks(false);
		getSldRed().setName("Red");
		getSldRed().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldRed().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldRed());

		// Label Red Percent
		lblRedPercent = new JLabel("78 %");
		lblRedPercent.setBounds(30, lblValueYoffset, 100, textHight);
		pnlMixer.add(lblRedPercent);
		
		// Text Red DEC Value
		txtRed = new JLabel(getValueRed() + " ");
		txtRed.setBounds(30, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtRed);

		// Text Red HEX Value
		hexRed = new JLabel(Integer.toHexString(getValueRed()) + " ");
		hexRed.setBounds(30, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(hexRed);
		
		
		// Faderline Green
		// Label Green Name
		lblGreen = new JLabel("Green");
		lblGreen.setBounds(110, yLblName, 100, textHight);
		pnlMixer.add(lblGreen);

		// Fader Green
		setSldGreen(new JSlider(JSlider.VERTICAL, 0, 255, getValueGreen()));
		getSldGreen().setBounds(90, ySlider, sldWidth, sldHight);
		getSldGreen().setMajorTickSpacing(10);
		getSldGreen().setMinorTickSpacing(5);
		getSldGreen().setPaintTicks(true);
		getSldGreen().setPaintLabels(true);
		getSldGreen().setSnapToTicks(false);
		getSldGreen().setName("Green");
		getSldGreen().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldGreen().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldGreen());

		// Label Green Percent
		lblGreenPercent = new JLabel("78 %");
		lblGreenPercent.setBounds(115, lblValueYoffset, 100, textHight);
		pnlMixer.add(lblGreenPercent);
		
		// Text Green DEC Value
		txtGreen = new JLabel(getValueGreen() + " ");
		txtGreen.setBounds(115, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtGreen);

		// Text Green HEX Value
		hexGreen = new JLabel(Integer.toHexString(getValueGreen()) + " ");
		hexGreen.setBounds(115, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(hexGreen);

		
		// Faderline Blue
		// Label Blue Name
		lblBlue = new JLabel("Blue");
		lblBlue.setBounds(200, yLblName, 100, textHight);
		pnlMixer.add(lblBlue);

		// Fader Blue
		setSldBlue(new JSlider(JSlider.VERTICAL, 0, 255, getValueBlue()));
		getSldBlue().setBounds(175, ySlider, sldWidth, sldHight);
		getSldBlue().setMajorTickSpacing(10);
		getSldBlue().setMinorTickSpacing(5);
		getSldBlue().setPaintTicks(true);
		getSldBlue().setPaintLabels(true);
		getSldBlue().setSnapToTicks(false);
		getSldBlue().setName("Blue");
		getSldBlue().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldBlue().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldBlue());

		// Label Blue Percent
		lblBluePercent = new JLabel("78 %");
		lblBluePercent.setBounds(200, lblValueYoffset, 100, textHight);
		pnlMixer.add(lblBluePercent);
		
		// Text Blue DEC Value
		txtBlue = new JLabel(getValueBlue() + " ");
		txtBlue.setBounds(200, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtBlue);

		// Text Blue HEX Value
		hexBlue = new JLabel(Integer.toHexString(getValueBlue()) + " ");
		hexBlue.setBounds(200, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(hexBlue);


		// Faderline Master
		// Master Ausgabe
		txtMaster = new JLabel("Master");
		txtMaster.setBounds(295, yLblName, 100, textHight);
		pnlMixer.add(txtMaster);

		// Master Fader
		setSldMaster(new JSlider(JSlider.VERTICAL, 0, 255, 128));
		getSldMaster().setBounds(280, ySlider, sldWidth, sldHight);
		getSldMaster().setMajorTickSpacing(10);
		getSldMaster().setMinorTickSpacing(5);
		getSldMaster().setPaintTicks(true);
		getSldMaster().setPaintLabels(false);
		getSldMaster().setSnapToTicks(false);
		getSldMaster().setName("Master");
		getSldMaster().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		getSldMaster().addChangeListener((ChangeListener) this);
		pnlMixer.add(getSldMaster());
		
		// Button Switch Master Log-Linear
		String btnText = "Linear";
		btnSwitch = new JButton(btnText);
		btnSwitch.setBounds(270, lblValueYoffset, buttonWidth, textHight);
		btnSwitch.setToolTipText("Switch Masterfader");
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				handlebtnSwitch(event);
			}
		});
		pnlMixer.add(btnSwitch);
		
		// Label DEC Value
		txtDec = new JLabel("Dec");
		txtDec.setBounds(300, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtDec);

		// Label HEX Value
		txtHex = new JLabel("Hex");
		txtHex.setBounds(300, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(txtHex);	
	}
	
	
	// If change RGB Sliders
	public void stateChanged(ChangeEvent evt) {
		JSlider source;
		source = (JSlider) evt.getSource();

		if (source.getName().equals("Red")) {
			setValueRed(source.getValue());
			txtRed.setText(source.getValue() + " ");
			hexRed.setText((getValueRed() < 16 ? "0" : "")
					+ Integer.toHexString(source.getValue()) + " ");
			lblRedPercent.setText(Integer.toString((getValueRed() * 100) / 255)
					+ " %");
			setTxtOut((getValueRed() < 16 ? "0" : "") + Integer.toHexString(getValueRed())
					+ (getValueGreen() < 16 ? "0" : "")
					+ Integer.toHexString(getValueGreen())
					+ (getValueBlue() < 16 ? "0" : "")
					+ Integer.toHexString(getValueBlue()));
			PanelOutput.getTxtOutput().setText(String.valueOf(getTxtOut()));
			getCp().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldRed().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldGreen().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldBlue().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldMaster().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		}
		
		if (source.getName().equals("Green")) {
			setValueGreen(source.getValue());
			txtGreen.setText(source.getValue() + " ");
			hexGreen.setText((getValueGreen() < 16 ? "0" : "")
					+ Integer.toHexString(source.getValue()) + " ");
			lblGreenPercent.setText(Integer.toString((getValueGreen() * 100) / 255)
					+ " %");
			setTxtOut((getValueRed() < 16 ? "0" : "") + Integer.toHexString(getValueRed())
					+ (getValueGreen() < 16 ? "0" : "")
					+ Integer.toHexString(getValueGreen())
					+ (getValueBlue() < 16 ? "0" : "")
					+ Integer.toHexString(getValueBlue()));
			PanelOutput.getTxtOutput().setText(String.valueOf(getTxtOut()));
			getCp().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldRed().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldGreen().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldBlue().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldMaster().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		}
		
		if (source.getName().equals("Blue")) {
			setValueBlue(source.getValue());
			txtBlue.setText(source.getValue() + " ");
			hexBlue.setText((getValueBlue() < 16 ? "0" : "")
					+ Integer.toHexString(source.getValue()) + " ");
			lblBluePercent.setText(Integer.toString((getValueBlue() * 100) / 255)
					+ " %");
			setTxtOut((getValueRed() < 16 ? "0" : "") + Integer.toHexString(getValueRed())
					+ (getValueGreen() < 16 ? "0" : "")
					+ Integer.toHexString(getValueGreen())
					+ (getValueBlue() < 16 ? "0" : "")
					+ Integer.toHexString(getValueBlue()));
			PanelOutput.getTxtOutput().setText(String.valueOf(getTxtOut()));
			getCp().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldRed().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldGreen().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldBlue().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
			getSldMaster().setBackground(new Color(getValueRed(), getValueGreen(), getValueBlue()));
		}
		
		
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
