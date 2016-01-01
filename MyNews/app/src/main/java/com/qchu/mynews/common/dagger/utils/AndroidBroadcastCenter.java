package com.qchu.mynews.common.dagger.utils;

import android.content.Context;
import android.content.Intent;

import com.qchu.common.BroadcastCenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class AndroidBroadcastCenter implements BroadcastCenter {

  private final Context context;

  @Inject
  public AndroidBroadcastCenter(Context context) {
    this.context = context;
  }

  @Override
  public void send(String action) {
    context.sendBroadcast(new Intent(action));
  }
}
