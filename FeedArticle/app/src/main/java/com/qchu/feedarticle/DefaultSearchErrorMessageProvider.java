package com.qchu.feedarticle;

import android.content.Context;

import com.qchu.feedarticle.applogic.domain.search.interactor.ErrorMessageProvider;
import com.qchu.feedarticle.applogic.domain.search.interactor.SearchError;

import javax.inject.Inject;

/**
 * Created by quocdungchu on 26/11/15.
 */
public class DefaultSearchErrorMessageProvider implements ErrorMessageProvider {
  private final Context context;

  @Inject
  public DefaultSearchErrorMessageProvider(Context context){
    this.context = context;
  }

  @Override
  public String message(SearchError searchError) {
    return null;
  }
}
