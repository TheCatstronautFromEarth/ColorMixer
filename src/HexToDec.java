public class HexToDec {
	
	public static void outputDec(String StrHexCode) {
		int valueRed, valueGreen, valueBlue;
		valueRed = Integer.parseInt(StrHexCode.substring(0,2), 16);
		valueGreen = Integer.parseInt(StrHexCode.substring(2,4), 16);
		valueBlue = Integer.parseInt(StrHexCode.substring(4,6), 16);
		WindowMixer.getSldRed().setValue(valueRed);
		WindowMixer.getSldGreen().setValue(valueGreen);
		WindowMixer.getSldBlue().setValue(valueBlue);
		WindowMixer.setMasterValue((valueRed + valueGreen + valueBlue) / 3);
		WindowMixer.getSldMaster().setValue(WindowMixer.getMasterValue());
	}
}
