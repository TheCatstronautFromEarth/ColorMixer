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
	private static int valueHexRed, valueHexGreen, valueHexBlue;
	private static boolean cueSelect = false;
	private static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	static void panCue() {
		
		// Init Panel
		JPanel pnlCue = new JPanel();
		pnlCue.setLayout(null);
		pnlCue.setBounds(15, 98, 230, 158);
		pnlCue.setOpaque(false);
		pnlCue.setBorder(BorderFactory.createTitledBorder(loweredetched, "Cue"));
		WindowMixer.getCp().add(pnlCue);
		
		// Button SetToCue 
		btnSetToCue = new JButton("Set To");
		btnSetToCue.setBounds(12, 18, 130, 24);
		btnSetToCue.addActionListener(actions);
		pnlCue.add(btnSetToCue);
		
		// Button Cue 1
		btnCue01 = new JButton("Cue 1");
		btnCue01.setBounds(12, 48, 90, 24);
		btnCue01.setEnabled(false);
		btnCue01.addActionListener(actions);
		pnlCue.add(btnCue01);
		// Button Cue 2
		btnCue02 = new JButton("Cue 2");
		btnCue02.setBounds(12, 73, 90, 24);
		btnCue02.setEnabled(false);
		btnCue02.addActionListener(actions);
		pnlCue.add(btnCue02);
		// Button Cue 3
		btnCue03 = new JButton("Cue 3");
		btnCue03.setBounds(12, 98, 90, 24);
		btnCue03.setEnabled(false);
		btnCue03.addActionListener(actions);
		pnlCue.add(btnCue03);
		// Button Cue 4
		btnCue04 = new JButton("Cue 4");
		btnCue04.setBounds(12, 123, 90, 24);
		btnCue04.setEnabled(false);
		btnCue04.addActionListener(actions);
		pnlCue.add(btnCue04);
		
		// Label Cue 1 Thumbnail
		lblCue01Thumb = new JLabel();
		lblCue01Thumb.setBounds(105, 50, 50, 20);
		pnlCue.add(lblCue01Thumb);
		// Label Cue 2 Thumbnail
		lblCue02Thumb = new JLabel();
		lblCue02Thumb.setBounds(105, 75, 50, 20);
		pnlCue.add(lblCue02Thumb);
		// Label Cue 3 Thumbnail
		lblCue03Thumb = new JLabel();
		lblCue03Thumb.setBounds(105, 100, 50, 20);
		pnlCue.add(lblCue03Thumb);
		// Label Cue 4 Thumbnail
		lblCue04Thumb = new JLabel();
		lblCue04Thumb.setBounds(105, 125, 50, 20);
		pnlCue.add(lblCue04Thumb);

		// Label Cue 1 Hex
		lblCue01 = new JLabel("-free-");
		lblCue01.setBounds(160, 50, 80, 20);
		pnlCue.add(lblCue01);
		// Label Cue 2 Hex
		lblCue02 = new JLabel("-free-");
		lblCue02.setBounds(160, 75, 80, 20);
		pnlCue.add(lblCue02);
		// Label Cue 3 Hex
		lblCue03 = new JLabel("-free-");
		lblCue03.setBounds(160, 100, 80, 20);
		pnlCue.add(lblCue03);
		// Label Cue 4 Hex
		lblCue04 = new JLabel("-free-");
		lblCue04.setBounds(160, 125, 80, 20);
		pnlCue.add(lblCue04);
	}

	// Buttons ActionListener 
	private static ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object btnSource = e.getSource();
			
			if (cueSelect) {
				btnSetToCue.setForeground(Color.BLACK);
				btnSetToCue.setText("Set To");
				
				if (lblCue01.getText().equals("-free-")) {
					btnCue01.setEnabled(false);
				}
				
				if (lblCue02.getText().equals("-free-")) {
					btnCue02.setEnabled(false);
				}
				
				if (lblCue03.getText().equals("-free-")) {
					btnCue03.setEnabled(false);
				}
				
				if (lblCue04.getText().equals("-free-")) {
					btnCue04.setEnabled(false);
				}
				
				setCue(btnSource);
				cueSelect = false;
			} else {
				if (btnSource == btnSetToCue) {
					btnSetToCue.setForeground(Color.RED);
					btnSetToCue.setText("Choose..");

					btnCue01.setEnabled(true);
					btnCue02.setEnabled(true);
					btnCue03.setEnabled(true);
					btnCue04.setEnabled(true);
					
					cueSelect = true;
				} else {
					callCue(btnSource);
				}
			}
			
		}
	};

	// Set Cue
	public static void setCue(Object btnSource) {
		String hexValue = PanelOutput.getTxtOutput().getText();
		valueHexRed = Integer.parseInt(hexValue.substring(0, 2), 16);
		valueHexGreen = Integer.parseInt(hexValue.substring(2, 4), 16);
		valueHexBlue = Integer.parseInt(hexValue.substring(4, 6), 16);
		
		Color color = new Color(valueHexRed, valueHexGreen, valueHexBlue);

		if (btnSource == btnCue01) {
			lblCue01.setText(hexValue);
			lblCue01Thumb.setOpaque(true);
			lblCue01Thumb.setBackground(color);
			btnCue01.setEnabled(true);
		} else if (btnSource == btnCue02) {
			lblCue02.setText(hexValue);
			lblCue02Thumb.setOpaque(true);
			lblCue02Thumb.setBackground(color);
			btnCue02.setEnabled(true);
		} else if (btnSource == btnCue03) {
			lblCue03.setText(hexValue);
			lblCue03Thumb.setOpaque(true);
			lblCue03Thumb.setBackground(color);
			btnCue03.setEnabled(true);
		} else if (btnSource == btnCue04) {
			lblCue04.setText(hexValue);
			lblCue04Thumb.setOpaque(true);
			lblCue04Thumb.setBackground(color);
			btnCue04.setEnabled(true);
		}
	}
	
	// Call Cue
	public static void callCue(Object btnSource) {
		if (btnSource == btnCue01) {
				Functions.HexToDec(lblCue01.getText());			
		} else if (btnSource == btnCue02) {
				Functions.HexToDec(lblCue02.getText());			
		} else if (btnSource == btnCue03) {
				Functions.HexToDec(lblCue03.getText());
		} else if (btnSource == btnCue04) {
				Functions.HexToDec(lblCue04.getText());
		}		
	}
	
	// Getter
	public static JButton getBtnCue01() {
		return btnCue01;
	}
	public static JButton getBtnCue02() {
		return btnCue02;
	}
	public static JButton getBtnCue03() {
		return btnCue03;
	}
	public static JButton getBtnCue04() {
		return btnCue04;
	}
	
	public static JLabel getLblCue01Thumb() {
		return lblCue01Thumb;
	}
	public static JLabel getLblCue02Thumb() {
		return lblCue02Thumb;
	}
	public static JLabel getLblCue03Thumb() {
		return lblCue03Thumb;
	}
	public static JLabel getLblCue04Thumb() {
		return lblCue04Thumb;
	}
	
	public static JLabel getLblCue01() {
		return lblCue01;
	}
	public static JLabel getLblCue02() {
		return lblCue02;
	}
	public static JLabel getLblCue03() {
		return lblCue03;
	}
	public static JLabel getLblCue04() {
		return lblCue04;
	}

}
