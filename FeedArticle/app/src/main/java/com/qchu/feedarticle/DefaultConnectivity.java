package com.qchu.feedarticle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qchu.once.shared.connectivity.Connectivity;

import javax.inject.Inject;

/**
 * Created by quocdungchu on 26/11/15.
 */

public class DefaultConnectivity implements Connectivity {
  private final Context context;

  @Inject
  public DefaultConnectivity(Context context){
    this.context = context;
  }

  @Override
  public boolean connectedInternet() {
    NetworkInfo networkInfo = ((ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE))
      .getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isConnected();
  }
}
