import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelCue {

	private static JButton btnSetToCue;
	private static int valueHexRed, valueHexGreen, valueHexBlue;
	private static boolean cueSelect = false;
	private static JButton[] buttonArray = new JButton[4];
	private static JLabel[] labelThumbArray = new JLabel[4];
	private static JLabel[] labelHexArray = new JLabel[4];
	
	static void panCue() {
		
		// Button SetToCue 
		btnSetToCue = new JButton("Set To");
		btnSetToCue.setBounds(12, 18, 130, 24);
		btnSetToCue.addActionListener(actions);
		WindowMixer.getPanelArray()[2].add(btnSetToCue);
		
		// Button Cue
		String[] buttonName = {"Cue 1", "Cue 2", "Cue 3", "Cue 4"};
		int[] yOffsetButton = {48,73,98,123};
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton(buttonName[i]);
			buttonArray[i].setBounds(12, yOffsetButton[i], 90, 24);
			buttonArray[i].setEnabled(false);
			buttonArray[i].addActionListener(actions);
			WindowMixer.getPanelArray()[2].add(buttonArray[i]);
		}
		// Label Thumb
		int[] yOffset = {50,75,100,125};
		for (int i = 0; i < labelThumbArray.length; i++) {
			labelThumbArray[i] = new JLabel();
			labelThumbArray[i].setBounds(105, yOffset[i], 30, 20);
			WindowMixer.getPanelArray()[2].add(labelThumbArray[i]);
		}		
		// Label Hex value
		for (int i = 0; i < labelHexArray.length; i++) {
			labelHexArray[i] = new JLabel("-free-");
			labelHexArray[i].setBounds(150, yOffset[i], 60, 20);
			WindowMixer.getPanelArray()[2].add(labelHexArray[i]);
		}
	}

	// Buttons ActionListener 
	public static ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object btnSource = e.getSource();
			if (cueSelect) {
				btnSetToCue.setForeground(Color.BLACK);
				btnSetToCue.setText("Set To");	
				for (int i = 0; i < labelHexArray.length; i++) {	
					if (labelHexArray[i].getText().equals("-free-")) {
						buttonArray[i].setEnabled(false);
					}
				}
				setCue(btnSource);
				cueSelect = false;
			} else {
				if (btnSource == btnSetToCue) {
					btnSetToCue.setForeground(Color.RED);
					btnSetToCue.setText("Choose..");
					for (int i = 0; i < getButtonArray().length; i++) {
						buttonArray[i].setEnabled(true);
					}			
					cueSelect = true;
				} else {
					callCue(btnSource);
				}
			}
		}
	};

	// Set Cue
	public static void setCue(Object btnSource) {
		String hexValue = PanelClipBoard.getTxtOutput().getText();
		valueHexRed = Integer.parseInt(hexValue.substring(0, 2), 16);
		valueHexGreen = Integer.parseInt(hexValue.substring(2, 4), 16);
		valueHexBlue = Integer.parseInt(hexValue.substring(4, 6), 16);
		
		Color color = new Color(valueHexRed, valueHexGreen, valueHexBlue);
		
		for (int i = 0; i < buttonArray.length; i++) {
			if (btnSource == buttonArray[i]) {
				labelHexArray[i].setText(hexValue);
				labelThumbArray[i].setOpaque(true);
				labelThumbArray[i].setBackground(color);
				buttonArray[i].setEnabled(true);
			}
		}
	}
	
	// Call Cue
	public static void callCue(Object btnSource) {
		for (int i = 0; i < buttonArray.length; i++) {	
			if (btnSource == buttonArray[i]) {
				Functions.HexToDec(labelHexArray[i].getText());			
			}
		}	
	}
	
	// Getter
	public static JButton[] getButtonArray() {
		return buttonArray;
	}
	public static JLabel[] getLabelThumbArray() {
		return labelThumbArray;
	}
	public static JLabel[] getLabelHexArray() {
		return labelHexArray;
	}
}
