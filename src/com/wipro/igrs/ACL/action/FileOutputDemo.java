package com.wipro.igrs.ACL.action;

import java.io.*;

class FileOutputDemo {
	public static void main(String args[]) {
		FileOutputStream out; // declare a file output object
		PrintStream p; // declare a print stream object
		try {
			// Create a new file output stream
			// connected to "myfile.txt"
			out = new FileOutputStream("myfile.txt");

			// Connect print stream to the output stream
			p = new PrintStream(out);
			p.println("I am going to test the data now.");
			p.close();
		} catch (Exception e) {
			System.err.println("Error writing to file");
		}

		File file = new File(".");
		String[] files = file.list();
		try {
			String data = readFileAsString("C:/Documents and Settings/neegaga/Desktop/myfile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
	private static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			// String readData = String.valueOf(buf, 0, numRead);
			fileData.append(buf, 0, numRead);
			// buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}
}
