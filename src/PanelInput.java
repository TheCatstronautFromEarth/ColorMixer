import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
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
	private static JTextField txtInput;
	private static JLabel lblMessage;
	private static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	private static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

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
		txtInput = new JTextField();
		txtInput.setBounds(12, 18, 90, 24);
		pnlInput.add(txtInput);
		
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
					pasteFromClipboard();
				} catch (UnsupportedFlavorException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnAssume) {
				checkHexValue();
			}
		}
	};
	
	
	// Paste Hex from Clipboard
	public static void pasteFromClipboard() throws UnsupportedFlavorException, IOException {
		Transferable transfer = cb.getContents(null);
		txtInput.setText((String) transfer.getTransferData(DataFlavor.stringFlavor));		
	}	


	// Check Hex Input
	public static void checkHexValue() {

		String hexInput = txtInput.getText();
		int characters = 0;
		int txtInputLength = hexInput.length();
		String message = "";

		if (txtInputLength == 6) {
			for (int i = 0; i < 6; i++) {
				char[] K = hexInput.toCharArray();
				if ((K[i] >= 'A' && K[i] <= 'F')
						|| (K[i] >= 'a' && K[i] <= 'f')
						|| (Character.isDigit(hexInput.charAt(i)))) {
					characters++;
				}
			}
			if (characters == 6) {
				message = "Accept";
				HexToDec.outputDec(hexInput);
				characters = 0;
				
			} else {
				message = "Only numbers or A-F";
				characters = 0;
			}
		} else {
			message = "Only 6 digits";
		}
		lblMessage.setText(message);
	}
	
}
