package com.qchu.feedarticle.ui.view.main;

import com.qchu.feedarticle.ui.common.PerActivity;
import com.qchu.feedarticle.ui.presenter.menu.MenuPresenter;

import dagger.Component;

/**
 * Created by louischu on 18/11/15.
 */
@PerActivity @Component (modules = MainModule.class)
public interface MainComponent {
  MenuPresenter menuPresenter();
}
