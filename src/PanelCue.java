import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelCue {

	private static final int intYOffset = 25;
	private static final int maxAmountOfButtons = 16;
	private static final JLabel[] cueLabelArray = new JLabel[16];
	private static final JButton[] cueButtonArray = new JButton[16];
	private static int intAmountOfLabels = 0;
	private static int intAmountOfButtons = 0;
	private static boolean boolCueSelect = false;
	private static boolean boolDelete = false;
	private static JButton buttonOverwrite;
	private static JButton buttonDelete;
	private static String cueHexText;

	public static void panCue() {
		buttonOverwrite = new JButton("Overwrite");
		buttonOverwrite.setBounds(8, 20, 120, 26);
		buttonOverwrite.addActionListener(event -> ChangeOverWrite());
		buttonOverwrite.setEnabled(false);
		WindowMixer.getPanelArray()[0].add(buttonOverwrite);

		buttonDelete = new JButton("Del");
		buttonDelete.setBounds(125, 20, 80, 26);
		buttonDelete.addActionListener(event -> ChangeDelete());
		buttonDelete.setEnabled(false);
		WindowMixer.getPanelArray()[0].add(buttonDelete);
		AddCueButton();
	}

	public static void AddCueButton() {
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
		cueLabelArray[intAmountOfLabels].setBackground(Functions.MakeColorFromString(cueHexText, false));
		cueLabelArray[intAmountOfLabels].setOpaque(true);
		WindowMixer.getPanelArray()[0].add(cueLabelArray[intAmountOfLabels]);
		WindowMixer.getPanelArray()[0].repaint();
		intAmountOfLabels++;
	}

	// AL for AddCueButton()
	private static ActionListener actions = e -> {
		JButton btnCueSource = (JButton) e.getSource();
		// when pressed +Cue
		if (Objects.equals(btnCueSource.getText(), "+ Cue")) {
			// Enable Buttons & Menue when adding first cue
			buttonOverwrite.setEnabled(true);
			buttonDelete.setEnabled(true);
			MenueBar.getSave().setEnabled(true);

			btnCueSource.setText("Cue " + intAmountOfButtons);
			AddCueLabel();
			if (intAmountOfButtons < maxAmountOfButtons) {
				AddCueButton();
			}
		} else {
			int button = Integer.parseInt(btnCueSource.getText().substring(4)) - 1;
			CueHandle(boolCueSelect, boolDelete, button);
		}
	};

	private static void ChangeOverWrite() {
		if (boolCueSelect) {
			buttonOverwrite.setForeground(Color.BLACK);
			boolCueSelect = false;
			buttonDelete.setEnabled(true);
			cueButtonArray[intAmountOfButtons - 1].setEnabled(true);
		} else {
			buttonOverwrite.setForeground(Color.RED);
			boolCueSelect = true;
			buttonDelete.setEnabled(false);
			SetAddCueButtonDisabled();
		}
	}

	private static void ChangeDelete() {
		if (boolDelete) {
			buttonDelete.setForeground(Color.BLACK);
			boolDelete = false;
			// Set overwrite Button disable, when intAmountOfLabels is 0
			boolean enable = (intAmountOfLabels == 0) ? false : true;
			buttonOverwrite.setEnabled(enable);
			// Set AddCue Button enable
			cueButtonArray[intAmountOfButtons - 1].setEnabled(true);
		} else {
			buttonDelete.setForeground(Color.RED);
			boolDelete = true;
			// Set overwrite Button disable
			buttonOverwrite.setEnabled(false);
			SetAddCueButtonDisabled();
		}
	}

	private static void SetAddCueButtonDisabled() {
		if (Objects.equals(cueButtonArray[intAmountOfButtons - 1].getText(), "+ Cue")) {
			cueButtonArray[intAmountOfButtons - 1].setEnabled(false);
		}
	}

	private static void CueHandle(Boolean cueSel, Boolean delSel, int bntNum) {
		if (cueSel) {
			cueHexText = PanelOutput.getTxtOutput().getText();
			cueLabelArray[bntNum].setBackground(Functions.MakeColorFromString(cueHexText, false));
			cueLabelArray[bntNum].setText(cueHexText);
			ChangeOverWrite();
		} else if (delSel) {
			delCue(bntNum);
			ChangeDelete();
		} else {
			WindowMixer.getCp().setBackground(Functions.MakeColorFromString(cueLabelArray[bntNum].getText(), true));
		}
	}

	private static void delCue(int button) {
		if (button < 15) {
			if (intAmountOfLabels != 16) {
				WindowMixer.getPanelArray()[0].remove(cueButtonArray[intAmountOfButtons - 1]);
				intAmountOfButtons--;
			} 
			cueButtonArray[intAmountOfButtons - 1].setText("+ Cue");
			for (int i = button; i < intAmountOfLabels - 1; i++) {
				cueLabelArray[i].setText(cueLabelArray[i + 1].getText());
				cueLabelArray[i].setBackground(cueLabelArray[i + 1].getBackground());
			}
		} else if (button == 15) {
			cueButtonArray[intAmountOfButtons - 1].setText("+ Cue");
		}
		WindowMixer.getPanelArray()[0].remove(cueLabelArray[intAmountOfLabels - 1]);
		intAmountOfLabels--;
		// If there no more Cues, then diabled DelButton & Menue
		if (intAmountOfLabels == 0) {
			buttonDelete.setEnabled(false);
			MenueBar.getSave().setEnabled(false);
		}
		WindowMixer.getPanelArray()[0].repaint();
	}

	// Getter
	public static JButton getButtonOverwrite() {
		return buttonOverwrite;
	}

	public static JButton getButtonDelete() {
		return buttonDelete;
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
