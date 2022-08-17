package com.wipro.igrs.device.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import netscape.javascript.JSObject;
import Cogent.CgtFpCaptureAccess;

public class FingerPrintApplet extends JApplet implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5353991432443921970L;
	private JButton btnCapture;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	private JPanel panelImageMain;
	private JPanel panelImageCapture;
	private boolean retVal;
	private boolean captureFinished = true;
	private boolean isInitialized;
	private boolean isCaptured;
	private byte[] imageByteArray;
	private String imagePath = "./image.bmp";
	private Thread snapShotThread;
	private JTextArea txtConsole;

	private String paramGUID;
	private String paramParentPath;
	private String paramFileName;
	private long begin;
	private long end;
	private JButton click = new JButton("Initialize");
	private JButton shot = new JButton("Capture");
	private JButton save = new JButton("save Image");
	private boolean showB=false;
	/**
	 * Create the applet.
	 */
	public FingerPrintApplet() {
		setName("FingerPrintApplet");

		mainPanel = new JPanel();
		
		mainPanel.setBackground(new Color(95, 158, 160));
		mainPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		btnCapture = new JButton("Capture from Finger Print");
		btnCapture.setActionCommand("Capture");
		// btnCapture.addActionListener(this);

		btnCapture.setBounds(351, 132, 74, 23);
		// mainPanel.add(btnCapture);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(129, 132, 193, 219);
		mainPanel.add(tabbedPane);

		panelImageMain = new JPanel();
		panelImageMain.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Image", null, panelImageMain, null);
		panelImageMain.setLayout(null);

		panelImageCapture = new JPanel() {
			private static final long serialVersionUID = -3622039750876661807L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				super.paintComponents(g);
				try {
					//println("Inside panelImageCapture.paint()");
					if (imageByteArray != null && imageByteArray.length > 0) {
						println("Updating OnScreen image");
						InputStream in = new ByteArrayInputStream(
								imageByteArray);
						// println("Image stream created : " + in);
						Image imgOrig = ImageIO.read(in);
						// Image imgOrig = Toolkit.getDefaultToolkit()
						// .createImage(imageByteArray);
						in.close();
						// println("Image obtained : " + imgOrig);
						g.drawImage(imgOrig, 0, 0, this.getWidth(), this
								.getHeight(), null);

					}
					// File imageFile = new File(imagePath);
					// if (imageFile.exists()) {
					// Image imgOrig = ImageIO.read(imageFile);
					// g.drawImage(imgOrig, 0, 0, this.getWidth(),
					// this.getHeight(), null);
					// }
				} catch (Exception e) {
					println(e);
				}
			}
		};
		panelImageCapture.setBounds(10, 11, 168, 169);
		panelImageMain.add(panelImageCapture);
		//panelImageMain.add(click);
		//panelImageMain.add(shot);
		//panelImageCapture.add(click);
		//panelImageCapture.add(shot);
		txtConsole = new JTextArea();
		txtConsole.setWrapStyleWord(true);
		txtConsole.setLineWrap(true);
		txtConsole.setEditable(false);
		txtConsole.setBounds(10, 50, 432, 86);
		
		
		mainPanel.add(click);
		mainPanel.add(shot);
		mainPanel.add(save);
		click.setBounds(10, 23,80,23);
		shot.setBounds(10, 23,80,23);
		save.setBounds(95, 23, 100,23);
		click.setVisible(false);
		shot.setVisible(false);
		save.setVisible(false);
		
		mainPanel.add(txtConsole);
		click.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button clicked");
				//yahoo();
				//sendToServlet();
				try {
					initDevice();
					//Thread.sleep(3000);
					
						click.setVisible(false);
					shot.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		shot.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("capture button clicked");
				//yahoo();
				//sendToServlet();
				try {
					
						capt();
					
					
					save.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("save button clicked");
				//yahoo();
				//sendToServlet();
				try {
					sendToServer();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void init() {
		super.init();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCapture)) {
			// refresh();
			initDevice();
			// refresh();
			if (isInitialized) {
				captureFingerPrint();
				// refresh();
				if (isCaptured) {
					saveImage();
					// refresh();
				}
				deInitDevice();
				// refresh();
			}
		}
	}

	private void saveImage() {
		try {
			if (imageByteArray != null) {
				this.paintAll(this.getGraphics());
				System.out.println("Saving Image : " + imageByteArray);
				File outputImage = new File(imagePath);
				FileOutputStream fileOut = new FileOutputStream(outputImage,
						false);
				fileOut.write(imageByteArray);
				fileOut.close();
				tabbedPane.setSelectedIndex(0);
				panelImageCapture.repaint();
				System.out.println("File save complete path : "
						+ outputImage.getAbsolutePath());
				// sendCapturedNotice();
			} else {
				System.out.println("Image is null skipping save");
			}
			fallBackThreadStop();
		} catch (Exception e) {
			println(e);
		}

	}

	@SuppressWarnings("deprecation")
	private void fallBackThreadStop() {
		try {
			if (snapShotThread != null) {
				println("Snapshot thread state : " + snapShotThread.getState());
				if (snapShotThread.getState()
						.equals(Thread.State.TIMED_WAITING)) {
					println("Force stop on Snapshot thread");
					captureFinished = true;
					snapShotThread.stop();
				}
			}
		} catch (Exception e) {
			println(e);
		}

	}

	private void sendCapturedNotice() {
		try {
			JSObject jsHandler = JSObject.getWindow(this);
			jsHandler.call("unloadPage", new Object[] {});
			stop();
			destroy();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
private void sendErrMsg(String err){
	try {
		JSObject jsHandler = JSObject.getWindow(this);
		if(err.equalsIgnoreCase("init")){
			jsHandler.call("initFail", new Object[] {});
		}
		else if(err.equalsIgnoreCase("calib")){
			jsHandler.call("calibFail", new Object[] {});
		}
		
		stop();
		destroy();
	} catch (Throwable e) {
		e.printStackTrace();
	}
}
	public void initDevice() {
		try {

			println("Initializing Device : CG4_SCANNER_CSD450 ");
			begin = System.currentTimeMillis();
			System.out.println("initialize begin time: " + begin);
			isInitialized = false;
			int initRet = CgtFpCaptureAccess
					.cgtFingerPrintInit(CgtFpCaptureAccess.CG4_SCANNER_CSD450);
			end = System.currentTimeMillis();
			System.out.println("initialize end time: " + end);
			System.out.println("Time taken to initialise>> " + (end - begin));
			if (initRet < 0 && initRet!=(-607)) {
				println("Initialization Failed. Error Code: " + initRet);
				println("Last ErrorCode: "
						+ CgtFpCaptureAccess.cgtFingerPrintGetLastErrorCode());
				sendErrMsg("init");
			} else {
				//println("Calibration is in progress.");
				/*begin = System.currentTimeMillis();
				System.out.println("calibration begin time: " + begin);
				int calibrationRet = CgtFpCaptureAccess
						.cgtFingerPrintCalibrate();
				end = System.currentTimeMillis();
				System.out.println("calibration end time: " + end);
				System.out.println("Time taken to calibrate>> " + (end - begin));
				if (calibrationRet < 0) {//
				//	println("Calibration Failed. Error Code: " + calibrationRet);
					println("Last ErrorCode: "+ CgtFpCaptureAccess.cgtFingerPrintGetLastErrorCode());
					sendErrMsg("calib");
				} else {*/
					isInitialized = true;
					//println("Calibration Success.");
					println("Initialization Success.");
					shot.setVisible(true);
					/*try {
						for (int iLoop = 3; iLoop > 0; iLoop--) {
							println("Place finger on scanner within " + iLoop
									+ " second(s).");
							Thread.sleep(999);
						}
					} catch (Exception e) {
					}*/
				}
			/*}*/
		} catch (Throwable e) {
			println(e);
			e.printStackTrace();
		}

	}
	public void capt() throws InterruptedException{
		CaptureWorker();
		// refresh();
		System.out.println("Leaving main thread");
		//Thread.sleep(2000);
		System.out.println("Back to main thread");
		paintAll(getGraphics());
		showB=true;
		
	}
	private void deInitDevice() {
		try {
			println("De-Initializing Device : CG4_SCANNER_CSD450 ");
			begin = System.currentTimeMillis();
			System.out.println("De-Initializing begin time: " + begin);
			int ret = CgtFpCaptureAccess.cgtFingerPrintDeInit();
			end = System.currentTimeMillis();
			System.out.println("De-Initializing end time: " + end);
			System.out.println("Time taken to De-Initializing>> "
					+ (end - begin));
			if (ret < 0) {
				println("De-init Failed. Error Code: " + ret);
			} else {
				println("De-Initialized.");
			}
		} catch (Exception e) {
			println(e);
		}

	}

	public void captureFingerPrint() {
		try {
			CaptureWorker();
			// refresh();
			System.out.println("Leaving main thread");
			//Thread.sleep(2000);
			System.out.println("Back to main thread");

			fallBackThreadStop();

			if (captureFinished) {
				System.out.println("No of bytes read : " + imageByteArray.length);
				paintAll(getGraphics());
				// System.out.println("Codebase : " + this.getCodeBase());
				// http://localhost:8080/ApacheStrutsApp/
				// http://localhost:8080/ApacheStrutsApp/ImageCaptureServlet
				URL url = new URL(this.getCodeBase().toString()
						+ "/FingerPrintHelper");
				URLConnection connection = url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type",
						"application/binary");
				if (connection instanceof HttpURLConnection) {
					begin = System.currentTimeMillis();
					System.out.println("server begin time: " + begin);
					sendToServlet(imageByteArray,
							(HttpURLConnection) connection);
					end = System.currentTimeMillis();
					System.out.println("server end time: " + end);
					System.out.println("Time taken to drop file on server >> "
							+ (end - begin));
				}
				deInitDevice();
				// Added by Anuj, to validate if file is created on SERVER,
				// start
				InputStream is = connection.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				String a = (String) ois.readObject();
				System.out.println("Response from server>>>>>>> " + a);
				// end
				// added by Anuj, to set response to jsp, start
				if ("true".equalsIgnoreCase(a)) {
					setRetVal(true);

				} else {
					System.out.println("in else block");
					setRetVal(false);

				}
				// end
				sendCapturedNotice();
			}
		} catch (Exception e) {
			println(e);
		}

	}
	private void sendToServer() throws Exception{
println("Please wait...saving file");
		System.out.println("No of bytes read : " + imageByteArray.length);
		paintAll(getGraphics());
		// System.out.println("Codebase : " + this.getCodeBase());
		// http://localhost:8080/ApacheStrutsApp/
		// http://localhost:8080/ApacheStrutsApp/ImageCaptureServlet
		URL url = new URL(this.getCodeBase().toString()
				+ "/FingerPrintHelper");
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type",
				"application/binary");
		if (connection instanceof HttpURLConnection) {
			begin = System.currentTimeMillis();
			System.out.println("server begin time: " + begin);
			sendToServlet(imageByteArray,
					(HttpURLConnection) connection);
			end = System.currentTimeMillis();
			System.out.println("server end time: " + end);
			System.out.println("Time taken to drop file on server >> "
					+ (end - begin));
		}
		deInitDevice();
		// Added by Anuj, to validate if file is created on SERVER,
		// start
		InputStream is = connection.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		String a = (String) ois.readObject();
		System.out.println("Response from server>>>>>>> " + a);
		// end
		// added by Anuj, to set response to jsp, start
		if ("true".equalsIgnoreCase(a)) {
			setRetVal(true);

		} else {
			System.out.println("in else block");
			setRetVal(false);

		}
		// end
		sendCapturedNotice();
	
	}

	private void CaptureWorker() {

		try {
			println("Capture Started : CG4_SCANNER_CSD450 ");
			captureFinished = false;
			isCaptured = false;
			
			SnapShotWorker();
			imageByteArray = CgtFpCaptureAccess
					.cgtFingerPrintCaptureStart(1,
							CgtFpCaptureAccess.CG4_FLAT_SINGLE_FINGER,
							CgtFpCaptureAccess.CG4_IMAGE_RESOLUTION_500,
							true, true);

			isCaptured = true;
			println("Capture Completed..Please wait updating onScreen image");
		} catch (Exception e) {
			println(e);
		}

	

	}

	public void SnapShotWorker() {

		println("Capturing image : CG4_SCANNER_CSD450 ");

		// refresh();
		imageByteArray = null;
		imageByteArray = CgtFpCaptureAccess.cgtFingerPrintSnapShot();
		//System.out.println("byte array length>"+imageByteArray.length);
		if (imageByteArray != null) {
			captureFinished = true;
			println("Image captured : CG4_SCANNER_CSD450 ");
			System.out.println("Byte Array Size : " + imageByteArray.length);
			// refresh();
			isCaptured = true;
		}
		else{
			System.out.println("NULL");
		}
		
	
	
		
	}

	private void sendToServlet(byte[] byteContent, HttpURLConnection connection) {
		try {
			HttpURLConnection hc = (HttpURLConnection) (connection);
			System.out.println("url is>>" + hc);
			hc.setRequestMethod("POST");
			OutputStream os = hc.getOutputStream();
			HashMap<String, Object> payload = new HashMap<String, Object>();
			payload.put("guid", paramGUID);
			payload.put("binary", byteContent);
			payload.put("fileName", paramFileName);
			payload.put("parentPath", paramParentPath);
			System.out.println("Sending payload : " + payload);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(payload);
			System.out.println("File uploaded. Response Code is "
					+ hc.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void println(Exception e) {
		StringBuilder builder = new StringBuilder();
		builder.append(e.getMessage());
		builder.append("\r\n");
		StackTraceElement[] stackTrace = e.getStackTrace();
		for (StackTraceElement element : stackTrace) {
			builder.append(element);
			builder.append("\r\n");
		}
		builder.append("\r\n");
		// refresh();
		System.out.print(builder);
		builder.delete(0, builder.length());
	}

	protected void println(Object line) {
		StringBuilder builder = new StringBuilder("" + line);
		builder.append("\r\n");
		System.out.print(builder);
		txtConsole.insert(builder.toString(), 0);
		refresh();
		builder.delete(0, builder.length());
	}

	protected void print(Exception e) {
		StringBuilder builder = new StringBuilder();
		builder.append(e.getMessage());
		builder.append("\r\n");
		StackTraceElement[] stackTrace = e.getStackTrace();
		for (StackTraceElement element : stackTrace) {
			builder.append(element);
			builder.append("\r\n");
		}
		// refresh();
		System.out.print(builder);
		builder.delete(0, builder.length());
	}

	protected void print(Object message) {
		StringBuilder builder = new StringBuilder("" + message);
		System.out.print(builder);
		txtConsole.insert(builder.toString(), 0);
		refresh();
		builder.delete(0, builder.length());
	}

	public void refresh() {
		try {
			// paintAll(getGraphics());
		} catch (Exception e) {
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		System.out.println("Got new imagePath : " + imagePath);
		this.imagePath = imagePath;
	}

	public boolean isCaptured() {
		return isCaptured;
	}

	public String getParamGUID() {
		return paramGUID;
	}

	public void setParamGUID(String paramGUID) {
		this.paramGUID = paramGUID;
	}

	public String getParamParentPath() {
		return paramParentPath;
	}

	public void setParamParentPath(String paramParentPath) {
		this.paramParentPath = paramParentPath;
	}

	public String getParamFileName() {
		return paramFileName;
	}

	public void setParamFileName(String paramFileName) {
		this.paramFileName = paramFileName;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	public boolean getRetVal() {
		return retVal;
	}

	public void setRetVal(boolean retVal) {
		this.retVal = retVal;
	}
	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
		
	}

	@Override
	public void stop() {
		System.out.println("Stop");
		super.stop();
		
	}
	public void exiting() {
		stop();
		destroy();
		System.exit(ABORT);
	}
}
