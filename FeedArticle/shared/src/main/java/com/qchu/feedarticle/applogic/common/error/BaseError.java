package com.qchu.feedarticle.applogic.common.error;

/**
 * Created by louischu on 24/11/15.
 */
public class BaseError extends Error {


  public final Object object;

  public BaseError(String message, Object object) {
    super(message);
    this.object = object;
  }
}
