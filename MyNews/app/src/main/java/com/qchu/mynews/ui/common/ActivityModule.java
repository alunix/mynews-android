package com.qchu.mynews.ui.common;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Quoc Dung Chu on 18/11/15.
 */
@Module
public class ActivityModule {
  private final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity
  AppCompatActivity provideActivity() {
    return this.activity;
  }
}
