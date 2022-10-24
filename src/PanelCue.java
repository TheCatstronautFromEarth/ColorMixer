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

		
		// SetToCue Button
		btnSetToCue = new JButton("Set To");
		btnSetToCue.setBounds(12, 18, 130, 24);
		btnSetToCue.addActionListener(actions);
		pnlCue.add(btnSetToCue);
		
		// Cue Buttons
		btnCue01 = new JButton("Cue 1");
		btnCue01.setBounds(12, 48, 90, 24);
		btnCue01.addActionListener(actions);
		pnlCue.add(btnCue01);
		
		btnCue02 = new JButton("Cue 2");
		btnCue02.setBounds(12, 73, 90, 24);
		btnCue02.addActionListener(actions);
		pnlCue.add(btnCue02);
		
		btnCue03 = new JButton("Cue 3");
		btnCue03.setBounds(12, 98, 90, 24);
		btnCue03.addActionListener(actions);
		pnlCue.add(btnCue03);
		
		btnCue04 = new JButton("Cue 4");
		btnCue04.setBounds(12, 123, 90, 24);
		btnCue04.addActionListener(actions);
		pnlCue.add(btnCue04);

		
		// Cue ColorThumbnails
		lblCue01Thumb = new JLabel();
		lblCue01Thumb.setBounds(105, 50, 50, 20);
		pnlCue.add(lblCue01Thumb);
		
		lblCue02Thumb = new JLabel();
		lblCue02Thumb.setBounds(105, 75, 50, 20);
		pnlCue.add(lblCue02Thumb);
		
		lblCue03Thumb = new JLabel();
		lblCue03Thumb.setBounds(105, 100, 50, 20);
		pnlCue.add(lblCue03Thumb);
		
		lblCue04Thumb = new JLabel();
		lblCue04Thumb.setBounds(105, 125, 50, 20);
		pnlCue.add(lblCue04Thumb);

		
		// Cue Hex Test Label
		lblCue01 = new JLabel("-free-");
		lblCue01.setBounds(160, 50, 80, 20);
		pnlCue.add(lblCue01);
		
		lblCue02 = new JLabel("-free-");
		lblCue02.setBounds(160, 75, 80, 20);
		pnlCue.add(lblCue02);
		
		lblCue03 = new JLabel("-free-");
		lblCue03.setBounds(160, 100, 80, 20);
		pnlCue.add(lblCue03);
		
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
				btnSetToCue.setForeground(new Color(0, 0, 0));
				btnSetToCue.setText("Set To");
				setCue(btnSource);
				cueSelect = false;
			} else {
				if (btnSource == btnSetToCue) {
					btnSetToCue.setForeground(new Color(255, 0, 0));
					btnSetToCue.setText("Choose..");
					cueSelect = true;
				} else {
					callCue(btnSource);
				}
			}
			
		}
	};

	
	// Set Cue
	public static void setCue(Object btnSource) {
		String hexValue;
		hexValue = PanelOutput.getTxtOutput().getText();
		valueHexRed = Integer.parseInt(hexValue.substring(0, 2), 16);
		valueHexGreen = Integer.parseInt(hexValue.substring(2, 4), 16);
		valueHexBlue = Integer.parseInt(hexValue.substring(4, 6), 16);
		
		if (btnSource == btnCue01) {
			lblCue01.setText(hexValue);
			lblCue01Thumb.setOpaque(true);
			lblCue01Thumb.setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		} else if (btnSource == btnCue02) {
			lblCue02.setText(hexValue);
			lblCue02Thumb.setOpaque(true);
			lblCue02Thumb.setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		} else if (btnSource == btnCue03) {
			lblCue03.setText(hexValue);
			lblCue03Thumb.setOpaque(true);
			lblCue03Thumb.setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		} else if (btnSource == btnCue04) {
			lblCue04.setText(hexValue);
			lblCue04Thumb.setOpaque(true);
			lblCue04Thumb.setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		}
	}

	
	// Call Cue
	public static void callCue(Object btnSource) {
		if (btnSource == btnCue01) {
			if (!lblCue01.getText().equals("-free-")) {
				HexToDec.outputDec(lblCue01.getText());
			}
		} else if (btnSource == btnCue02) {
			if (!lblCue02.getText().equals("-free-")) {
				HexToDec.outputDec(lblCue02.getText());
			}
		} else if (btnSource == btnCue03) {
			if (!lblCue03.getText().equals("-free-")) {
				HexToDec.outputDec(lblCue03.getText());
			}
		} else if (btnSource == btnCue04) {
			if (!lblCue04.getText().equals("-free-")) {
				HexToDec.outputDec(lblCue04.getText());
			}
		}
	}

	
	// Getter
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
