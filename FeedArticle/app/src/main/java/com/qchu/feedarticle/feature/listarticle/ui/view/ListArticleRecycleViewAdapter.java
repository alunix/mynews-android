package com.qchu.feedarticle.feature.listarticle.ui.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ArticleItemDataBinding;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.BoundArticle;

import java.util.List;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class ListArticleRecycleViewAdapter extends RecyclerView.Adapter {

	List<BoundArticle> mBoundArticleList;

	public ListArticleRecycleViewAdapter(List<BoundArticle> boundArticleList) {
		mBoundArticleList = boundArticleList;
	}

	public void reload(List<BoundArticle> boundArticleList) {
		mBoundArticleList = boundArticleList;
		notifyDataSetChanged();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

		ArticleItemDataBinding articleItemDataBinding =
			DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
				R.layout.list_article_item, viewGroup, true);

		return new ArticleViewHolder(articleItemDataBinding);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
		BoundArticle boundArticle = mBoundArticleList.get(i);
		((ArticleViewHolder)viewHolder).bind(boundArticle);
	}

	@Override
	public int getItemCount() {
		return mBoundArticleList.size();
	}

	static class ArticleViewHolder extends RecyclerView.ViewHolder {

		ArticleItemDataBinding mArticleItemDataBinding;

		public ArticleViewHolder(ArticleItemDataBinding articleItemDataBinding) {
			super(articleItemDataBinding.getRoot());
			mArticleItemDataBinding = articleItemDataBinding;
		}

		void bind(BoundArticle boundArticle) {
			mArticleItemDataBinding.setArticle(boundArticle);
		}


	}
}
