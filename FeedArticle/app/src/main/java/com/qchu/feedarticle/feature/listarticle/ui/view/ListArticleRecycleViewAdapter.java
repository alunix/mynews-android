package com.qchu.feedarticle.feature.listarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ArticleItemDataBinding;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.BoundArticle;

import java.util.List;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class ListArticleRecycleViewAdapter extends RecyclerView.Adapter {

	List<BoundArticle> mBoundArticleList;
	OnItemClick mOnItemClick;

	public ListArticleRecycleViewAdapter(List<BoundArticle> boundArticleList,
	                                     OnItemClick onItemClick) {
		mBoundArticleList = boundArticleList;
		mOnItemClick = onItemClick;
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
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
		BoundArticle boundArticle = mBoundArticleList.get(position);

		ArticleItemDataBinding articleItemDataBinding =
			((ArticleViewHolder)viewHolder).mArticleItemDataBinding;

		articleItemDataBinding.setArticle(boundArticle);
		articleItemDataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mOnItemClick.onItemClickAtPosition(view, position);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mBoundArticleList.size();
	}

	static class ArticleViewHolder extends RecyclerView.ViewHolder {

		ArticleItemDataBinding mArticleItemDataBinding;

		ArticleViewHolder(ArticleItemDataBinding articleItemDataBinding) {
			super(articleItemDataBinding.getRoot());
			mArticleItemDataBinding = articleItemDataBinding;
		}
	}

	public interface OnItemClick {
		void onItemClickAtPosition(View itemView, int position);
	}
}
