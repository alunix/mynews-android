package com.qchu.mynews.applogic.common;

import com.google.common.base.Predicate;

/**
 * Created by Quoc Dung Chu on 28/02/16.
 */
public enum Priority {
  WHENEVER(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3),
  IMMEDIATE(4);

  private final int value;

  Priority(int value){
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
