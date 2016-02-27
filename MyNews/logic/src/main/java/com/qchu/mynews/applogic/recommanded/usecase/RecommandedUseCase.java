package com.qchu.mynews.applogic.recommanded.usecase;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface RecommandedUseCase {
  void synchronize();
  void synchronize(OnSynchronizeListener onSynchronizeListener);
}
