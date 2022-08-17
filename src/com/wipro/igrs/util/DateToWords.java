package com.wipro.igrs.util;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateToWords {

	private static final String[] tensNames = { "", " ten", " twenty",
			" thirty", " forty", " fifty", " sixty", " seventy", " eighty",
			" ninety" };

	private static final String[] numNames = { "", " one", " two", " three",
			" four", " five", " six", " seven", " eight", " nine", " ten",
			" eleven", " twelve", " thirteen", " fourteen", " fifteen",
			" sixteen", " seventeen", " eighteen", " nineteen" };

	private static final String[] days = { "", "first", "second", "third",
			"fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth",
			"eleventh", "twelfth  ", "thirteenth", "fourteenth", "fifteenth",
			"sixteenth", "seventeenth", "eighteenth", "nineteenth",
			"twentieth", "twenty first", "twenty second",
			"twenty third", "twenty fourth", "twenty fifth", "twenty sixth",
			"twenty seventh", "twenty eighth", "twenty ninth", "thirtieth",
			"thirty first" };

	private static String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + " hundred" + soFar;
	}

	private static String convertYear(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "zero";
		}

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands)
					+ " thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;
		result = result.replaceAll("^\\s+", "");
		result = result.replaceAll("\\b\\s{2,}\\b", " ");
//		result = toTitleCase(result);
		return result;
	}
	
	private static String convertDecimal(String number) {
		
		StringBuilder decimalWord=new StringBuilder("");
		
		int length=number.length();
		
		if(length==1){
			
			int num=Integer.parseInt(number);
			
			switch(num){
			
			case 1:  decimalWord=decimalWord.append("One");
			break;
			case 2:  decimalWord=decimalWord.append("Two");
			break;
			case 3:  decimalWord=decimalWord.append("Three");
			break;
			case 4:  decimalWord=decimalWord.append("Four");
			break;
			case 5:  decimalWord=decimalWord.append("Five");
			break;
			case 6:  decimalWord=decimalWord.append("Six");
			break;
			case 7:  decimalWord=decimalWord.append("Seven");
			break;
			case 8:  decimalWord=decimalWord.append("Eight");
			break;
			case 9:  decimalWord=decimalWord.append("Nine");
			break;
			case 0:  decimalWord=decimalWord.append("Zero");
			break;
			
			
			
			
			}
			
			
			
			
		}else if(length==2){
			
			int num;
			
			for(int i=0;i<2;i++){
				
				num=Integer.parseInt(String.valueOf(number.charAt(i)));
				
				switch(num){
				
				case 1:  decimalWord=decimalWord.append(" One");
				break;
				case 2:  decimalWord=decimalWord.append(" Two");
				break;
				case 3:  decimalWord=decimalWord.append(" Three");
				break;
				case 4:  decimalWord=decimalWord.append(" Four");
				break;
				case 5:  decimalWord=decimalWord.append(" Five");
				break;
				case 6:  decimalWord=decimalWord.append(" Six");
				break;
				case 7:  decimalWord=decimalWord.append(" Seven");
				break;
				case 8:  decimalWord=decimalWord.append(" Eight");
				break;
				case 9:  decimalWord=decimalWord.append(" Nine");
				break;
				case 0:  decimalWord=decimalWord.append(" Zero");
				break;
				
				
				
				
				}
				
				
			}
			
			
			
			
		}
		
		
		
		return decimalWord.toString();
		
	}
	
	private static String convertAmount(Double number) {
		// 0 to 999 999 999 999
		
		String stringAmount=String.valueOf(number);
		
		String wholeNo="";
		String decimalNo="";
		String[] amountArr=stringAmount.split("\\.");
		
		if(amountArr!=null && amountArr.length==2){
			if(amountArr[0].equalsIgnoreCase("")){
				wholeNo="0";
			}else{
			wholeNo=amountArr[0];
			}
    		decimalNo=amountArr[1];
		}else{
			wholeNo=stringAmount;
    		//decimalNo=amountArr[1];	
		}
		
		String wholeNumberWords=convertYear(Long.parseLong(wholeNo));
		
		String decimalNumberWords=convertDecimal(decimalNo);
		
		return wholeNumberWords+" Rupees "+decimalNumberWords+" Paise Only";
	}

	private static String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}

			titleCase.append(c);
		}

		return titleCase.toString();
	}

	public static String convertDays(int day) {
		String retVal = "";
		try {
			retVal = days[day];
		} catch (Exception e) {
		}
		return retVal;
	}
	
	public static String convertMonths(int month) {
		String retVal = "";
		try {
			DateFormatSymbols helper = new DateFormatSymbols();
			retVal = helper.getMonths()[month];
		} catch (Exception e) {
		}
		return retVal;
	}
	
	public static String convertDateToWords(String day, String month, String year) {
		/*
		17:39:15,552  DEBUG [empmgmt.action.EmpMgmtAction] day : 10
		17:39:16,598  DEBUG [empmgmt.action.EmpMgmtAction] month : Sep
		17:39:17,286  DEBUG [empmgmt.action.EmpMgmtAction] year : 1977
		 */
		String retVal = "";
		try {
			StringBuilder sBldr = new StringBuilder();
			Date dobInput;
			Calendar calObject = Calendar.getInstance();
			SimpleDateFormat simpDF = new SimpleDateFormat("ddMMMyyyy");
			simpDF.setLenient(false);
			sBldr.append(day);
			sBldr.append(month);
			sBldr.append(year);
			dobInput = simpDF.parse(sBldr.toString());
			sBldr = new StringBuilder();
			calObject.setTime(dobInput);
			sBldr.append(DateToWords.convertDays(calObject.get(Calendar.DAY_OF_MONTH)).trim());
			sBldr.append(" of ");
			sBldr.append(DateToWords.convertMonths(calObject.get(Calendar.MONTH)).trim());
			sBldr.append(" ");
			sBldr.append(DateToWords.convertYear(calObject.get(Calendar.YEAR)).trim());
			retVal = toTitleCase(sBldr.toString());
			retVal = retVal.replaceAll("  ", " ");
			/*
			*** one thousand three hundred sixteen
			*** first
			*** January
			 */
		} catch (Exception e) {
		}
		return retVal;
	}
	
	public static String convertAmountToWords(String amount) {
		/*
		17:39:15,552  DEBUG [empmgmt.action.EmpMgmtAction] day : 10
		17:39:16,598  DEBUG [empmgmt.action.EmpMgmtAction] month : Sep
		17:39:17,286  DEBUG [empmgmt.action.EmpMgmtAction] year : 1977
		 */
		String retVal = "";
		try {
			StringBuilder sBldr = new StringBuilder();
			//Date dobInput;
			//Calendar calObject = Calendar.getInstance();
			//SimpleDateFormat simpDF = new SimpleDateFormat("ddMMMyyyy");
			//simpDF.setLenient(false);
			//sBldr.append(day);
			//sBldr.append(month);
			sBldr.append(amount);
			//dobInput = simpDF.parse(sBldr.toString());
			sBldr = new StringBuilder();
			//calObject.setTime(dobInput);
			//sBldr.append(DateToWords.convertDays(calObject.get(Calendar.DAY_OF_MONTH)).trim());
			//sBldr.append(" of ");
			//sBldr.append(DateToWords.convertMonths(calObject.get(Calendar.MONTH)).trim());
			//sBldr.append(" ");
			sBldr.append(convertAmount(Double.parseDouble(amount)).trim());
			retVal = toTitleCase(sBldr.toString());
			retVal = retVal.replaceAll("  ", " ");
			/*
			*** one thousand three hundred sixteen
			*** first
			*** January
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public static void main(String[] args) {

		System.out.println("*** " + DateToWords.convertYear(1316));
		System.out.println("*** " + DateToWords.convertDays(1));
		System.out.println("*** " + DateToWords.convertMonths(1));

	}
}