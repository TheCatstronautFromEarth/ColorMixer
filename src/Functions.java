import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Functions {

	// Copy from ClipBoard
	private static Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	public static String pasteFromClipboard() throws UnsupportedFlavorException, IOException {
		String strfromCb;
		Transferable transfer = cb.getContents(null);		
		strfromCb = (String) transfer.getTransferData(DataFlavor.stringFlavor);		
		return strfromCb;		
	}
	
	// Check Hex Input
	public static String checkHexValue(String hexInput) {
		int characters = 0;
		int txtInputLength = hexInput.length();
		String message = "";
		if (txtInputLength == 6) {
			for (int i = 0; i < 6; i++) {
				char[] K = hexInput.toCharArray();
				if ((K[i] >= 'A' && K[i] <= 'F')
						|| (K[i] >= 'a' && K[i] <= 'f')
						|| (Character.isDigit(hexInput.charAt(i)))) {
					characters++;
				}
			}
			if (characters == 6) {
				message = "Accept";
				HexToDec(hexInput);
				characters = 0;
			} else {
				message = "Only 0-9 or A-F";
				characters = 0;
			}
		} else {
			message = "Only 6 digits";
		}
		return message;
	}
	
	// Convert String Hex to int Dec
	public static Color HexToDec(String StrHexCode) {
		int Red, Green, Blue;
		Red = Integer.parseInt(StrHexCode.substring(0,2), 16);
		Green = Integer.parseInt(StrHexCode.substring(2,4), 16);
		Blue = Integer.parseInt(StrHexCode.substring(4,6), 16);

		PanelMixer.getSliderArray()[0].setValue(Red);
		PanelMixer.getSliderArray()[1].setValue(Green);
		PanelMixer.getSliderArray()[2].setValue(Blue);
		
		return new Color(Red, Green, Blue);
	}

	// Int DEC value to String Percent value
	public static String DecToPercent(int value) {
		String percent = (Integer.toString((value * 100) / 255) + " %");	
		return percent;
	}

	public static String makeHexStr(int r, int g, int b) {
		String strHexOut = (r < 16 ? "0" : "")
				+ Integer.toHexString(r)
				+ (g < 16 ? "0" : "")
				+ Integer.toHexString(g)
				+ (b < 16 ? "0" : "")
				+ Integer.toHexString(b);
		return strHexOut;
	}
	
	public static String makeHexValStr(int col) {
		String strHexVal = (col < 16 ? "0" : "") + Integer.toHexString(col);
		return strHexVal;
		
	}
}
