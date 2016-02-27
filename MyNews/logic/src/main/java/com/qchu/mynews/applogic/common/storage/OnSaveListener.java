package com.qchu.mynews.applogic.common.storage;

import java.util.List;

/**
 * Created by Quoc Dung Chu on 27/02/16.
 */
public interface OnSaveListener <Entity> {
  void onSave(List<Entity> entities);
}
