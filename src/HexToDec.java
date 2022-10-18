public class HexToDec {
	
	public static void outputDec(String StrHexCode) {
		int valueRed, valueGreen, valueBlue;
		valueRed = Integer.parseInt(StrHexCode.substring(0,2), 16);
		valueGreen = Integer.parseInt(StrHexCode.substring(2,4), 16);
		valueBlue = Integer.parseInt(StrHexCode.substring(4,6), 16);
		ColorMixer.sldRed.setValue(valueRed);
		ColorMixer.sldGreen.setValue(valueGreen);
		ColorMixer.sldBlue.setValue(valueBlue);
		ColorMixer.masterValue = (valueRed + valueGreen + valueBlue) / 3;
		ColorMixer.sldMaster.setValue(ColorMixer.masterValue);
	}
}
