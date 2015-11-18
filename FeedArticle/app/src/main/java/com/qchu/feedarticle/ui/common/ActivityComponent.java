package com.qchu.feedarticle.ui.common;

import dagger.Component;
import dagger.Module;

/**
 * Created by louischu on 18/11/15.
 */
@PerActivity
@Component (modules = ActivityModule.class)
public interface ActivityComponent {
  TitleController titleController();
}
