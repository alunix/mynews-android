package com.qchu.mynews.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public class Converters {
  public static String stringOf(Date date, String dateFormat){
    if (date == null)
      return "";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    return simpleDateFormat.format(date);
  }
}
