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

	public static Color MakeColorFromString(String StrHexCode, boolean SetSliderValues) {
		int rgb[] = SplitHexStrToInt(StrHexCode);
		if (SetSliderValues) {
			PanelMixer.getSliderArray()[3].setValue((rgb[0]+rgb[1]+rgb[2])/3);
			PanelMixer.getSliderArray()[0].setValue(rgb[0]);
			PanelMixer.getSliderArray()[1].setValue(rgb[1]);
			PanelMixer.getSliderArray()[2].setValue(rgb[2]);	
		}
		return new Color(rgb[0], rgb[1], rgb[2]);
	}
	
	// Int DEC value to String Percent value
	public static String DecToPercent(int value) {
		String percent = (Integer.toString((value * 100) / 255) + " %");	
		return percent;
	}

	public static int[] SplitHexStrToInt(String hexString) {
		int rgb[] = new int[3];
		rgb[0] = Integer.parseInt(hexString.substring(0, 2), 16);
		rgb[1] = Integer.parseInt(hexString.substring(2, 4), 16);
		rgb[2] = Integer.parseInt(hexString.substring(4, 6), 16);
		return rgb;
	}
	
	public static String makeHexStr(int r, int g, int b) {
		String strHexOut = String.format("%02x", r) + String.format("%02x", g) + String.format("%02x", b);
		return strHexOut;
	}
}
