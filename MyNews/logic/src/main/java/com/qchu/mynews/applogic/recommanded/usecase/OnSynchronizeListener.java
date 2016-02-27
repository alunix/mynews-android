package com.qchu.mynews.applogic.recommanded.usecase;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface OnSynchronizeListener {
  void onStart();
  void onNext();
  void onFinish();
}
