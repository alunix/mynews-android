package com.qchu.feedarticle.ui.common;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by louischu on 22/11/15.
 */
@PerActivity
public class IntentController {

  private final List<Handler> handlers;

  @Inject public IntentController() {
    this.handlers = new ArrayList<>();
  }

  public void handleNewIntent(Intent newIntent){
    for(Handler handler: handlers){
      handler.onHandleNewIntent(newIntent);
    }
  }

  public void addHandler(Handler handler){
    handlers.add(handler);
  }

  public interface Handler {
    void onHandleNewIntent(Intent newIntent);
  }
}
