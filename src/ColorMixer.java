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

public class ColorMixer extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 3065787531046475413L;

	static int valueRed = 200, valueGreen = 200, valueBlue = 200;
	static JButton btnSwitch;
	static JSlider sldRed, sldGreen, sldBlue, sldMaster;
	static JLabel lblRed, lblGreen, lblBlue;
	static JLabel txtRed, txtGreen, txtBlue;
	static JLabel hexRed, hexGreen, hexBlue;
	static JLabel lblRedPercent, lblGreenPercent, lblBluePercent;
	static JLabel txtMaster, txtDec, txtHex;
	static int oldMasterValue = 200, masterValue = 200;
	static String txtOut;
	static boolean MasterLog = false;
	static Border loweredetched;
	static Container cp;
	static int xoffset = 16, yLblName = 15, ySlider = 35, buttonWidth = 90, textHight = 24, sldHight = 360, sldWidth = 70, lblValueYoffset = 400;

	public ColorMixer(String titel) {
		super(titel);
		init();
		this.setResizable(false);
		this.setVisible(true);
	}

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
		PanelFile.panFile();
		PanelOutput.panOut();

			
		// Panel Mixer
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JPanel pnlMixer = new JPanel();
		pnlMixer.setLayout(null);
		pnlMixer.setBounds(260, 15, 380, 475);
		// pnlMixer.setBackground(new Color(0,0,0));
		pnlMixer.setOpaque(false);
		pnlMixer.setBorder(BorderFactory.createTitledBorder(loweredetched,
				"Mixer"));
		cp.add(pnlMixer);

		
		// Faderline Red
		// Label Red Name
		lblRed = new JLabel("Red");
		lblRed.setBounds(30, yLblName, 100, textHight);
		pnlMixer.add(lblRed);

		// Fader Red
		sldRed = new JSlider(JSlider.VERTICAL, 0, 255, valueRed);
		sldRed.setBounds(5, ySlider, sldWidth, sldHight);
		sldRed.setMajorTickSpacing(10);
		sldRed.setMinorTickSpacing(5);
		sldRed.setPaintTicks(true);
		sldRed.setPaintLabels(true);
		sldRed.setSnapToTicks(false);
		sldRed.setName("Red");
		sldRed.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldRed.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldRed);

		// Label Red Percent
		lblRedPercent = new JLabel("78 %");
		lblRedPercent.setBounds(30, lblValueYoffset, 100, textHight);
		pnlMixer.add(lblRedPercent);
		
		// Text Red DEC Value
		txtRed = new JLabel(valueRed + " ");
		txtRed.setBounds(30, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtRed);

		// Text Red HEX Value
		hexRed = new JLabel(Integer.toHexString(valueRed) + " ");
		hexRed.setBounds(30, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(hexRed);
		
		
		// Faderline Green
		// Label Green Name
		lblGreen = new JLabel("Green");
		lblGreen.setBounds(110, yLblName, 100, textHight);
		pnlMixer.add(lblGreen);

		// Fader Green
		sldGreen = new JSlider(JSlider.VERTICAL, 0, 255, valueGreen);
		sldGreen.setBounds(90, ySlider, sldWidth, sldHight);
		sldGreen.setMajorTickSpacing(10);
		sldGreen.setMinorTickSpacing(5);
		sldGreen.setPaintTicks(true);
		sldGreen.setPaintLabels(true);
		sldGreen.setSnapToTicks(false);
		sldGreen.setName("Green");
		sldGreen.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldGreen.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldGreen);

		// Label Green Percent
		lblGreenPercent = new JLabel("78 %");
		lblGreenPercent.setBounds(115, lblValueYoffset, 100, textHight);
		pnlMixer.add(lblGreenPercent);
		
		// Text Green DEC Value
		txtGreen = new JLabel(valueGreen + " ");
		txtGreen.setBounds(115, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtGreen);

		// Text Green HEX Value
		hexGreen = new JLabel(Integer.toHexString(valueGreen) + " ");
		hexGreen.setBounds(115, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(hexGreen);

		
		// Faderline Blue
		// Label Blue Name
		lblBlue = new JLabel("Blue");
		lblBlue.setBounds(200, yLblName, 100, textHight);
		pnlMixer.add(lblBlue);

		// Fader Blue
		sldBlue = new JSlider(JSlider.VERTICAL, 0, 255, valueBlue);
		sldBlue.setBounds(175, ySlider, sldWidth, sldHight);
		sldBlue.setMajorTickSpacing(10);
		sldBlue.setMinorTickSpacing(5);
		sldBlue.setPaintTicks(true);
		sldBlue.setPaintLabels(true);
		sldBlue.setSnapToTicks(false);
		sldBlue.setName("Blue");
		sldBlue.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldBlue.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldBlue);

		// Label Blue Percent
		lblBluePercent = new JLabel("78 %");
		lblBluePercent.setBounds(200, lblValueYoffset, 100, textHight);
		pnlMixer.add(lblBluePercent);
		
		// Text Blue DEC Value
		txtBlue = new JLabel(valueBlue + " ");
		txtBlue.setBounds(200, lblValueYoffset+20, 100, textHight);
		pnlMixer.add(txtBlue);

		// Text Blue HEX Value
		hexBlue = new JLabel(Integer.toHexString(valueBlue) + " ");
		hexBlue.setBounds(200, lblValueYoffset+40, 100, textHight);
		pnlMixer.add(hexBlue);


		// Faderline Master
		// Master Ausgabe
		txtMaster = new JLabel("Master");
		txtMaster.setBounds(295, yLblName, 100, textHight);
		pnlMixer.add(txtMaster);

		// Master Fader
		sldMaster = new JSlider(JSlider.VERTICAL, 0, 255, 128);
		sldMaster.setBounds(280, ySlider, sldWidth, sldHight);
		sldMaster.setMajorTickSpacing(10);
		sldMaster.setMinorTickSpacing(5);
		sldMaster.setPaintTicks(true);
		sldMaster.setPaintLabels(false);
		sldMaster.setSnapToTicks(false);
		sldMaster.setName("Master");
		sldMaster.setBackground(new Color(valueRed, valueGreen, valueBlue));
		sldMaster.addChangeListener((ChangeListener) this);
		pnlMixer.add(sldMaster);
		
		// Button Switch Master Log-Linear
		String btnText = "Linear";
		btnSwitch = new JButton(btnText);
		btnSwitch.setBounds(270, lblValueYoffset, 90, textHight);
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
			valueRed = source.getValue();
			txtRed.setText(source.getValue() + " ");
			hexRed.setText((valueRed < 16 ? "0" : "")
					+ Integer.toHexString(source.getValue()) + " ");
			lblRedPercent.setText(Integer.toString((valueRed * 100) / 255)
					+ " %");
			txtOut = (valueRed < 16 ? "0" : "") + Integer.toHexString(valueRed)
					+ (valueGreen < 16 ? "0" : "")
					+ Integer.toHexString(valueGreen)
					+ (valueBlue < 16 ? "0" : "")
					+ Integer.toHexString(valueBlue);
			PanelOutput.txtOutput.setText(String.valueOf(txtOut));
			cp.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldRed.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldGreen.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldBlue.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldMaster.setBackground(new Color(valueRed, valueGreen, valueBlue));
		}
		
		if (source.getName().equals("Green")) {
			valueGreen = source.getValue();
			txtGreen.setText(source.getValue() + " ");
			hexGreen.setText((valueGreen < 16 ? "0" : "")
					+ Integer.toHexString(source.getValue()) + " ");
			lblGreenPercent.setText(Integer.toString((valueGreen * 100) / 255)
					+ " %");
			txtOut = (valueRed < 16 ? "0" : "") + Integer.toHexString(valueRed)
					+ (valueGreen < 16 ? "0" : "")
					+ Integer.toHexString(valueGreen)
					+ (valueBlue < 16 ? "0" : "")
					+ Integer.toHexString(valueBlue);
			PanelOutput.txtOutput.setText(String.valueOf(txtOut));
			cp.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldRed.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldGreen.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldBlue.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldMaster.setBackground(new Color(valueRed, valueGreen, valueBlue));
		}
		
		if (source.getName().equals("Blue")) {
			valueBlue = source.getValue();
			txtBlue.setText(source.getValue() + " ");
			hexBlue.setText((valueBlue < 16 ? "0" : "")
					+ Integer.toHexString(source.getValue()) + " ");
			lblBluePercent.setText(Integer.toString((valueBlue * 100) / 255)
					+ " %");
			txtOut = (valueRed < 16 ? "0" : "") + Integer.toHexString(valueRed)
					+ (valueGreen < 16 ? "0" : "")
					+ Integer.toHexString(valueGreen)
					+ (valueBlue < 16 ? "0" : "")
					+ Integer.toHexString(valueBlue);
			PanelOutput.txtOutput.setText(String.valueOf(txtOut));
			cp.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldRed.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldGreen.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldBlue.setBackground(new Color(valueRed, valueGreen, valueBlue));
			sldMaster.setBackground(new Color(valueRed, valueGreen, valueBlue));
		}
		
		
		// If change Master Slider
		if (source.getName().equals("Master")) {
			oldMasterValue = masterValue;
			masterValue = source.getValue();

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

}
