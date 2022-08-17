package com.wipro.igrs.device.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.wipro.igrs.device.util.TwainNativeWrapper;

public class ScannerApplet extends JApplet implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8316730670544999565L;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;

	private boolean isInitialized;
	private boolean isCaptured;
	private JTextArea txtConsole;
	private JPanel panelImageMain;
	private JPanel panelImageCapture;

	private String paramGUID;
	private String paramParentPath;
	private String paramFileName;

	private TwainNativeWrapper wrapper;
	private BufferedImage img;
	private ArrayList<BufferedImage> images;
	private JButton btnNewButton;

	/**
	 * Create the applet.
	 */
	public ScannerApplet() {
		getContentPane().setLayout(new BorderLayout(0, 0));

		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(95, 158, 160));
		mainPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 132, 432, 337);
		mainPanel.add(tabbedPane);

		panelImageMain = new JPanel();
		panelImageMain.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Image", null, panelImageMain, null);
		panelImageMain
				.setLayout(new BoxLayout(panelImageMain, BoxLayout.X_AXIS));

		panelImageCapture = new JPanel() {
			private static final long serialVersionUID = -3622039750876661807L;

			@Override
			public void paint(Graphics g) {

				try {
					if (images != null && images.isEmpty() != true) {
						//println("Updating OnScreen image");
						Image imgOrig = images.get(images.size() - 1);
						g.drawImage(imgOrig, this.getX(), this.getY(),
								this.getWidth(), this.getHeight(), null);
						g.dispose();
					}
				} catch (Exception e) {
					println(e);
				}
			}
		};
		panelImageMain.add(panelImageCapture);
		panelImageCapture.setLayout(null);

		txtConsole = new JTextArea();
		txtConsole.setWrapStyleWord(true);
		txtConsole.setLineWrap(true);
		txtConsole.setEditable(false);
		txtConsole.setBounds(10, 23, 432, 86);
		mainPanel.add(txtConsole);

		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(165, 110, 89, 23);
		// btnNewButton.addActionListener(this);
		// mainPanel.add(btnNewButton);

		myInit();
	}

	private void myInit() {
		images = new ArrayList<BufferedImage>();

	}

	public boolean initTwainCall() {
		boolean retVal = false;
		try {
			wrapper = TwainNativeWrapper.getInstance();
			println("Checking TWAIN availability");
			retVal = (wrapper.isTwainAvailble() == 1);
			if (retVal) {
				println("TWAIN check successful");
			} else {
				println("TWAIN check failed");
			}
		} catch (Throwable t) {
			println(new Exception(t));
		}
		return retVal;
	}

	public boolean copyToClipboard() {
		boolean retVal = false;
		try {
			println("Initializing Scanner and Copying to Clipboard");
			retVal = (wrapper.copyToClipboard() == 1);
			if (retVal) {
				println("Image copy successful");
			} else {
				println("Image copy failed");
			}
		} catch (Throwable t) {
			println(new Exception(t));
		}
		return retVal;
	}

	public boolean getImageFromClipBoard() {
		boolean retVal = false;
		try {
			println("Copying from Clipboard");
			Clipboard clipUtil = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			Transferable transferUtil = clipUtil.getContents(null);
			boolean isImage = transferUtil
					.isDataFlavorSupported(DataFlavor.imageFlavor);
			if (isImage) {
				println("Clipboard contains an image");
				Object content = transferUtil
						.getTransferData(DataFlavor.imageFlavor);
				if (content instanceof BufferedImage) {
					println("Adding to list of captured images");
					img = (BufferedImage) content;
					//images.add(capturedImage);
					paintAll(getGraphics());
				}
				if(img!=null){
					retVal=true;
				}
				else{
					retVal=false;
				}
				//retVal = (images.isEmpty() == false);
			} else {
				println("Clipboard does not contains an image");
			}
			if (retVal) {
				println("Image grab successful");
			} else {
				println("Image grab failed");
			}
		} catch (Throwable t) {
			println(new Exception(t));
		}
		return retVal;
	}

	public boolean sendToServlet() {
		boolean retVal = false;
		try {
				ArrayList<byte[]> arrays = new ArrayList<byte[]>(images.size());
				URL url = new URL(this.getCodeBase().toString()
						+ "/ScannerHelper");
				ByteArrayOutputStream baos;
				byte[] bytes;

				baos = new ByteArrayOutputStream();
				ImageIO.write(img, "gif", baos);
				baos.flush();
				bytes = baos.toByteArray();
				baos.close();

				URLConnection connection = url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type",
						"application/binary");
				HttpURLConnection hc = (HttpURLConnection) (connection);
				hc.setRequestMethod("POST");
				OutputStream os = hc.getOutputStream();
				HashMap<String, Object> payload = new HashMap<String, Object>();
				payload.put("guid", paramGUID);
				//convertImages(arrays, images);
				payload.put("images", bytes);
				payload.put("fileName", paramFileName);
				payload.put("parentPath", paramParentPath);
				ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bufferStream);
				oos.writeObject(payload);
				oos.close();
				println("Payload size is " + bufferStream.toByteArray().length);
				oos = new ObjectOutputStream(os);
				oos.writeObject(payload);
				oos.close();
				//Added by Anuj, to validate if file is created on SERVER, start
				InputStream is = hc.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				String a= (String)ois.readObject();
				System.out.println("Response from server>>>>>>> "+a);
				//end
				int responseCode = hc.getResponseCode();
				println("File(s) uploaded. Response Code is " + responseCode);
				retVal = (responseCode == 200);
				//added by Anuj, to set response to jsp, start
				if("true".equalsIgnoreCase(a)){
					retVal=true;
				}
				else{
					retVal=false;
				}
				//end
			
		} catch (Throwable t) {
			println(new Exception(t));
		}
		return retVal;
	}

	public void clearImages() {
		if (images != null) {
			images.clear();
		}
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
		System.out.print(builder);
		builder.delete(0, builder.length());
	}

	protected void println(Object line) {
		StringBuilder builder = new StringBuilder("" + line);
		builder.append("\r\n");
		System.out.print(builder);
		txtConsole.insert(builder.toString(), 0);
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
		System.out.print(builder);
		builder.delete(0, builder.length());
	}

	protected void print(Object message) {
		StringBuilder builder = new StringBuilder("" + message);
		System.out.print(builder);
		txtConsole.insert(builder.toString(), 0);
		builder.delete(0, builder.length());
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(btnNewButton)) {
			// getImageFromClipBoard();
			// this.paramGUID = "" + Math.random() * 1000 + "-" + Math.random()
			// * 1000 + "-" + Math.random() * 1000;
			// this.paramParentPath = "D:/Upload/99";
			// sendToServlet();
		}

	}
public void exiting(){
	stop();
	destroy();
	System.exit(0);
}
	private void convertImages(ArrayList<byte[]> arrays,
			ArrayList<BufferedImage> images) {
		println("Converting images : " + images.size());
		try {
			ByteArrayOutputStream baos;
			byte[] bytes;

			for (BufferedImage bufferedImage : images) {
				baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "gif", baos);
				baos.flush();
				bytes = baos.toByteArray();
				baos.close();
				arrays.add(bytes);
			}
		} catch (Exception e) {
			println(e);
		}
	}
}
