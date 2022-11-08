import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelCue {

    private static final int yOffset = 25;
    private static int amountOfLabels = 0;
    private static int amountOfButtons = 0;
    private static final int maxAmountOfButtons = 16;
    private static boolean cueSelect = false;
    private static JButton buttonOverwrite;
    private static final JLabel[] cueLabelArray = new JLabel[16];
    private static final JButton[] cueButtonArray = new JButton[16];
    private static String cueHexText;

    public static void panCue() {

        buttonOverwrite = new JButton("Overwrite");
        buttonOverwrite.setBounds(8, 20, 150, 26);
        buttonOverwrite.addActionListener(event -> ChangeOverWrite());
        buttonOverwrite.setEnabled(false);
        WindowMixer.getPanelArray()[2].add(buttonOverwrite);

        AddCueButton();
    }

    public static void AddCueButton()  {
    	cueButtonArray[amountOfButtons] = new JButton("+ Cue");
    	cueButtonArray[amountOfButtons].setBounds(8, 50 + amountOfButtons * yOffset, 90, 24);
    	cueButtonArray[amountOfButtons].addActionListener(actions);
        WindowMixer.getPanelArray()[2].add(cueButtonArray[amountOfButtons]);
        WindowMixer.getPanelArray()[2].repaint();
        amountOfButtons++;
    }

    public static void AddCueLabel() {
    	cueHexText = PanelClipBoard.getTxtOutput().getText();
        cueLabelArray[amountOfLabels] = new JLabel(cueHexText, SwingConstants.CENTER);
        cueLabelArray[amountOfLabels].setBounds(110, 50 + amountOfLabels * yOffset, 100, 24);
        cueLabelArray[amountOfLabels].setBackground(Functions.HexToDec(cueHexText));
        cueLabelArray[amountOfLabels].setOpaque(true);
        WindowMixer.getPanelArray()[2].add(cueLabelArray[amountOfLabels]);
        WindowMixer.getPanelArray()[2].repaint();
        amountOfLabels++;
    }

    public static ActionListener actions = e -> {
        JButton btnCueSource = (JButton) e.getSource();
        if (Objects.equals(btnCueSource.getText(), "+ Cue")) {
        	getButtonOverwrite().setEnabled(true);
            btnCueSource.setText("Cue " + amountOfButtons);
            AddCueLabel();
            if(amountOfButtons < maxAmountOfButtons) { AddCueButton(); }            
        } else {
        	String buttonNumber = btnCueSource.getText().substring(4);
        	CueHandle(cueSelect, buttonNumber);
        }
    };

	private static void ChangeOverWrite() {
		if (cueSelect) {
			getButtonOverwrite().setForeground(Color.BLACK);
			cueSelect = false;
		} else {
			getButtonOverwrite().setForeground(Color.RED);
    		cueSelect = true;	
    	}
	}
	
	public static void CueHandle(Boolean cueSel, String bntNum) {
		if (cueSel) {
    		cueHexText = PanelClipBoard.getTxtOutput().getText();
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
	public static int getAmountOfButtons() {
		return amountOfButtons;
	}
	
	public static JLabel[] getLabelArray() {
		return cueLabelArray;
	}
	public static int getAmountOfLabels() {
		return amountOfLabels;
	}
}
