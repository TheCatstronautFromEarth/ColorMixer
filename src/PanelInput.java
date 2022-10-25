import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelInput {

	private static JButton btnAssume, btnGetClipboard;
	private static JTextField tFInput;
	private static JLabel lblMessage;
	private static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	static void panClip() {

		// Panel Hex Input
		JPanel pnlInput = new JPanel();
		pnlInput.setLayout(null);
		pnlInput.setBounds(15, 258, 230, 100);
		pnlInput.setOpaque(false);
		pnlInput.setBorder(BorderFactory.createTitledBorder(loweredetched, "HEX input"));
		WindowMixer.getCp().add(pnlInput);
		
		// Button Paste value from Clipboard
		btnGetClipboard = new JButton("Paste");
		btnGetClipboard.setBounds(110, 18, 90, 24);
		btnGetClipboard.addActionListener(actions);
		pnlInput.add(btnGetClipboard);

		// Button Assume value from Textfield
		btnAssume = new JButton("Assume");
		btnAssume.setBounds(12, 64, 90, 24);
		btnAssume.addActionListener(actions);
		pnlInput.add(btnAssume);
		
		// TextField HEX hexInput
		tFInput = new JTextField();
		tFInput.setBounds(12, 18, 90, 24);
		pnlInput.add(tFInput);
		
		// Label Message
		lblMessage = new JLabel("Enter HEX value");
		lblMessage.setBounds(22, 40, 400, 24);
		pnlInput.add(lblMessage);
	}

	// Buttons ActionListener 
	private static ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnGetClipboard) {
					try {
						tFInput.setText(Functions.pasteFromClipboard());
					} catch (UnsupportedFlavorException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
			} else if (e.getSource() == btnAssume) {
				lblMessage.setText(Functions.checkHexValue(tFInput.getText()));
			}
		}
	};
}