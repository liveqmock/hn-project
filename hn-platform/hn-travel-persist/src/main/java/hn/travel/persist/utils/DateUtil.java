package hn.travel.persist.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
* GoEast-Commons
* @author Sean Lai
* @version 1.0
* Copyright (c) 2005.
*/

public class DateUtil {

    public static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_FORMAT_PATTERN2 = "yy/MM/dd";
    public static final String SHORT_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    
    public static final SimpleDateFormat DEAFULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static final SimpleDateFormat SHORT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    public static final SimpleDateFormat SHORT_FORMAT2 = new SimpleDateFormat("yy/MM/dd");
    
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    
    public static final SimpleDateFormat FOLDER_FORMAT = new SimpleDateFormat("yyyyMMdd");
    
    public static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy年");
    
    public static final SimpleDateFormat FILENAME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    private DateUtil() {
    }
    
    public static void main(String[] args) throws ParseException {
		String y = "2012-9-12";
		Date d = SHORT_FORMAT.parse(y);
		String s = YEAR_FORMAT.format(d);
		System.out.println(s);
	}
}
