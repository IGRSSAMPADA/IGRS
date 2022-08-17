package com.wipro.igrs.report.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class IGRSUtil {
    public IGRSUtil() {
    }
    public static java.util.Date getUtilDateFromString(String strDate, String format) {
                    java.util.Date newDate;
                    if(strDate!=null){
                    String[] result = strDate.split("\\/");
                    String tokens = "";
                    for (int x=0; x<result.length; x++)
                    {
                            tokens = result[x];
                    }
                    if(tokens.length()<=3){
                            return null;
                            }

                    }

                    ParsePosition pos = new ParsePosition(0);
                    if (strDate == null || strDate == "" || format == null) {
                            newDate = null;
                    } else  {
                            SimpleDateFormat formatter = new SimpleDateFormat(format);
                            formatter.setLenient(false);
                            newDate = formatter.parse(strDate, pos);
                    }
                    return newDate;
            }

}
