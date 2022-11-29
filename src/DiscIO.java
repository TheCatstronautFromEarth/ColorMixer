import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class DiscIO {

    private final static JFileChooser open = new JFileChooser();
    private final static JFileChooser save = new JFileChooser();

    private static void read(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String row;
            int i = 0;
            int existAmountOfLabel;
            while ((row = in.readLine()) != null) {
                existAmountOfLabel = PanelCue.getIntAmountOfLabels();
                if (i == existAmountOfLabel) {
                    PanelCue.AddCueLabel();
                    if (i < 15) {
                        PanelCue.AddCueButton();
                    }
                }
                SetBtnAndLbl(i, row.substring(3, 9));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void SetBtnAndLbl(int i, String row) {
        PanelCue.getButtonArray()[i].setText("Cue " + (i + 1));
        PanelCue.getLabelArray()[i].setText(row);
        PanelCue.getLabelArray()[i].setOpaque(true);
        PanelCue.getLabelArray()[i].setBackground(Functions.MakeColorFromString(row, false));
        PanelCue.getButtonOverwrite().setEnabled(true);
        PanelCue.getButtonDelete().setEnabled(true);
        MenuBar.getSave().setEnabled(true);
    }

    private static void write(String file) {
        boolean writable = false;
        String[] row = new String[16];
        for (int i = 0; i < PanelCue.getIntAmountOfLabels(); i++) {
            row[i] = String.format("%02d", (i + 1)) + ":" + PanelCue.getLabelArray()[i].getText();
        }
        try {
            File path = save.getCurrentDirectory();
            File saveFile = new File(path + "/" + file);
            FileWriter fw;
            if (saveFile.exists()) {
                int answer = JOptionPane.showConfirmDialog(null, "File already exists. Overwrite?", "Alert", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (answer == JOptionPane.OK_OPTION) {
                    writable = true;
                }
            } else {
                writable = true;
            }
            if (writable) {
                fw = new FileWriter(saveFile.getPath(), false);
                PrintWriter pw = new PrintWriter(fw);
                for (int i = 0; i <= PanelCue.getIntAmountOfLabels() - 1; i++) {
                    pw.println(row[i]);
                }
                fw.flush();
                fw.close();
                pw.flush();
                pw.close();
                JOptionPane.showMessageDialog(null, "Save successful");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Open file
    protected static void openFile() {
        open.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        open.addChoosableFileFilter(new colorFileFilter());
        int returnValue = open.showOpenDialog(open);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = open.getSelectedFile();
            DiscIO.read(file);
        }
    }

    // Save file
    protected static void saveFile() {
        int returnValue;
        String SaveName;
        SaveName = "New_Preset.cmf";

        File file = new File(SaveName);
        save.setSelectedFile(file);
        returnValue = save.showSaveDialog(save);
        save.setFileSelectionMode(JFileChooser.FILES_ONLY);
        save.addChoosableFileFilter(new colorFileFilter());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String Name = save.getSelectedFile().getName();
            DiscIO.write(Name);
        }
    }

    // FileFilter
    private static class colorFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String filename = file.getName();
            return file.isDirectory() || filename.toLowerCase().endsWith(".cmf");
        }

        public String getDescription() {
            return "*.cmf";
        }
    }
}