package com.qchu.feedarticle.feature.main.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.listarticle.ui.view.ListArticleFragment;
import com.qchu.feedarticle.feature.main.ui.view.databinding.MainActivityBinding;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuPresenter;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuUserInterface;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuWireframeInterface;
import com.qchu.feedarticle.feature.menu.ui.wireframe.MenuWireframe;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MainActivity extends AppCompatActivity implements MenuUserInterface {

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

		mMenuPresenter = MenuPresenter.create(this,
			new MenuWireframe(this, R.id.fragmentContainer));
	}

	protected void onDestroy(){
		super.onDestroy();
		mMenuPresenter.finish();
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
