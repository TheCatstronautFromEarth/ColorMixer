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
	private static JLabel lblOutput;
	private static Border loweredetched;
	private static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	private static int xoffset = 12, yoffset = 18, buttonWidth = 90, textHight = 24;

	static void panClip() {

		// Panel Mixer
		JPanel ClipBox = new JPanel();
		ClipBox.setLayout(null);
		ClipBox.setBounds(15, 258, 230, 100);
		ClipBox.setOpaque(false);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		ClipBox.setBorder(BorderFactory.createTitledBorder(loweredetched,
				"HEX input"));
		WindowMixer.getCp().add(ClipBox);
		
		// Button get value from Clipboard
		btnGetClipboard = new JButton("Paste");
		btnGetClipboard.setBounds(110, yoffset, buttonWidth, textHight);
		btnGetClipboard.setMnemonic('g');
		btnGetClipboard.setToolTipText("Paste from clipboard");
		btnGetClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					handlebtnGetClipboard(event);
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		ClipBox.add(btnGetClipboard);

		// Button Assume value from Textfield
		btnAssume = new JButton("Assume");
		btnAssume.setBounds(xoffset, yoffset + 46, buttonWidth, textHight);
		btnAssume.setToolTipText("Assume HEX value");
		btnAssume.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent event) {
						handlebtnAssume(event);
					}
				});
		ClipBox.add(btnAssume);
		
		// TextFeld HEX hexInput
		txtInput = new JTextField();
		txtInput.setBounds(xoffset, yoffset, buttonWidth, textHight);
		ClipBox.add(txtInput);
		
		// Label message
		lblOutput = new JLabel("Enter HEX value");
		lblOutput.setBounds(xoffset + 10, 40, 400, textHight);
		ClipBox.add(lblOutput);
	}

	// Get Hex from Clipboard
	protected static void handlebtnGetClipboard(ActionEvent event)
			throws UnsupportedFlavorException, IOException {
		Transferable transfer = cb.getContents(null);
		txtInput.setText((String) transfer
				.getTransferData(DataFlavor.stringFlavor));
	}

	// Text Hex input
	protected static void handlebtnAssume(ActionEvent event) {

		String hexInput = txtInput.getText();
		int pruefe = 0;
		int laenge = hexInput.length();
		String message = "";

		if (laenge == 6) {
			for (int i = 0; i < 6; i++) {
				char[] K = hexInput.toCharArray();
				if ((K[i] >= 'A' && K[i] <= 'F')
						|| (K[i] >= 'a' && K[i] <= 'f')
						|| (Character.isDigit(hexInput.charAt(i)))) {
					pruefe++;
				}
			}
			if (pruefe == 6) {
				message = "Accept";
				pruefe = 0;
				HexToDec.outputDec(hexInput);
			} else {
				message = "Only numbers or A-F";
				pruefe = 0;
			}
		} else {
			message = "Only 6 digits";
		}
		lblOutput.setText(message);
	}

}
