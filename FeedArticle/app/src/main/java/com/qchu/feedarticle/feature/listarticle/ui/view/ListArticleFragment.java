package com.qchu.feedarticle.feature.listarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.FeedArticleConfiguration;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.BoundArticle;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.EntityTransformer;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ListArticleFragmentDataBinding;
import com.qchu.feedarticle.feature.listarticle.ui.wireframe.ListArticleWireframe;

import java.util.List;

/**
 * Created by quocdungchu on 22/09/15.
 */
public class ListArticleFragment extends Fragment implements ListArticleUserInterface {

	ListArticlePresenter mListArticlePresenter;
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

		mListArticlePresenter = ListArticlePresenter.create(
			FeedArticleConfiguration.get().getArticleInteractor(),
			this,
			new ListArticleWireframe(getActivity()));

		mListArticleFragmentDataBinding.swipeRefreshLayout.setOnRefreshListener(
			new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					mListArticlePresenter.onSwipeRefreshEvent(ListArticleFragment.this);
				}
			});

	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		mListArticlePresenter.finish();
	}

	@Override
	public void bindArticles(List<Article> articleList) {
		List<BoundArticle> boundArticleList = EntityTransformer.boundArticleList(articleList);

		ListArticleRecycleViewAdapter listArticleRecycleViewAdapter =
			(ListArticleRecycleViewAdapter) mListArticleFragmentDataBinding.recycleView.getAdapter();

		if(listArticleRecycleViewAdapter == null) {
			listArticleRecycleViewAdapter = new ListArticleRecycleViewAdapter(boundArticleList,
				new ListArticleRecycleViewAdapter.OnItemClick() {
				@Override
				public void onItemClickAtPosition(View itemView, int position) {
					mListArticlePresenter.onArticleItemClickEvent(ListArticleFragment.this, position);
				}
			});
			mListArticleFragmentDataBinding.recycleView.setAdapter(listArticleRecycleViewAdapter);
		} else {
			listArticleRecycleViewAdapter.reload(boundArticleList);
		}
	}

	@Override
	public void beginSwipeRefreshingLayout(ListArticlePresenter listArticlePresenter) {
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
	public void endSwipeRefreshingLayout(ListArticlePresenter listArticlePresenter) {
		mListArticleFragmentDataBinding.swipeRefreshLayout.setRefreshing(false);
	}
}
