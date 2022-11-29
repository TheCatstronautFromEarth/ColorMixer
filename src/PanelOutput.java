import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelOutput {
    private static JTextField txtOutput;
    private final static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

    static void panOutput() {
        // Button SetToClipboard
        JButton btnSetClipboard = new JButton("Copy");
        btnSetClipboard.setBounds(12, 18, 90, 24);
        btnSetClipboard.addActionListener(copyAction);
        WindowMixer.getPanelArray()[2].add(btnSetClipboard);

        // TextField HEX output
        txtOutput = new JTextField();
        txtOutput.setBounds(110, 18, 90, 24);
        txtOutput.setText("c8c8c8");
        WindowMixer.getPanelArray()[2].add(txtOutput);
    }

    // Buttons ActionListener Copy to ClipBoard
    private static final ActionListener copyAction = e -> {
        StringSelection stringSelection = new StringSelection(txtOutput.getText());
        cb.setContents(stringSelection, null);
    };

    // Getter
    public static JTextField getTxtOutput() {
        return txtOutput;
    }
}
