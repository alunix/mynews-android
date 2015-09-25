package com.qchu.feedarticle.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by quocdungchu on 25/09/15.
 */
public class DateDeserializer {

	static final String[] DATE_FORMATS = new String[]{
		"yyyy-MM-dd'T'HH:mm:ssZ",
		"yyyy-MM-dd'T'HH:mm:ss",
		"yyyy-MM-dd",
		"EEE MMM dd HH:mm:ss z yyyy",
		"EEE, dd MMM yyyy HH:mm:ss zzz", //Rss xml
		"HH:mm:ss",
		"MM/dd/yyyy HH:mm:ss aaa",
		"yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
		"yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
		"yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
		"MMM d',' yyyy H:mm:ss a"
	};

	List<SimpleDateFormat> mReusedDateFormat = new ArrayList<>();

	static DateDeserializer sDateDeserializer;

	public static DateDeserializer get(){
		if(sDateDeserializer == null) {
			sDateDeserializer = new DateDeserializer();
		}
		return sDateDeserializer;
	}

	DateDeserializer(){}

	public Date deserialize(String dateString) throws Exception {

		for(SimpleDateFormat simpleDateFormat: mReusedDateFormat) {
			try {
				return simpleDateFormat.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (String format : DATE_FORMATS) {
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.UK);
				//simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				Date date = simpleDateFormat.parse(dateString);
				if(date != null) {
					mReusedDateFormat.add(simpleDateFormat);
				}
				return date;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		throw new Exception("Unparseable date: \"" + dateString
			+ "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS));
	}
}
