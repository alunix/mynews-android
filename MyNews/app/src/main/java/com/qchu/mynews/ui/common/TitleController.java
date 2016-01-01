package com.qchu.mynews.ui.common;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by Quoc Dung Chu on 18/11/15.
 */
@PerActivity
public class TitleController {

  private final AppCompatActivity activity;

  @Inject public TitleController(AppCompatActivity activity){
    this.activity = activity;
  }

  public void title(String title){
    this.activity.getSupportActionBar().setTitle(title);
  }
}
