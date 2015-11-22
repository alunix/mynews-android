package com.qchu.feedarticle.ui.view.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.ui.common.BaseActivity;
import com.qchu.feedarticle.ui.presenter.menu.MenuUserInterface;
import com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface;
import com.qchu.feedarticle.view.main.databinding.MainActivityBinding;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MainActivity extends BaseActivity implements MenuUserInterface {

  private static final String TAG = "MainActivity";
  private MainActivityBinding dataBinding;

  private MainComponent mainComponent;

  public MainComponent mainComponent(){
    if(mainComponent == null){
      mainComponent = DaggerMainComponent.builder()
        .mainModule(new MainModule(this))
        .build();
    }
    return mainComponent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    dataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    setSupportActionBar(dataBinding.toolbar);

    final ActionBar actionBar = getSupportActionBar();
    actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    actionBar.setDisplayHomeAsUpEnabled(true);

    setupDrawerContent(dataBinding.navView);

    mainComponent().menuPresenter().onCreate();
  }

  protected void onDestroy(){
    super.onDestroy();
    mainComponent().menuPresenter().onDestroy();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        dataBinding.drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
      new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
          menuItem.setChecked(true);
          dataBinding.drawerLayout.closeDrawers();
          switch (menuItem.getItemId()) {

            case R.id.nav_home:
              mainComponent().menuPresenter()
                .onClickMenuItem(MenuWireframeInterface.MenuItem.HOME);
              break;

            case R.id.nav_favorite:
              mainComponent().menuPresenter()
                .onClickMenuItem(MenuWireframeInterface.MenuItem.MY_ARTICLES);
              break;

            case R.id.nav_search:
              mainComponent().menuPresenter()
                .onClickMenuItem(MenuWireframeInterface.MenuItem.SEARCH);
              break;

            case R.id.nav_info:
              mainComponent().menuPresenter()
                .onClickMenuItem(MenuWireframeInterface.MenuItem.INFORMATIONS);
              break;

            default:
              break;
          }
          return true;
        }
      });
  }

  @Override
  protected void onNewIntent(Intent newIntent){
    Log.d(TAG,"on new intent: action " + newIntent.getAction());
    activityComponent().intentController().handleNewIntent(newIntent);
  }
}
