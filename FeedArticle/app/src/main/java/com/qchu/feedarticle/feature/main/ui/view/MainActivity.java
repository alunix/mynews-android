package com.qchu.feedarticle.feature.main.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.main.ui.view.databinding.MainActivityBinding;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuPresenter;
import com.qchu.feedarticle.feature.menu.ui.presenter.MenuUserInterface;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class MainActivity extends AppCompatActivity implements MenuUserInterface {

	ListArticlePresenter mListArticlePresenter;
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
		/*
		mTutorialActivityBinding = DataBindingUtil.setContentView(
			this, R.layout.tutorial_activity);

		setSupportActionBar(mTutorialActivityBinding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		//setup presenter
		mTutorialPresenter = TutorialPresenter.create(
			InteractorFactory.createTutorialInteractor(),
			this,
			new TutorialWireframe(this));
			*/
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
}
