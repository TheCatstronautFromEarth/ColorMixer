import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelCue {
	
	static JButton btnSetToCue, btnCue01, btnCue02, btnCue03, btnCue04;
	static JLabel lblCue01Thumb, lblCue02Thumb, lblCue03Thumb, lblCue04Thumb;
	static JLabel lblCue01, lblCue02, lblCue03, lblCue04; 
	static int inputHexRed, inputHexGreen, inputHexBlue;
	static boolean cueSelect = false;
	static Border loweredetched;
	static int xoffset = 12, yoffset = 18, buttonWidth = 130, textHight = 24, buttonWidthCue = 90;
	
	static void panCue() {

		JPanel pnlCue = new JPanel();
		pnlCue.setLayout(null);
		pnlCue.setBounds(15, 98, 230, 158);
		pnlCue.setOpaque(false);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		pnlCue.setBorder(BorderFactory.createTitledBorder(loweredetched, "Cue"));
		ColorMixer.cp.add(pnlCue);
		
		

		
		
		// Set To Cue Button
		btnSetToCue = new JButton("Set To");				
		btnSetToCue.setBounds(xoffset, yoffset, buttonWidth, textHight);
		//btnSetToCue.setMnemonic('t');
		btnSetToCue.setToolTipText("Set current Cue");
		btnSetToCue.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnSetToCue(event);
			}
		});
		pnlCue.add(btnSetToCue);
		
		
		
		
	// Cue 1 Button
		btnCue01 = new JButton("Cue 1");				
		btnCue01.setBounds(xoffset, 48, buttonWidthCue, textHight);
		//btnCue01.setMnemonic('1');
		btnCue01.setToolTipText("Cue 1");
		btnCue01.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue01(event);
			}
		});
		pnlCue.add(btnCue01);
		
	// Cue 1 Farb Label	
		lblCue01Thumb = new JLabel();								
		lblCue01Thumb.setBounds(105, 50, 50, 20);
		pnlCue.add(lblCue01Thumb);
		
	// Cue 1 Label	
		lblCue01 = new JLabel("-free-");
		lblCue01.setBounds(160, 50, 80, 20);
		pnlCue.add(lblCue01);
		
		
	// Cue 2 Button
		btnCue02 = new JButton("Cue 2");
		btnCue02.setBounds(xoffset, 73, buttonWidthCue, textHight);
		//btnCue02.setMnemonic('2');
		btnCue02.setToolTipText("Cue 2");
		btnCue02.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue02(event);
			}
		});
		pnlCue.add(btnCue02);
		
	// Cue 2 Farb Label	
		lblCue02Thumb = new JLabel();								
		lblCue02Thumb.setBounds(105, 75, 50, 20);
		pnlCue.add(lblCue02Thumb);
		
	// Cue 2 Label	
		lblCue02 = new JLabel("-free-");
		lblCue02.setBounds(160, 75, 80, 20);
		pnlCue.add(lblCue02);
		
		
	// Cue 3 Button
		btnCue03 = new JButton("Cue 3");				
		btnCue03.setBounds(xoffset, 98, buttonWidthCue, textHight);
		//btnCue03.setMnemonic('3');
		btnCue03.setToolTipText("Cue 3");
		btnCue03.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue03(event);
			}
		});
		pnlCue.add(btnCue03);
		
	// Cue 3 Farb Label	
		lblCue03Thumb = new JLabel();								
		lblCue03Thumb.setBounds(105, 100, 50, 20);
		pnlCue.add(lblCue03Thumb);
		
	// Cue 3 Label	
		lblCue03 = new JLabel("-free-");								
		lblCue03.setBounds(160, 100, 80, 20);
		pnlCue.add(lblCue03);
		
	// Cue 4 Button	
		btnCue04 = new JButton("Cue 4");
		btnCue04.setBounds(xoffset, 123, buttonWidthCue, textHight);
		//btnCue04.setMnemonic('4');
		btnCue04.setToolTipText("Cue 4");
		btnCue04.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue04(event);
			}
		});
		pnlCue.add(btnCue04);
		
	// Cue 4 Farb Label	
		lblCue04Thumb = new JLabel();								
		lblCue04Thumb.setBounds(105, 125, 50, 20);
		pnlCue.add(lblCue04Thumb);
		
	// Cue 4 Label	
		lblCue04 = new JLabel("-free-");								
		lblCue04.setBounds(160, 125, 80, 20);
		pnlCue.add(lblCue04);
	}
	
	
	// Set To Cue
	protected static void handlebtnSetToCue(ActionEvent event) {

		if (event.getActionCommand().equals("Set To")) {
			btnSetToCue.setText("Choose..");
			cueSelect = true;
			btnSetToCue.setForeground(new Color(255, 0, 0));
		} else {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
		}
	}
	
	//	Cue01	
	protected static void handlebtnCue01(ActionEvent event) {
		String wert;
		wert = PanelOutput.txtOutput.getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			lblCue01.setText(String.valueOf(ColorMixer.txtOut));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			lblCue01Thumb.setOpaque(true);
			lblCue01Thumb.setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
			
		} else {
			if (!lblCue01.getText().equals("-free-")) {
				HexToDec.outputDec(lblCue01.getText());
			}
		}
	}

	//	Cue02
	protected static void handlebtnCue02(ActionEvent event) {
		String wert;
		wert = PanelOutput.txtOutput.getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			lblCue02.setText(String.valueOf(ColorMixer.txtOut));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			lblCue02Thumb.setOpaque(true);
			lblCue02Thumb.setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
		} else {
			if (!lblCue02.getText().equals("-free-")) {
				HexToDec.outputDec(lblCue02.getText());
			}
		}
	}
	
	//	Cue03
	protected static void handlebtnCue03(ActionEvent event) {
		String wert;
		wert = PanelOutput.txtOutput.getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			lblCue03.setText(String.valueOf(ColorMixer.txtOut));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			lblCue03Thumb.setOpaque(true);
			lblCue03Thumb.setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
		} else {
			if (!lblCue03.getText().equals("-free-")) {

				HexToDec.outputDec(lblCue03.getText());
			}
		}
	}
	
	//	Cue04
	protected static void handlebtnCue04(ActionEvent event) {
		String wert;
		wert = PanelOutput.txtOutput.getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			lblCue04.setText(String.valueOf(ColorMixer.txtOut));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			lblCue04Thumb.setOpaque(true);
			lblCue04Thumb.setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
		} else {
			if (!lblCue04.getText().equals("-free-")) {

				HexToDec.outputDec(lblCue04.getText());
			}
		}
	}
}
