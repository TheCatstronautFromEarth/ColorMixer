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
	private static Border loweredetched;
	private static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	private static int xoffset = 12, yoffset = 18, buttonWidth = 90, textHight = 24;
	
	static void panOut() {
		
		JPanel OutBox = new JPanel();
		OutBox.setLayout(null);
		OutBox.setBounds(15, 366, 230, 56);
		OutBox.setOpaque(false);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		OutBox.setBorder(BorderFactory.createTitledBorder(loweredetched, "HEX Output"));
		WindowMixer.getCp().add(OutBox);
		
		// Button Set value to clipboard 
		btnSetClipboard = new JButton("Copy");				
		btnSetClipboard.setBounds(xoffset, yoffset, buttonWidth, textHight);
		btnSetClipboard.setMnemonic('k');
		btnSetClipboard.setToolTipText("Copy to clipboard");
		btnSetClipboard.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent event) {
				handlebtnSetClipboard(event);
			}
		});
		OutBox.add(btnSetClipboard);
		
		// TextField HEX output	
		setTxtOutput(new JTextField());				
		getTxtOutput().setBounds(110, yoffset, buttonWidth, textHight);
		WindowMixer.setTxtOut((WindowMixer.getValueRed() < 16 ? "0":"") + Integer.toHexString(WindowMixer.getValueRed()) + (WindowMixer.getValueGreen() < 16 ? "0":"") + Integer.toHexString(WindowMixer.getValueGreen()) + (WindowMixer.getValueBlue() < 16 ? "0":"") + Integer.toHexString(WindowMixer.getValueBlue()));
    	getTxtOutput().setText(String.valueOf(WindowMixer.getTxtOut()));
    	OutBox.add(getTxtOutput());
    	
	}
	
	// Set Hex value to clipboard
	protected static void handlebtnSetClipboard(ActionEvent event) {
		StringSelection stringSelection = new StringSelection(getTxtOutput().getText());
		cb.setContents(stringSelection, null);
	}

	public static JTextField getTxtOutput() {
		return txtOutput;
	}

	public static void setTxtOutput(JTextField txtOutput) {
		PanelOutput.txtOutput = txtOutput;
	}
}
