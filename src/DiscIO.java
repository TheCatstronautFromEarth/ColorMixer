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
			int i = 0;
			int red[] = new int[16];
			int green[] = new int[16];
			int blue[] = new int[16];
			String valueHex[] = new String[16];
			int existAmmountOfLabel = PanelCue.getAmountOfLabels();		
			while ((row = in.readLine()) != null) {
				valueHex[i] = row.substring(3, 9);
				red[i] = Integer.parseInt(row.substring(3, 5), 16);
				green[i] = Integer.parseInt(row.substring(5, 7), 16);
				blue[i] = Integer.parseInt(row.substring(7, 9), 16);
				existAmmountOfLabel = PanelCue.getAmountOfLabels();
				if (i == existAmmountOfLabel) {
					PanelCue.AddCueLabel();
					if (i < 15) {
						PanelCue.AddCueButton();
					}
				}
				PanelCue.getButtonArray()[i].setText("Cue " + Integer.toString(i+1));
				PanelCue.getLabelArray()[i].setText(valueHex[i]);
				PanelCue.getLabelArray()[i].setOpaque(true);
				PanelCue.getLabelArray()[i].setBackground(new Color(red[i], green[i], blue[i]));
				PanelCue.getButtonOverwrite().setEnabled(true);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String file) {
		boolean writable = false;
		String[] row = new String[16];
		PanelCue.getLabelArray();
		for (int i = 0; i < PanelCue.getAmountOfLabels(); i++) {
			row[i] = String.format("%02d", (i+1)) + ":" +PanelCue.getLabelArray()[i].getText();
		}
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
				for (int i = 0; i <= PanelCue.getAmountOfLabels()-1; i++) {
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