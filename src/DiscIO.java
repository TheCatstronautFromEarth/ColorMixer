import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class DiscIO {

	public static void read(File file) {

		try {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(file));
			String row = null;
			int i = 1;
			int red[] = new int[20];
			int green[] = new int[20];
			int blue[] = new int[20];
			String valueHex[] = new String[20];
			while ((row = in.readLine()) != null) {
				valueHex[i] = row.substring(3, 9);
				if (!valueHex[i].equals("-free-")) {
					red[i] = Integer.parseInt(row.substring(3, 5), 16);
					green[i] = Integer.parseInt(row.substring(5, 7), 16);
					blue[i] = Integer.parseInt(row.substring(7, 9), 16);
					PanelCue.getLabelHexArray()[i-1].setText(valueHex[i]);
					PanelCue.getLabelThumbArray()[i-1].setOpaque(true);
					PanelCue.getLabelThumbArray()[i-1].setBackground(new Color(red[i], green[i], blue[i]));
					PanelCue.getButtonArray()[i-1].setEnabled(true);
				} else {
					PanelCue.getLabelHexArray()[i-1].setText("-free-");
					PanelCue.getLabelThumbArray()[i-1].setBackground(new Color(PanelMixer.getIntValue()[0], PanelMixer.getIntValue()[1], PanelMixer.getIntValue()[2]));
					PanelCue.getLabelThumbArray()[i-1].setOpaque(false);
					PanelCue.getButtonArray()[i-1].setEnabled(false);
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String file) {
		int i;
		boolean writable = false;
		String[] row = new String[4];
		row[0] = "01:" + PanelCue.getLabelHexArray()[0].getText();
		row[1] = "02:" + PanelCue.getLabelHexArray()[1].getText();
		row[2] = "03:" + PanelCue.getLabelHexArray()[2].getText();
		row[3] = "04:" + PanelCue.getLabelHexArray()[3].getText();

		try {
			File pfad = PanelPreset.save.getCurrentDirectory();
			File saveFile = new File(pfad + "/" + file);
			FileWriter fw = null;
			if (saveFile.exists()) {
				int answer = JOptionPane.showConfirmDialog(null,
						"File already exists. Overwrite?", "Alert",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (answer == JOptionPane.OK_OPTION) {
					writable = true;
				} else if (answer == JOptionPane.NO_OPTION) {
					writable = false;
				} else if (answer == JOptionPane.CANCEL_OPTION) {
					writable = false;
				} else if (answer == JOptionPane.CLOSED_OPTION) {
					writable = false;
				}
			} else {
				writable = true;
			}

			if (writable) {
				fw = new FileWriter(saveFile.getPath(), false);
				PrintWriter pw = new PrintWriter(fw);
				for (i = 0; i <= 3; i++) {
					pw.println(row[i]);
				}
				fw.flush();
				fw.close();
				pw.flush();
				pw.close();
				JOptionPane.showMessageDialog(null, "Save successful");
				PanelPreset.getfileNameTF().setText(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}