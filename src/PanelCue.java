import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelCue {

    private static final int intYOffset = 25;
    private static int intAmountOfLabels = 0;
    private static int intAmountOfButtons = 0;
    private static final int maxAmountOfButtons = 16;
    private static boolean boolCueSelect = false;
    private static JButton buttonOverwrite;
    private static final JLabel[] cueLabelArray = new JLabel[16];
    private static final JButton[] cueButtonArray = new JButton[16];
    private static String cueHexText;

    public static void panCue() {

        buttonOverwrite = new JButton("Overwrite");
        buttonOverwrite.setBounds(8, 20, 150, 26);
        buttonOverwrite.addActionListener(event -> ChangeOverWrite());
        buttonOverwrite.setEnabled(false);
        WindowMixer.getPanelArray()[0].add(buttonOverwrite);
        AddCueButton();
    }

    public static void AddCueButton()  {
    	cueButtonArray[intAmountOfButtons] = new JButton("+ Cue");
    	cueButtonArray[intAmountOfButtons].setBounds(8, 50 + intAmountOfButtons * intYOffset, 90, 24);
    	cueButtonArray[intAmountOfButtons].addActionListener(actions);
        WindowMixer.getPanelArray()[0].add(cueButtonArray[intAmountOfButtons]);
        WindowMixer.getPanelArray()[0].repaint();
        intAmountOfButtons++;
    }

    public static void AddCueLabel() {
    	cueHexText = PanelOutput.getTxtOutput().getText();
        cueLabelArray[intAmountOfLabels] = new JLabel(cueHexText, SwingConstants.CENTER);
        cueLabelArray[intAmountOfLabels].setBounds(110, 50 + intAmountOfLabels * intYOffset, 100, 24);
        cueLabelArray[intAmountOfLabels].setBackground(Functions.HexToDec(cueHexText));
        cueLabelArray[intAmountOfLabels].setOpaque(true);
        WindowMixer.getPanelArray()[0].add(cueLabelArray[intAmountOfLabels]);
        WindowMixer.getPanelArray()[0].repaint();
        intAmountOfLabels++;
    }

    public static ActionListener actions = e -> {
        JButton btnCueSource = (JButton) e.getSource();
        if (Objects.equals(btnCueSource.getText(), "+ Cue")) {
        	getButtonOverwrite().setEnabled(true);
            btnCueSource.setText("Cue " + intAmountOfButtons);
            AddCueLabel();
            if(intAmountOfButtons < maxAmountOfButtons) { AddCueButton(); }            
        } else {
        	String buttonNumber = btnCueSource.getText().substring(4);
        	CueHandle(boolCueSelect, buttonNumber);
        }
    };

	private static void ChangeOverWrite() {
		if (boolCueSelect) {
			getButtonOverwrite().setForeground(Color.BLACK);
			boolCueSelect = false;
		} else {
			getButtonOverwrite().setForeground(Color.RED);
    		boolCueSelect = true;	
    	}
	}
	
	public static void CueHandle(Boolean cueSel, String bntNum) {
		if (cueSel) {
    		cueHexText = PanelOutput.getTxtOutput().getText();
    		cueLabelArray[Integer.parseInt(bntNum)-1].setBackground( Functions.HexToDec(cueHexText ));
    		cueLabelArray[Integer.parseInt(bntNum)-1].setText(cueHexText);
    		ChangeOverWrite(); 
		} else {
			WindowMixer.getCp().setBackground(Functions.HexToDec(cueLabelArray[Integer.parseInt(bntNum)-1].getText()));
		}	
	}

	// Getter
	public static JButton getButtonOverwrite() {
		return buttonOverwrite;
	}
	
	public static JButton[] getButtonArray() {
		return cueButtonArray;
	}
	
	public static JLabel[] getLabelArray() {
		return cueLabelArray;
	}
	public static int getIntAmountOfLabels() {
		return intAmountOfLabels;
	}
}
