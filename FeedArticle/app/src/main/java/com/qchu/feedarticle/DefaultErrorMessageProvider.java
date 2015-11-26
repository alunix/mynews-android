package com.qchu.feedarticle;

import android.content.Context;

import com.qchu.once.shared.network.interactor.ErrorMessageProvider;

import javax.inject.Inject;

/**
 * Created by quocdungchu on 26/11/15.
 */
public class DefaultErrorMessageProvider implements ErrorMessageProvider {

  private final Context context;

  @Inject
  public DefaultErrorMessageProvider(Context context){
    this.context = context;
  }

  @Override
  public String messageForNoInternetConnection() {
    return null;
  }

  @Override
  public String message(int statusCode, String responseErrorMessage) {
    return null;
  }
}
