
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelClipBoard {

	private static JButton[] buttonArray = new JButton[3];
	private static JTextField[] textField = new JTextField[2];
	private static JLabel lblMessage;
	private static Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();

	static void panClip() {
		
		// TextFields
//		String[] TextfieldName = {"Output", "Input"};
		int[] xOffsetTextField = {15, 300};
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField();
			textField[i].setBounds(xOffsetTextField[i], 18, 70, 26);
			WindowMixer.getPanelArray()[3].add(textField[i]);
		}
		
		// TextField HEX output
		String strHexOut = Functions.makeHexStr(PanelMixer.getIntValue()[0],PanelMixer.getIntValue()[1],PanelMixer.getIntValue()[2]);	
		textField[0].setText(strHexOut);

		// Buttons
		String[] buttonName = {"Copy", "Paste", "Assume"};
		int[] xOffsetButton = {90,200,500};
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton(buttonName[i]);
			buttonArray[i].setBounds(xOffsetButton[i], 18, 90, 26);
			buttonArray[i].addActionListener(actions);
			WindowMixer.getPanelArray()[3].add(buttonArray[i]);
		}

		// Label Message
		lblMessage = new JLabel("Enter HEX value");
		lblMessage.setBounds(380, 18, 400, 26);
		WindowMixer.getPanelArray()[3].add(lblMessage);
	}

	// Buttons ActionListener 
	private static ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonArray[1]) {
					try {
						textField[1].setText(Functions.pasteFromClipboard());
					} catch (UnsupportedFlavorException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
			} else if (e.getSource() == buttonArray[2]) {
				lblMessage.setText(Functions.checkHexValue(textField[1].getText()));
			} else if (e.getSource() == buttonArray[0]) {
				StringSelection stringSelection = new StringSelection(textField[0].getText());
				clipBoard.setContents(stringSelection, null);
			}
		}
	};
	
	// Getter
	public static JTextField getTxtOutput() {
		return textField[0];
	}
}