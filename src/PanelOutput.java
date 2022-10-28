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
		txtOutput = new JTextField();
		txtOutput.setBounds(110, 18, 90, 24);
		
		
		// !!!!
		WindowMixer.setStrHexOut((
				WindowMixer.getIntValueRed() < 16 ? "0" : "")
				+ Integer.toHexString(WindowMixer.getIntValueRed())
				+ (WindowMixer.getIntValueGreen() < 16 ? "0" : "")
				+ Integer.toHexString(WindowMixer.getIntValueGreen())
				+ (WindowMixer.getIntValueBlue() < 16 ? "0" : "")
				+ Integer.toHexString(WindowMixer.getIntValueBlue()));
		txtOutput.setText(String.valueOf(WindowMixer.getStrHexOut()));
		
		pnlOutput.add(txtOutput);
	}

	// Buttons ActionListener Copy to ClipBoard
	private static ActionListener copyAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection stringSelection = new StringSelection(txtOutput.getText());
			cb.setContents(stringSelection, null);
		}
	};

	// Getter
	public static JTextField getTxtOutput() {
		return txtOutput;
	}
}
