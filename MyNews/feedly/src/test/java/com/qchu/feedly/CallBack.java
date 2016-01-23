package com.qchu.feedly;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
public interface CallBack<Result> {
  void onComplete();
  void onNext(Result result);
  void onError(Throwable error);
}
