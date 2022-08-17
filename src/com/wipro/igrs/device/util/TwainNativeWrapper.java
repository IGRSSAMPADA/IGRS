package com.wipro.igrs.device.util;

public class TwainNativeWrapper {

	/**
	 * Staic instance used by singleton
	 */
	private static final TwainNativeWrapper mInstance = new TwainNativeWrapper();

	/**
	 * Name of the C/C++ JNI DLL which communicates with the C TWAIN DLL. The
	 * file name is case sensative and must NOT contain the .DLL extension
	 */
	protected final String DLL_NAME = "EZTW32";

	private TwainNativeWrapper() {
		initLib();
	}

	/**
	 * Singleton interface point Using the single pattern in order to prevent
	 * unessary DLL initialization calls. As only one device typically is active
	 * at a time this design pattern is appropriate.
	 * 
	 * @return Static instance of class
	 */
	public static TwainNativeWrapper getInstance() {
		return mInstance;
	}

	/**
	 * Checks if the TWAIN Datasource Manager is available and can be loaded.
	 * Does not actually load the devices's datasources. But normally, the
	 * presence of the DSM means that at least one datasource has been
	 * installed. IsAvailable is fast after the first call It can be used to
	 * enable or disable menu items for example.
	 * 
	 * @return Determines if the underlying WIN32 OS has a TWAIN32
	 *         implementation installed. <CODE>1</CODE> if available
	 *         <CODE>1</CODE> if missing
	 */
	public native int isTwainAvailble();

	/**
	 * Captures image from TWAIN and stores it in the clipboard
	 * 
	 * @return <CODE>1</CODE> if available <CODE>1</CODE> if missing
	 */
	public native int copyToClipboard();

	private void initLib() {

		try {
			System.out.println("Loading : " + DLL_NAME + ".dll");
			System.loadLibrary(DLL_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}