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
		setLblCue01Thumb(new JLabel());
		getLblCue01Thumb().setBounds(105, 50, 50, 20);
		pnlCue.add(getLblCue01Thumb());
		
		setLblCue02Thumb(new JLabel());
		getLblCue02Thumb().setBounds(105, 75, 50, 20);
		pnlCue.add(getLblCue02Thumb());
		
		setLblCue03Thumb(new JLabel());
		getLblCue03Thumb().setBounds(105, 100, 50, 20);
		pnlCue.add(getLblCue03Thumb());
		
		setLblCue04Thumb(new JLabel());
		getLblCue04Thumb().setBounds(105, 125, 50, 20);
		pnlCue.add(getLblCue04Thumb());

		
		// Cue Hex Test Label
		setLblCue01(new JLabel("-free-"));
		getLblCue01().setBounds(160, 50, 80, 20);
		pnlCue.add(getLblCue01());
		
		setLblCue02(new JLabel("-free-"));
		getLblCue02().setBounds(160, 75, 80, 20);
		pnlCue.add(getLblCue02());
		
		setLblCue03(new JLabel("-free-"));
		getLblCue03().setBounds(160, 100, 80, 20);
		pnlCue.add(getLblCue03());
		
		setLblCue04(new JLabel("-free-"));
		getLblCue04().setBounds(160, 125, 80, 20);
		pnlCue.add(getLblCue04());
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
			getLblCue01().setText(hexValue);
			getLblCue01Thumb().setOpaque(true);
			getLblCue01Thumb().setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		} else if (btnSource == btnCue02) {
			getLblCue02().setText(hexValue);
			getLblCue02Thumb().setOpaque(true);
			getLblCue02Thumb().setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		} else if (btnSource == btnCue03) {
			getLblCue03().setText(hexValue);
			getLblCue03Thumb().setOpaque(true);
			getLblCue03Thumb().setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		} else if (btnSource == btnCue04) {
			getLblCue04().setText(hexValue);
			getLblCue04Thumb().setOpaque(true);
			getLblCue04Thumb().setBackground(new Color(valueHexRed, valueHexGreen, valueHexBlue));
		}
	}

	
	// Call Cue
	public static void callCue(Object btnSource) {
		if (btnSource == btnCue01) {
			if (!getLblCue01().getText().equals("-free-")) {
				HexToDec.outputDec(getLblCue01().getText());
			}
		} else if (btnSource == btnCue02) {
			if (!getLblCue02().getText().equals("-free-")) {
				HexToDec.outputDec(getLblCue02().getText());
			}
		} else if (btnSource == btnCue03) {

			if (!getLblCue03().getText().equals("-free-")) {
				HexToDec.outputDec(getLblCue03().getText());
			}
		} else if (btnSource == btnCue04) {
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
