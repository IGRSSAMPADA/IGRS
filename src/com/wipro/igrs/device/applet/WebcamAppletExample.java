package com.wipro.igrs.device.applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import netscape.javascript.JSObject;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

//import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;

public class WebcamAppletExample extends JApplet implements ActionListener {

	private static final long serialVersionUID = 3517366452510566924L;

	private Dimension size = WebcamResolution.QQVGA.getSize();
	private Webcam webcam;
	private WebcamPanel panel;
	private String paramGUID;
	private String paramParentPath;
	private String paramFileName;
	private JTextArea txtConsole = new JTextArea();
	private ReentrantLock lock = new ReentrantLock();
	JButton click = new JButton("Capture");
	JButton shot = new JButton("Save and Exit");
	Image img;
	BufferedImage image;
	File file;
	private boolean retVal;
	public WebcamAppletExample() {
		super();
		System.out.println("Construct");
	}

	@Override
	public synchronized void start() {
	}

	public void yahoo() {
		System.out.println("in yahoo method");
		setImage(webcam.getImage());
		setImage(image);

	}

	public void istart() {

		System.out.println("Start");

		super.start();


		setLayout(new FlowLayout());
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(176, 144));
		System.out.println(webcam);
		panel = new WebcamPanel(webcam);
		add(panel);
		//jp.add(panel);
		int LEFT;
		int CENTER;
		add(click, 0);
		add(shot, 0);
		panel.setBackground(new Color(95, 158, 160));
		panel.setBorder(new LineBorder(Color.BLACK, 1, true));
		click.setVisible(true);
		shot.setVisible(false);
		add(panel);
		shot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("saving and exiting");
				sendCapturedNotice();

			}
		});

		click.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button clicked");
				yahoo();
				sendToServlet();
				try {
					Thread.sleep(3000);
					if(retVal){
					shot.setVisible(true);
					}
					else{
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public void sendToServlet() {
		try {

			ByteArrayOutputStream baos;
			byte[] bytes;

			baos = new ByteArrayOutputStream();
			ImageIO.write(getImage(), "gif", baos);
			baos.flush();
			bytes = baos.toByteArray();
			baos.close();

			String getCodeBase = this.getCodeBase().toString();
			URL url = new URL(getCodeBase + "/WebcameraHelper");
			System.out.println("url is>>>" + url);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/binary");
			HttpURLConnection hc = (HttpURLConnection) (connection);
			System.out.println(hc);
			hc.setRequestMethod("POST");
			OutputStream os = hc.getOutputStream();
			HashMap<String, Object> payload = new HashMap<String, Object>();
			payload.put("guid", paramGUID);

			payload.put("binary", bytes);
			payload.put("fileName", paramFileName);
			payload.put("parentPath", paramParentPath);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(payload);
			System.out.println("File uploaded. Response Code is "
					+ hc.getResponseCode());
			//Added by Anuj, to validate if file is created on SERVER, start
			InputStream is = hc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			String a= (String)ois.readObject();
			System.out.println("Response from server>>>>>>> "+a);
			//end
			//added by Anuj, to set response to jsp, start
			if("true".equalsIgnoreCase(a)){
				setRetVal(true);
				
			}
			else{
				System.out.println("in else block");
				setRetVal(false);
				sendErrorNotice();
			}
			//end
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {

		System.out.println(getParamParentPath() + "/" + getParamGUID() + "/"
				+ getParamFileName());
		return getParamParentPath() + "/" + getParamGUID() + "/" + "PHOTO.jpg";

		// return "C:\\Upload\\abc\\PHOTO.jpg";
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
		builder.delete(0, builder.length());
	}

	private void sendCapturedNotice() {
		try {
			webcam.getLock().unlock();
			JSObject jsHandler = JSObject.getWindow(this);
			jsHandler.call("unloadPage", new Object[] {});
			System.exit(ABORT);
		} catch (Exception e) {
		}

	}
private void sendErrorNotice(){
	try {
		webcam.getLock().unlock();
		JSObject jsHandler = JSObject.getWindow(this);
		jsHandler.call("unloadPage", new Object[] {});
		
	} catch (Exception e) {
		e.printStackTrace();
	}

}
	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
		if (webcam.open()) {
			webcam.close();
		}
	}

	@Override
	public void stop() {
		System.out.println("Stop");
		super.stop();
		if (webcam.open()) {
			webcam.close();
		}
	}

	public void paint(Graphics g) {
	}

	@Override
	public void init() {
		System.out.println("Init");
		super.init();
	}

	public String getParamGUID() {
		return paramGUID;
	}

	public void setParamGUID(String paramGUID) {
		this.paramGUID = paramGUID;
		System.out.println(this.paramGUID);
	}

	public String getParamParentPath() {
		return paramParentPath;
	}

	public void setParamParentPath(String paramParentPath) {
		this.paramParentPath = paramParentPath;
		System.out.println(this.paramParentPath);
	}

	public String getParamFileName() {
		return paramFileName;
	}

	public void setParamFileName(String paramFileName) {
		this.paramFileName = paramFileName;
		System.out.println(this.paramFileName);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("action performed" + e.getActionCommand());

	}

	public synchronized BufferedImage getImage() {
		System.out.println("getting image");
		return image;
	}

	public synchronized void setImage(BufferedImage image) {
		System.out.println("setting image");
		this.image = image;
	}
	

	public boolean getRetVal() {
		return retVal;
	}

	public void setRetVal(boolean retVal) {
		this.retVal = retVal;
	}

	@SuppressWarnings("deprecation")
	public void exiting() {
		try {
			if (webcam.open()) {
				webcam.close();
			}
			try {
				Thread.currentThread().stop();
			} catch (Exception e) {
			}
			stop();
			destroy();
			// System.exit(ABORT);
			System.exit(0);
		} catch (Exception e) {
			System.exit(0);
		}
	}

}