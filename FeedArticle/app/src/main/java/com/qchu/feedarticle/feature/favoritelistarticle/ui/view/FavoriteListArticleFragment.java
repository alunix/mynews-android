package com.qchu.feedarticle.feature.favoritelistarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.FeedArticleConfiguration;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter.FavoriteListArticlePresenter;
import com.qchu.feedarticle.feature.favoritelistarticle.ui.view.databinding.FavoriteListArticleFragmentDataBinding;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;
import com.qchu.feedarticle.feature.listarticle.ui.view.ListArticleRecycleViewAdapter;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.BindableArticle;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.EntityTransformer;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ListArticleFragmentDataBinding;
import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshListArticlePresenter;
import com.qchu.feedarticle.feature.refreshlistarticle.ui.wireframe.RefreshListArticleWireframe;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class FavoriteListArticleFragment extends Fragment
	implements ListArticleUserInterface {

	FavoriteListArticlePresenter mFavoriteListArticlePresenter;
	FavoriteListArticleFragmentDataBinding mFavoriteListArticleFragmentDataBinding;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		mFavoriteListArticleFragmentDataBinding =
			DataBindingUtil.inflate(inflater, R.layout.favorite_list_article_fragment, null, false);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mFavoriteListArticleFragmentDataBinding.recycleView.setLayoutManager(layoutManager);

		return mFavoriteListArticleFragmentDataBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);


	}


	@Override
	public void bindArticles(List<Article> articleList) {
		List<BindableArticle> bindableArticleList = EntityTransformer.boundArticleList(articleList);

		ListArticleRecycleViewAdapter listArticleRecycleViewAdapter =
			(ListArticleRecycleViewAdapter) mFavoriteListArticleFragmentDataBinding.recycleView.getAdapter();

		if(listArticleRecycleViewAdapter == null) {
			listArticleRecycleViewAdapter = new ListArticleRecycleViewAdapter(bindableArticleList,
				new ListArticleRecycleViewAdapter.OnItemClick() {
					@Override
					public void onItemClickAtPosition(View itemView, int position) {
						mFavoriteListArticlePresenter.onArticleItemClickEvent(FavoriteListArticleFragment.this, position);
					}
				});
			mFavoriteListArticleFragmentDataBinding.recycleView.setAdapter(listArticleRecycleViewAdapter);
		} else {
			listArticleRecycleViewAdapter.reload(bindableArticleList);
		}
	}
}
