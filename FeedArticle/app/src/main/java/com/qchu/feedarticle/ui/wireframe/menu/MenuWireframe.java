package com.qchu.feedarticle.ui.wireframe.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.common.FragmentWireframe;
import com.qchu.feedarticle.ui.view.favoritelistarticle.FavoriteListArticleFragment;
import com.qchu.feedarticle.ui.view.home.HomeFragment;
import com.qchu.feedarticle.ui.view.refreshlistarticle.RefreshListArticleFragment;
import com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface;
import com.qchu.feedarticle.ui.view.search.SearchActivity;
import com.qchu.feedarticle.ui.view.search.SearchFragment;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MenuWireframe extends FragmentWireframe implements MenuWireframeInterface {

  public MenuWireframe(AppCompatActivity fromActivity, int fragmentContainerId) {
    super(fromActivity, fragmentContainerId);
  }

  @Override
  public void presentUserInterfaceForMenuItem(MenuItem menuItem) {
    switch (menuItem){
      case HOME:
        replaceFragment(new HomeFragment());
        break;

      case MY_ARTICLES:
        replaceFragment(new FavoriteListArticleFragment());
        break;

      case SEARCH:
        replaceFragment(new SearchFragment());
        break;

      default:
        break;
    }
  }
}
