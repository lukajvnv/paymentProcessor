package com.project.bitcoinHandler.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

	
	public static String encode(Date date) {
		return formatter.format(date);
	}
	
	public static Date decode(String date)  {
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static String encodeT(Timestamp date) {
		return timestampFormatter.format(date);
	}
	
	public static Timestamp decodeT(String date)  {
		try {
			Date parsedDate = timestampFormatter.parse(date);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
