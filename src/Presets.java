import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Presets {
	
	public static void read(File file) {

		try {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(file));
			String row = null;
			int i = 0;
			int CueNummer[] = new int[20];
			int red[] = new int[20];
			int green[] = new int[20];
			int blue[] = new int[20];
			String valueHex[] = new String[20];

			while ((row = in.readLine()) != null) {

				valueHex[i] = row.substring(3, 9);

				if (!valueHex[i].equals("-free-")) {

					CueNummer[i] = Integer.parseInt(row.substring(0, 2));
					red[i] = Integer.parseInt(row.substring(3, 5), 16);
					green[i] = Integer.parseInt(row.substring(5, 7), 16);
					blue[i] = Integer.parseInt(row.substring(7, 9), 16);

					if (CueNummer[i] == 1) {
						PanelCue.getLblCue01().setText(valueHex[i]);
						PanelCue.getLblCue01Thumb().setOpaque(true);
						PanelCue.getLblCue01Thumb().setBackground(new Color(red[i],
								green[i], blue[i]));
					}
					if (CueNummer[i] == 2) {
						PanelCue.getLblCue02().setText(valueHex[i]);
						PanelCue.getLblCue02Thumb().setOpaque(true);
						PanelCue.getLblCue02Thumb().setBackground(new Color(red[i],
								green[i], blue[i]));
					}
					if (CueNummer[i] == 3) {
						PanelCue.getLblCue03().setText(valueHex[i]);
						PanelCue.getLblCue03Thumb().setOpaque(true);
						PanelCue.getLblCue03Thumb().setBackground(new Color(red[i],
								green[i], blue[i]));
					}
					if (CueNummer[i] == 4) {
						PanelCue.getLblCue04().setText(valueHex[i]);
						PanelCue.getLblCue04Thumb().setOpaque(true);
						PanelCue.getLblCue04Thumb().setBackground(new Color(red[i],
								green[i], blue[i]));
					}
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
		String[] row = new String[20];
		row[0] = "01:" + PanelCue.getLblCue01().getText();
		row[1] = "02:" + PanelCue.getLblCue02().getText();
		row[2] = "03:" + PanelCue.getLblCue03().getText();
		row[3] = "04:" + PanelCue.getLblCue04().getText();

		try {
			File pfad = PanelPreset.save.getCurrentDirectory();
			File saveFile = new File(pfad + "/" + file);
			FileWriter fw = null;
			if (saveFile.exists()) {
				int antwort = JOptionPane.showConfirmDialog(null,
						"File already exists. Overwrite?", "Alert",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (antwort == JOptionPane.OK_OPTION) {
					writable = true;
				} else if (antwort == JOptionPane.NO_OPTION) {
					writable = false;
				} else if (antwort == JOptionPane.CANCEL_OPTION) {
					writable = false;
				} else if (antwort == JOptionPane.CLOSED_OPTION) {
					writable = false;
				}
			} else {
				writable = true;
			}

			if (writable == true) {
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
				PanelPreset.get_fileNameTF().setText(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
