package com.qchu.feedly;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class Converter {
  public static String rssUrlFrom(String feedId){
    return feedId != null ? feedId.substring(5): null;
  }

  public static String feedIdFrom(String rssUrl){
    return "feed/"+rssUrl;
  }
}
