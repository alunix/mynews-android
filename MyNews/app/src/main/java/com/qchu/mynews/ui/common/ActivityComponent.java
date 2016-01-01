package com.qchu.mynews.ui.common;

import dagger.Component;

/**
 * Created by Quoc Dung Chu on 18/11/15.
 */
@PerActivity
@Component (modules = ActivityModule.class)
public interface ActivityComponent {
  TitleController titleController();
  IntentController intentController();
}
