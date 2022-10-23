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
	
	private static JButton btnSetToCue, btnCue01, btnCue02, btnCue03, btnCue04;
	private static JLabel lblCue01Thumb, lblCue02Thumb, lblCue03Thumb, lblCue04Thumb;
	private static JLabel lblCue01, lblCue02, lblCue03, lblCue04; 
	private static int inputHexRed, inputHexGreen, inputHexBlue;
	private static boolean cueSelect = false;
	private static Border loweredetched;
	private static int xoffset = 12, yoffset = 18, buttonWidth = 130, textHeight = 24, buttonWidthCue = 90;
	
	static void panCue() {

		JPanel pnlCue = new JPanel();
		pnlCue.setLayout(null);
		pnlCue.setBounds(15, 98, 230, 158);
		pnlCue.setOpaque(false);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		pnlCue.setBorder(BorderFactory.createTitledBorder(loweredetched, "Cue"));
		WindowMixer.getCp().add(pnlCue);
		
		

		
		
		// Set To Cue Button
		btnSetToCue = new JButton("Set To");				
		btnSetToCue.setBounds(xoffset, yoffset, buttonWidth, textHeight);
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
		btnCue01.setBounds(xoffset, 48, buttonWidthCue, textHeight);
		//btnCue01.setMnemonic('1');
		btnCue01.setToolTipText("Cue 1");
		btnCue01.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue01(event);
			}
		});
		pnlCue.add(btnCue01);
		
	// Cue 1 Farb Label	
		setLblCue01Thumb(new JLabel());								
		getLblCue01Thumb().setBounds(105, 50, 50, 20);
		pnlCue.add(getLblCue01Thumb());
		
	// Cue 1 Label	
		setLblCue01(new JLabel("-free-"));
		getLblCue01().setBounds(160, 50, 80, 20);
		pnlCue.add(getLblCue01());
		
		
	// Cue 2 Button
		btnCue02 = new JButton("Cue 2");
		btnCue02.setBounds(xoffset, 73, buttonWidthCue, textHeight);
		//btnCue02.setMnemonic('2');
		btnCue02.setToolTipText("Cue 2");
		btnCue02.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue02(event);
			}
		});
		pnlCue.add(btnCue02);
		
	// Cue 2 Farb Label	
		setLblCue02Thumb(new JLabel());								
		getLblCue02Thumb().setBounds(105, 75, 50, 20);
		pnlCue.add(getLblCue02Thumb());
		
	// Cue 2 Label	
		setLblCue02(new JLabel("-free-"));
		getLblCue02().setBounds(160, 75, 80, 20);
		pnlCue.add(getLblCue02());
		
		
	// Cue 3 Button
		btnCue03 = new JButton("Cue 3");				
		btnCue03.setBounds(xoffset, 98, buttonWidthCue, textHeight);
		//btnCue03.setMnemonic('3');
		btnCue03.setToolTipText("Cue 3");
		btnCue03.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue03(event);
			}
		});
		pnlCue.add(btnCue03);
		
	// Cue 3 Farb Label	
		setLblCue03Thumb(new JLabel());								
		getLblCue03Thumb().setBounds(105, 100, 50, 20);
		pnlCue.add(getLblCue03Thumb());
		
	// Cue 3 Label	
		setLblCue03(new JLabel("-free-"));								
		getLblCue03().setBounds(160, 100, 80, 20);
		pnlCue.add(getLblCue03());
		
	// Cue 4 Button	
		btnCue04 = new JButton("Cue 4");
		btnCue04.setBounds(xoffset, 123, buttonWidthCue, textHeight);
		//btnCue04.setMnemonic('4');
		btnCue04.setToolTipText("Cue 4");
		btnCue04.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnCue04(event);
			}
		});
		pnlCue.add(btnCue04);
		
	// Cue 4 Farb Label	
		setLblCue04Thumb(new JLabel());								
		getLblCue04Thumb().setBounds(105, 125, 50, 20);
		pnlCue.add(getLblCue04Thumb());
		
	// Cue 4 Label	
		setLblCue04(new JLabel("-free-"));								
		getLblCue04().setBounds(160, 125, 80, 20);
		pnlCue.add(getLblCue04());
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
		wert = PanelOutput.getTxtOutput().getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			getLblCue01().setText(String.valueOf(WindowMixer.getTxtOut()));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			getLblCue01Thumb().setOpaque(true);
			getLblCue01Thumb().setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
			
		} else {
			if (!getLblCue01().getText().equals("-free-")) {
				HexToDec.outputDec(getLblCue01().getText());
			}
		}
	}

	//	Cue02
	protected static void handlebtnCue02(ActionEvent event) {
		String wert;
		wert = PanelOutput.getTxtOutput().getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			getLblCue02().setText(String.valueOf(WindowMixer.getTxtOut()));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			getLblCue02Thumb().setOpaque(true);
			getLblCue02Thumb().setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
		} else {
			if (!getLblCue02().getText().equals("-free-")) {
				HexToDec.outputDec(getLblCue02().getText());
			}
		}
	}
	
	//	Cue03
	protected static void handlebtnCue03(ActionEvent event) {
		String wert;
		wert = PanelOutput.getTxtOutput().getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			getLblCue03().setText(String.valueOf(WindowMixer.getTxtOut()));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			getLblCue03Thumb().setOpaque(true);
			getLblCue03Thumb().setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
		} else {
			if (!getLblCue03().getText().equals("-free-")) {

				HexToDec.outputDec(getLblCue03().getText());
			}
		}
	}
	
	//	Cue04
	protected static void handlebtnCue04(ActionEvent event) {
		String wert;
		wert = PanelOutput.getTxtOutput().getText();
		if (cueSelect == true) {
			btnSetToCue.setText("Set To");
			cueSelect = false;
			btnSetToCue.setForeground(new Color(0, 0, 0));
			getLblCue04().setText(String.valueOf(WindowMixer.getTxtOut()));
			
			inputHexRed = Integer.parseInt(wert.substring(0,2), 16);
			inputHexGreen = Integer.parseInt(wert.substring(2,4), 16);
			inputHexBlue = Integer.parseInt(wert.substring(4,6),16 );
			getLblCue04Thumb().setOpaque(true);
			getLblCue04Thumb().setBackground(new Color(inputHexRed,inputHexGreen,inputHexBlue));
		} else {
			if (!getLblCue04().getText().equals("-free-")) {

				HexToDec.outputDec(getLblCue04().getText());
			}
		}
	}


	// Setter Getter
	public static JLabel getLblCue01Thumb() {
		return lblCue01Thumb;
	}


	public static void setLblCue01Thumb(JLabel lblCue01Thumb) {
		PanelCue.lblCue01Thumb = lblCue01Thumb;
	}


	public static JLabel getLblCue02Thumb() {
		return lblCue02Thumb;
	}


	public static void setLblCue02Thumb(JLabel lblCue02Thumb) {
		PanelCue.lblCue02Thumb = lblCue02Thumb;
	}


	public static JLabel getLblCue03Thumb() {
		return lblCue03Thumb;
	}


	public static void setLblCue03Thumb(JLabel lblCue03Thumb) {
		PanelCue.lblCue03Thumb = lblCue03Thumb;
	}


	public static JLabel getLblCue04Thumb() {
		return lblCue04Thumb;
	}


	public static void setLblCue04Thumb(JLabel lblCue04Thumb) {
		PanelCue.lblCue04Thumb = lblCue04Thumb;
	}


	public static JLabel getLblCue01() {
		return lblCue01;
	}


	public static void setLblCue01(JLabel lblCue01) {
		PanelCue.lblCue01 = lblCue01;
	}


	public static JLabel getLblCue02() {
		return lblCue02;
	}


	public static void setLblCue02(JLabel lblCue02) {
		PanelCue.lblCue02 = lblCue02;
	}


	public static JLabel getLblCue03() {
		return lblCue03;
	}


	public static void setLblCue03(JLabel lblCue03) {
		PanelCue.lblCue03 = lblCue03;
	}


	public static JLabel getLblCue04() {
		return lblCue04;
	}


	public static void setLblCue04(JLabel lblCue04) {
		PanelCue.lblCue04 = lblCue04;
	}
}
