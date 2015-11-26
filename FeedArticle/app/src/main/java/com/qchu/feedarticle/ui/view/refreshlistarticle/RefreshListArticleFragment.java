package com.qchu.feedarticle.ui.view.refreshlistarticle;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.ui.common.BaseFragment;
import com.qchu.feedarticle.ui.view.listarticle.ListArticleRecycleViewAdapter;
import com.qchu.feedarticle.ui.presenter.refreshlistarticle.RefreshListArticlePresenter;
import com.qchu.feedarticle.ui.presenter.refreshlistarticle.RefreshListArticleUserInterface;
import com.qchu.feedarticle.ui.view.listarticle.databinding.BindableArticle;
import com.qchu.feedarticle.ui.view.listarticle.databinding.EntityTransformer;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ListArticleFragmentDataBinding;
import com.qchu.feedarticle.ui.wireframe.refreshlistarticle.RefreshListArticleWireframe;

import java.util.List;

/**
 * Created by quocdungchu on 22/09/15.
 */
public class RefreshListArticleFragment extends BaseFragment
	implements RefreshListArticleUserInterface {

	RefreshListArticlePresenter mRefreshListArticlePresenter;
	ListArticleFragmentDataBinding mListArticleFragmentDataBinding;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                            Bundle savedInstanceState){
		mListArticleFragmentDataBinding =
			DataBindingUtil.inflate(inflater, R.layout.list_article_fragment, null, false);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mListArticleFragmentDataBinding.recycleView.setLayoutManager(layoutManager);

		return mListArticleFragmentDataBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

		//setup presenter
		mRefreshListArticlePresenter = new RefreshListArticlePresenter(
			appComponent().articleInteractor(),
			this,
			new RefreshListArticleWireframe(getActivity()));
		mRefreshListArticlePresenter.onCreate();

		mListArticleFragmentDataBinding.swipeRefreshLayout.setOnRefreshListener(
			new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					mRefreshListArticlePresenter.onSwipeRefreshEvent(RefreshListArticleFragment.this);
				}
			});
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		mRefreshListArticlePresenter.onDestroy();
	}

	@Override
	public void bindArticles(List<Article> articleList) {
		List<BindableArticle> bindableArticleList = EntityTransformer.boundArticleList(articleList);

		ListArticleRecycleViewAdapter listArticleRecycleViewAdapter =
			(ListArticleRecycleViewAdapter) mListArticleFragmentDataBinding.recycleView.getAdapter();

		if(listArticleRecycleViewAdapter == null) {
			listArticleRecycleViewAdapter = new ListArticleRecycleViewAdapter(bindableArticleList,
				new ListArticleRecycleViewAdapter.OnItemClick() {
				@Override
				public void onItemClickAtPosition(View itemView, int position) {
					mRefreshListArticlePresenter.onArticleItemClickEvent(RefreshListArticleFragment.this, position);
				}
			});
			mListArticleFragmentDataBinding.recycleView.setAdapter(listArticleRecycleViewAdapter);
		} else {
			listArticleRecycleViewAdapter.reload(bindableArticleList);
		}
	}

	@Override
	public void beginSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter) {
		if(!mListArticleFragmentDataBinding.swipeRefreshLayout.isRefreshing()) {
			new Handler().post(new Runnable() {
				@Override
				public void run() {
					mListArticleFragmentDataBinding.swipeRefreshLayout.setRefreshing(true);
				}
			});
		}
	}

	@Override
	public void endSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter) {
		mListArticleFragmentDataBinding.swipeRefreshLayout.setRefreshing(false);
	}
}
