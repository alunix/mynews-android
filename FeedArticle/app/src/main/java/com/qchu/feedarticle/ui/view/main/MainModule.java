package com.qchu.feedarticle.ui.view.main;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.ui.common.PerActivity;
import com.qchu.feedarticle.ui.presenter.menu.MenuUserInterface;
import com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface;
import com.qchu.feedarticle.ui.wireframe.menu.MenuWireframe;

import dagger.Module;
import dagger.Provides;

/**
 * Created by louischu on 18/11/15.
 */
@PerActivity @Module
public class MainModule {


  private final MainActivity mainActivity;

  public MainModule(MainActivity mainActivity){
    this.mainActivity = mainActivity;
  }

  @Provides @PerActivity MenuUserInterface provideMenuUserInterface(){
    return this.mainActivity;
  }

  @Provides @PerActivity MenuWireframeInterface provideMenuWireframeInterface(){
    return new MenuWireframe(this.mainActivity, R.id.fragmentContainer);
  }


}
