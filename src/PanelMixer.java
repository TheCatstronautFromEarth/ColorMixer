import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class PanelMixer {

    private static final int[] intValue = new int[3];
    private static int intMasterValue = 200;
    private static boolean MasterLinear = true;
    private static final JSlider[] sliderArray = new JSlider[4];
    private static final JLabel[] labelArray = new JLabel[15];
    private static JButton btnSwitch;

    public static void panMix() {
        intValue[0] = 200;
        intValue[1] = 200;
        intValue[2] = 200;

        String[] sliderNames = { "Red", "Green", "Blue", "Master" };
        int[] xOffsetSlider = { 5, 90, 175, 280 };
        for (int i = 0; i < 4; i++) {
            sliderArray[i] = new JSlider(JSlider.VERTICAL, 0, 255, 128);
            sliderArray[i].setName(sliderNames[i]);
            sliderArray[i].setBounds(xOffsetSlider[i], 35, 70, 360);
            sliderArray[i].setValue(200);
            sliderArray[i].setMajorTickSpacing(10);
            sliderArray[i].setMinorTickSpacing(5);
            sliderArray[i].setPaintTicks(true);
            sliderArray[i].setSnapToTicks(false);
            sliderArray[i].setBackground(new Color(intValue[0], intValue[1], intValue[2]));
            sliderArray[i].addChangeListener(actions);
            sliderArray[i].setPaintLabels(true);
            if (i == 3) {
                sliderArray[i].setPaintLabels(false);
            }
            WindowMixer.getPanelArray()[1].add(sliderArray[i]);
        }

        // Initialize all Labels
        String[] str = { "Red", "Green", "Blue", "Master", "78 %", "78 %", "78 %", "200", "200", "200", "Dec", "c8", "c8", "c8", "Hex" };
        int[] xOffset = { 30, 115, 200, 295, 30, 115, 200, 30, 115, 200, 300, 30, 115, 200, 300 };
        int[] yOffset = { 15, 15, 15, 15, 400, 400, 400, 420, 420, 420, 420, 440, 440, 440, 440 };
        for (int i = 0; i < labelArray.length; i++) {
            labelArray[i] = new JLabel(str[i]);
            labelArray[i].setBounds(xOffset[i], yOffset[i], 100, 24);
            WindowMixer.getPanelArray()[1].add(labelArray[i]);
        }

        String btnText = "Linear";
        btnSwitch = new JButton(btnText);
        btnSwitch.setBounds(270, 400, 90, 24);
        btnSwitch.addActionListener(event -> ChangeMasterSlider());
        WindowMixer.getPanelArray()[1].add(btnSwitch);
    }

    private static final ChangeListener actions = e -> {
        JSlider source = (JSlider) e.getSource();
        if (source.getName().equals("Master")) {
            int intOldMasterValue = intMasterValue;
            intMasterValue = source.getValue();
            if (intOldMasterValue < intMasterValue) {
                masterSlider(MasterLinear, true);
            } else if (intOldMasterValue > intMasterValue) {
                masterSlider(MasterLinear, false);
            }
        } else {
            if (source.getName().equals("Red")) {
                rgbSliderChange(0, source);
            }
            if (source.getName().equals("Green")) {
                rgbSliderChange(1, source);
            }
            if (source.getName().equals("Blue")) {
                rgbSliderChange(2, source);
            }
        }
        WindowMixer.SetWindowColor(intValue[0], intValue[1], intValue[2]);
    };

    private static void ChangeMasterSlider() {
        if (btnSwitch.getActionCommand().equals("Linear")) {
            btnSwitch.setText("Log");
            MasterLinear = false;
        } else {
            btnSwitch.setText("Linear");
            MasterLinear = true;
        }
    }

    private static void rgbSliderChange(int sliderID, JSlider faderSource) {
        intValue[sliderID] = faderSource.getValue();
        labelArray[sliderID + 7].setText(Integer.toString(intValue[sliderID]));
        labelArray[sliderID + 11].setText(String.format("%02x", intValue[sliderID]));
        labelArray[sliderID + 4].setText(Functions.DecToPercent(intValue[sliderID]));
    }

    // Set RGB-Slider when MasterSlider is moving
    private static void masterSlider(boolean masterLinear, boolean moveUp) {
        if (masterLinear && moveUp) {
            for (int i = 0; i < sliderArray.length - 1; i++) {
                sliderArray[i].setValue(sliderArray[i].getValue() + 1);
            }
        } else if (!masterLinear && moveUp) {
            for (int i = 0; i < sliderArray.length - 1; i++) {
                sliderArray[i].setValue((int) (sliderArray[i].getValue() * 1.01 + 1));
            }
        } else if (masterLinear) {
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
    public static JSlider[] getSliderArray() {
        return sliderArray;
    }

}