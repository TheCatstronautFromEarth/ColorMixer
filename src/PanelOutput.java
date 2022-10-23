import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelOutput {

	private static JButton btnSetClipboard;
	private static JTextField txtOutput;
	private static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);;
	private static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

	static void panOut() {
		// Panel Output
		JPanel pnlOutput = new JPanel();
		pnlOutput.setLayout(null);
		pnlOutput.setBounds(15, 366, 230, 56);
		pnlOutput.setOpaque(false);
		pnlOutput.setBorder(BorderFactory.createTitledBorder(loweredetched, "HEX Output"));
		WindowMixer.getCp().add(pnlOutput);

		// Button SetToClipboard
		btnSetClipboard = new JButton("Copy");
		btnSetClipboard.setBounds(12, 18, 90, 24);
		btnSetClipboard.addActionListener(copyAction);
		pnlOutput.add(btnSetClipboard);

		// TextField HEX output
		setTxtOutput(new JTextField());
		getTxtOutput().setBounds(110, 18, 90, 24);
		WindowMixer.setTxtOut((
				WindowMixer.getValueRed() < 16 ? "0" : "")
				+ Integer.toHexString(WindowMixer.getValueRed())
				+ (WindowMixer.getValueGreen() < 16 ? "0" : "")
				+ Integer.toHexString(WindowMixer.getValueGreen())
				+ (WindowMixer.getValueBlue() < 16 ? "0" : "")
				+ Integer.toHexString(WindowMixer.getValueBlue()));
		getTxtOutput().setText(String.valueOf(WindowMixer.getTxtOut()));
		pnlOutput.add(getTxtOutput());
	}

	
	// Buttons ActionListener 
	private static ActionListener copyAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection stringSelection = new StringSelection(getTxtOutput().getText());
			cb.setContents(stringSelection, null);
		}
	};

	
	// Setter Getter
	public static JTextField getTxtOutput() {
		return txtOutput;
	}

	public static void setTxtOutput(JTextField txtOutput) {
		PanelOutput.txtOutput = txtOutput;
	}
}
