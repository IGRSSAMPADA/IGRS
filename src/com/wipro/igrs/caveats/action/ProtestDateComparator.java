package com.wipro.igrs.caveats.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.wipro.igrs.caveats.dto.CaveatsDTO;

public class ProtestDateComparator implements Comparator<CaveatsDTO>{

	@Override
	public int compare(CaveatsDTO dto1, CaveatsDTO dto2) {
		
		Date date1 = null;
			Date date2 = null;
			try {
				 date1=new SimpleDateFormat("dd-MM-yyyy").parse(dto1.getCreatedDate());
				 date2=new SimpleDateFormat("dd-MM-yyyy").parse(dto2.getCreatedDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return date2.compareTo(date1);
	}

}
