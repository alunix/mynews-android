package com.qchu.mynews.ui.common;

import android.text.Html;
import android.text.Spanned;

import com.google.common.base.Strings;

/**
 * Created by Quoc Dung Chu on 06/02/16.
 */
public class Html2 {
  public static Spanned fromHtml(String source){
    return Strings.isNullOrEmpty(source) ?
      null:
      Html.fromHtml(source);
  }
}
