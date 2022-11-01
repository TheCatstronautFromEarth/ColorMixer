import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelMixer {

	private static int intValue[] = new int[3];
	private static int intOldMasterValue = 200, intMasterValue = 200;
	private static String StrHexOut;
	private static boolean MasterLinear = true;
	private static JLabel[] labelTitleArray = new JLabel[4];
	private static JSlider[] sliderArray = new JSlider[4];
	private static JLabel[] labelPercentArray = new JLabel[3];
	private static JLabel[] labelDecArray = new JLabel[4];
	private static JLabel[] labelHexArray = new JLabel[4];
	private static JButton btnSwitch;

	public static void panMix() {
		intValue[0] = 200;
		intValue[1] = 200;
		intValue[2] = 200;
		Color color = new Color(intValue[0], intValue[1], intValue[2]);

		// Slider
		String[] sliderNames = { "Red", "Green", "Blue", "Master" };
		int[] xOffsetSlider = { 5, 90, 175, 280 };
		for (int i = 0; i < labelTitleArray.length; i++) {
			sliderArray[i] = new JSlider(JSlider.VERTICAL, 0, 255, 128);
			sliderArray[i].setName(sliderNames[i]);
			sliderArray[i].setBounds(xOffsetSlider[i], 35, 70, 360);
			sliderArray[i].setValue(200);
			sliderArray[i].setMajorTickSpacing(10);
			sliderArray[i].setMinorTickSpacing(5);
			sliderArray[i].setPaintTicks(true);
			sliderArray[i].setSnapToTicks(false);
			sliderArray[i].setBackground(color);
			sliderArray[i].addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					// If Fader Event Change
					JSlider source;
					source = (JSlider) evt.getSource();
					// If change Master Slider
					if (source.getName().equals("Master")) {
						intOldMasterValue = intMasterValue;
						intMasterValue = source.getValue();
						// If Masterfader moves up
						if (intOldMasterValue < intMasterValue) {
							masterSliderUp(MasterLinear);
						}
						// If Masterfader moves down
						if (intOldMasterValue > intMasterValue) {
							masterSliderDown(MasterLinear);
						}
					}
					// If change RGB sliders
					else {
						// If changed red slider
						if (source.getName().equals("Red")) {
							intValue[0] = source.getValue();
							redSliderChange();
						}
						// If changed green slider
						if (source.getName().equals("Green")) {
							intValue[1] = source.getValue();
							greenSliderChange();
						}
						// If changed blue slider
						if (source.getName().equals("Blue")) {
							intValue[2] = source.getValue();
							blueSliderChange();
						}
					}
					StrHexOut = Functions.makeHexStr(intValue[0], intValue[1], intValue[2]);
					PanelClipBoard.getTxtOutput().setText(StrHexOut);
					Color color = new Color(intValue[0], intValue[1], intValue[2]);
					WindowMixer.getCp().setBackground(color);
					for (int i = 0; i < sliderArray.length; i++) {
						sliderArray[i].setBackground(color);
					}
				}
			});
			if (i < 3) {
				sliderArray[i].setPaintLabels(true);
			} else {
				sliderArray[i].setPaintLabels(false);
			}
			WindowMixer.getPanelArray()[0].add(sliderArray[i]);
		}

		// Label Title Name
		String[] titleNames = { "Red", "Green", "Blue", "Master" };
		int[] xOffsetTitle = { 30, 115, 200, 295 };
		for (int i = 0; i < labelTitleArray.length; i++) {
			labelTitleArray[i] = new JLabel(titleNames[i]);
			labelTitleArray[i].setBounds(xOffsetTitle[i], 15, 50, 20);
			WindowMixer.getPanelArray()[0].add(labelTitleArray[i]);
		}

		// Label Percent Value
		String[] strPercentValues = { "78 %", "78 %", "78 %" };
		int[] xOffsetPercent = { 30, 115, 200 };
		for (int i = 0; i < labelPercentArray.length; i++) {
			labelPercentArray[i] = new JLabel(strPercentValues[i]);
			labelPercentArray[i].setBounds(xOffsetPercent[i], 400, 100, 24);
			WindowMixer.getPanelArray()[0].add(labelPercentArray[i]);
		}

		// Label Dec Value
		String[] strDecValues = { Integer.toString(intValue[0]),
				Integer.toString(intValue[1]), Integer.toString(intValue[2]),
				"Dec" };
		int[] xOffsetDec = { 30, 115, 200, 300 };
		for (int i = 0; i < labelDecArray.length; i++) {
			labelDecArray[i] = new JLabel(strDecValues[i]);
			labelDecArray[i].setBounds(xOffsetDec[i], 420, 100, 24);
			WindowMixer.getPanelArray()[0].add(labelDecArray[i]);
		}

		// Label Hex Value
		String[] strHexValues = { Integer.toHexString(intValue[0]),
				Integer.toHexString(intValue[1]),
				Integer.toHexString(intValue[2]), "Hex" };
		int[] xOffsetHex = { 30, 115, 200, 300 };
		for (int i = 0; i < labelHexArray.length; i++) {
			labelHexArray[i] = new JLabel(strHexValues[i]);
			labelHexArray[i].setBounds(xOffsetHex[i], 440, 100, 24);
			WindowMixer.getPanelArray()[0].add(labelHexArray[i]);
		}

		// Button Switch Master Log-Linear
		String btnText = "Linear";
		btnSwitch = new JButton(btnText);
		btnSwitch.setBounds(270, 400, 90, 24);
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Linear")) {
					btnSwitch.setText("Log");
					MasterLinear = false;
				} else {
					btnSwitch.setText("Linear");
					MasterLinear = true;
				}
			}
		});
		WindowMixer.getPanelArray()[0].add(btnSwitch);
	}

	private static void redSliderChange() {
		labelDecArray[0].setText(Integer.toString(intValue[0])); 			// Label Red Dec 
		labelHexArray[0].setText(Integer.toHexString(intValue[0])); 		// Label Red Hex 
		labelPercentArray[0].setText(Functions.DecToPercent(intValue[0]));	// Label Red Percent 
	}

	private static void greenSliderChange() {
		labelDecArray[1].setText(Integer.toString(intValue[1])); 			// Label Green Dec 
		labelHexArray[1].setText(Integer.toHexString(intValue[1])); 		// Label Green Hex 
		labelPercentArray[1].setText(Functions.DecToPercent(intValue[1])); 	// Label Green Percent 
	}

	private static void blueSliderChange() {
		labelDecArray[2].setText(Integer.toString(intValue[2])); 			// Label Blue Dec 
		labelHexArray[2].setText(Integer.toHexString(intValue[2])); 		// Label Blue Hex
		labelPercentArray[2].setText(Functions.DecToPercent(intValue[2])); 	// Label Blue Percent
	}

	// Set RGB-Slider when MasterSlider moves up
	private static void masterSliderUp(boolean masterLinear) {
		if (masterLinear) {
			for (int i = 0; i < sliderArray.length - 1; i++) {
				sliderArray[i].setValue(sliderArray[i].getValue() + 1);
			}
		} else {
			for (int i = 0; i < sliderArray.length - 1; i++) {
				sliderArray[i].setValue((int) (sliderArray[i].getValue() * 1.01 + 1));
			}
		}
	}

	// Set RGB-Slider when MasterSlider moves down
	private static void masterSliderDown(boolean masterLinear) {
		if (masterLinear) {
			for (int i = 0; i < sliderArray.length - 1; i++) {
				sliderArray[i].setValue(sliderArray[i].getValue() - 1);
			}
		} else {
			for (int i = 0; i < sliderArray.length - 1; i++) {
				sliderArray[i].setValue((int) (sliderArray[i].getValue() / 1.01));
			}
		}
	}

	// Getter
	public static int[] getIntValue() {
		return intValue;
	}

	public static JSlider[] getSliderArray() {
		return sliderArray;
	}

	public static String getStrHexOut() {
		return StrHexOut;
	}
}