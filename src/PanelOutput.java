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

	static JButton btnSetClipboard;
	static JTextField txtOutput;
	static Border loweredetched;
	static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	static int xoffset = 12, yoffset = 18, buttonWidth = 90, textHight = 24;
	
	static void panOut() {
		
		JPanel OutBox = new JPanel();
		OutBox.setLayout(null);
		OutBox.setBounds(15, 366, 230, 56);
		OutBox.setOpaque(false);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		OutBox.setBorder(BorderFactory.createTitledBorder(loweredetched, "HEX Output"));
		ColorMixer.cp.add(OutBox);
		
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
		txtOutput = new JTextField();				
		txtOutput.setBounds(110, yoffset, buttonWidth, textHight);
		ColorMixer.txtOut = (ColorMixer.valueRed < 16 ? "0":"") + Integer.toHexString(ColorMixer.valueRed) + (ColorMixer.valueGreen < 16 ? "0":"") + Integer.toHexString(ColorMixer.valueGreen) + (ColorMixer.valueBlue < 16 ? "0":"") + Integer.toHexString(ColorMixer.valueBlue);
    	txtOutput.setText(String.valueOf(ColorMixer.txtOut));
    	OutBox.add(txtOutput);
    	
	}
	
	// Set Hex value to clipboard
	protected static void handlebtnSetClipboard(ActionEvent event) {
		StringSelection stringSelection = new StringSelection(txtOutput.getText());
		cb.setContents(stringSelection, null);
	}
}
