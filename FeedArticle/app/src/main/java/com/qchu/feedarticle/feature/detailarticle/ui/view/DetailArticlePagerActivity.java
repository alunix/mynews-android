package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qchu.feedarticle.FeedArticleApplication;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.common.BaseActivity;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticlePresenter;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticleUserInterface;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.BindableArticle;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticlePagerActivityDataBinding;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.EntityTransformer;
import com.qchu.feedarticle.feature.detailarticle.ui.wireframe.DetailArticleWireframe;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteActionResult;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticlePagerActivity extends BaseActivity
	implements DetailArticleUserInterface {

	public static final String CURRENT_INDEX = "CURRENT_INDEX";
	public static final String ARTICLE_LIST = "ARTICLE_LIST";

	DetailArticlePresenter mDetailArticlePresenter;
	DetailArticlePagerActivityDataBinding mDetailArticlePagerActivityDataBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDetailArticlePagerActivityDataBinding =
			DataBindingUtil.setContentView(this, R.layout.detail_article_pager_activity);
		setSupportActionBar(mDetailArticlePagerActivityDataBinding.toolbar);

		//setup action bar
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(null);

		//setup viewpager
		mDetailArticlePagerActivityDataBinding.viewpager.addOnPageChangeListener(
			new ViewPager.OnPageChangeListener() {

				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

				}

				@Override
				public void onPageSelected(int position) {
					if(mDetailArticlePresenter != null) {
						mDetailArticlePresenter.onPageSelectedEvent(
							DetailArticlePagerActivity.this,
							mDetailArticlePagerActivityDataBinding.viewpager.getCurrentItem());
					}
				}

				@Override
				public void onPageScrollStateChanged(int state) {

				}
			});

		//setup presenter
		mDetailArticlePresenter = new DetailArticlePresenter(
			appLogicComponent().articleInteractor(),
			appLogicComponent().favoriteInteractor(),
			this,
			new DetailArticleWireframe(this),
			getIntent().getStringArrayListExtra(ARTICLE_LIST),
			getIntent().getIntExtra(CURRENT_INDEX,-1));
		mDetailArticlePresenter.onCreate();
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		mDetailArticlePresenter.onDestroy();
	}

	public void onFavoriteButtonClick(View view){
		mDetailArticlePresenter.onAddOrRemoveCurrentArticleInFavoriteEvent(this);
	}

	@Override
	public void bindArticles(DetailArticlePresenter detailArticlePresenter,
	                         List<Article> articleList) {
		List<BindableArticle> bindableArticleList = EntityTransformer.bindableArticleList(articleList);

		PagerAdapter pagerAdapter = mDetailArticlePagerActivityDataBinding.viewpager.getAdapter();
		if(pagerAdapter == null) {
			pagerAdapter = new DetailArticleViewPagerAdater(
				getSupportFragmentManager(), bindableArticleList);
			mDetailArticlePagerActivityDataBinding.viewpager.setAdapter(pagerAdapter);
		} else {
			((DetailArticleViewPagerAdater)pagerAdapter).reload(bindableArticleList);
		}
	}

	@Override
	public void selectIndex(DetailArticlePresenter detailArticlePresenter,
	                        int selectedIndex, boolean animated) {
		mDetailArticlePagerActivityDataBinding.viewpager.setCurrentItem(selectedIndex, animated);
	}

	@Override
	public void updateWithCurrentArticle(
		DetailArticlePresenter detailArticlePresenter, Article article) {

	}

	@Override
	public void updateFavoriteStateOfCurrentArticle(
		DetailArticlePresenter detailArticlePresenter, boolean isInFavorite) {

		mDetailArticlePagerActivityDataBinding.favoriteButton.setSelected(isInFavorite);
	}


	@Override
	public void showMessageToCompleteUpdateCurrentArticleInFavorite(
		DetailArticlePresenter detailArticlePresenter,
		FavoriteActionResult updateFavoriteActionResult) {

	}

}
