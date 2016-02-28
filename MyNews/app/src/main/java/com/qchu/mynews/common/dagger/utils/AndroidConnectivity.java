package com.qchu.mynews.common.dagger.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qchu.common.utils.Connectivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Quoc Dung Chu on 01/01/16.
 */
@Singleton
public class AndroidConnectivity implements Connectivity {

  private final Context context;

  @Inject
  public AndroidConnectivity(Context context) {
    this.context = context;
  }

  @Override public boolean isConnected() {
    NetworkInfo networkInfo = ((ConnectivityManager)
      this.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isConnected();
  }
}
