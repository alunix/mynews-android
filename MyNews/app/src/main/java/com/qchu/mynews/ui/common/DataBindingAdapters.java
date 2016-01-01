package com.qchu.mynews.ui.common;

import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import android.widget.TextView;

import com.qchu.mynews.common.Converters;

import java.util.Date;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
public class DataBindingAdapters {

  @BindingAdapter("app:tintColor")
  public static void setTintColor (ImageView imageView, int color){
    imageView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
  }

  @BindingAdapter({"app:date", "app:dateFormat"})
  public static void setDateString(TextView textView, Date date, String dateFormat){
    textView.setText(Converters.stringOf(date, dateFormat));
  }
}
