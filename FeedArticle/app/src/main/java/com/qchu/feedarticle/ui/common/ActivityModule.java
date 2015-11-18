package com.qchu.feedarticle.ui.common;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 18/11/15.
 */
@Module
public class ActivityModule {
  private final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity AppCompatActivity provideActivity() {
    return this.activity;
  }
}
