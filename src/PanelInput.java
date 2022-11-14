import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PanelInput {

	private static JButton btnAssume, btnGetClipboard;
	private static JTextField tFInput;
	private static JLabel lblMessage;
	private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");

	static void panInput() {

		// Button Paste value from Clipboard
		btnGetClipboard = new JButton("Paste");
		btnGetClipboard.setBounds(110, 18, 90, 24);
		btnGetClipboard.addActionListener(actions);
		WindowMixer.getPanelArray()[3].add(btnGetClipboard);

		// Button Assume value from Textfield
		btnAssume = new JButton("Assume");
		btnAssume.setBounds(265, 18, 90, 24);
		btnAssume.setEnabled(false);
		btnAssume.addActionListener(event -> { Functions.HexToDec(tFInput.getText()); });
		WindowMixer.getPanelArray()[3].add(btnAssume);
		
		// TextField HEX hexInput
		tFInput = new JTextField();
		tFInput.setBounds(12, 18, 90, 24);
		tFInput.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
			}
			public void insertUpdate(DocumentEvent arg0) {
				CheckHexInput();
			}
			public void removeUpdate(DocumentEvent arg0) {
				CheckHexInput();
			}
		});
		WindowMixer.getPanelArray()[3].add(tFInput);
		
		// Label Message
		lblMessage = new JLabel("Enter HEX value");
		lblMessage.setBounds(22, 40, 400, 24);
		WindowMixer.getPanelArray()[3].add(lblMessage);
	}
	
	private static boolean isHexadecimal() {
	    final Matcher matcher = HEXADECIMAL_PATTERN.matcher(tFInput.getText());
	    return matcher.matches();
	}
	
	protected static void CheckHexInput() {
		String CheckText = null;
		if (isHexadecimal()) {	
			if (tFInput.getText().length() == 6){
				btnAssume.setEnabled(true);
				CheckText = "Input OK";
			} else {
				btnAssume.setEnabled(false);
				CheckText = "Only 6 digits";		
			}
		} else {
			if (tFInput.getText().length() == 0) {
				CheckText = "Enter HEX value";
			} else {
				CheckText = "Only 0-9 or A-F";
			}
		}
		lblMessage.setText(CheckText);
	}

	// Button paste from Clipboard ActionListener 
	private static ActionListener actions = e -> {
		if (e.getSource() == btnGetClipboard) {
			try {
				tFInput.setText(Functions.pasteFromClipboard());
			} catch (UnsupportedFlavorException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
	};
}