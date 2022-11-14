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
	
	// Convert String Hex to int Dec
	public static Color HexToDec(String StrHexCode) {
		int Red, Green, Blue;
		Red = Integer.parseInt(StrHexCode.substring(0,2), 16);
		Green = Integer.parseInt(StrHexCode.substring(2,4), 16);
		Blue = Integer.parseInt(StrHexCode.substring(4,6), 16);
		WindowMixer.SetWindowColor(Red, Green, Blue);
		PanelMixer.getSliderArray()[3].setValue((Red+Green+Blue)/3);
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
		String strHexOut = String.format("%02x", r) + String.format("%02x", g) + String.format("%02x", b);
		return strHexOut;
	}
}
