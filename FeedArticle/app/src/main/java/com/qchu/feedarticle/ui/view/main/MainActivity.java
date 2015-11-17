package com.qchu.feedarticle.ui.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.common.BaseActivity;
import com.qchu.feedarticle.ui.presenter.menu.MenuPresenter;
import com.qchu.feedarticle.ui.presenter.menu.MenuUserInterface;
import com.qchu.feedarticle.ui.presenter.menu.MenuWireframeInterface;
import com.qchu.feedarticle.ui.wireframe.menu.MenuWireframe;
import com.qchu.feedarticle.view.main.databinding.MainActivityBinding;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MainActivity extends BaseActivity implements MenuUserInterface {

	MenuPresenter mMenuPresenter;

	MainActivityBinding mMainActivityBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
		setSupportActionBar(mMainActivityBinding.toolbar);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		actionBar.setDisplayHomeAsUpEnabled(true);

		setupDrawerContent(mMainActivityBinding.navView);

		mMenuPresenter = new MenuPresenter(this,
			new MenuWireframe(this, R.id.fragmentContainer));
		mMenuPresenter.onCreate();
	}

	protected void onDestroy(){
		super.onDestroy();
		mMenuPresenter.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				mMainActivityBinding.drawerLayout.openDrawer(GravityCompat.START);
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
					mMainActivityBinding.drawerLayout.closeDrawers();
					switch (menuItem.getItemId()) {
						case R.id.nav_home:
							mMenuPresenter.onClickMenuItem(MenuWireframeInterface.MenuItem.HOME);
							break;
					}
					return true;
				}
			});
	}
}