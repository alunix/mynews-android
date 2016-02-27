package com.qchu.mynews.common.util;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public class Lists {
  public static <T> boolean isNullOrEmpty(List<T> list){
    return list == null || list.isEmpty();
  }
}
