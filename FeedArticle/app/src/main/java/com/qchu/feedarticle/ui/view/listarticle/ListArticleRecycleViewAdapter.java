package com.qchu.feedarticle.ui.view.listarticle;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ArticleItemDataBinding;
import com.qchu.feedarticle.ui.view.listarticle.databinding.BindableArticle;

import java.util.List;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class ListArticleRecycleViewAdapter extends RecyclerView.Adapter {

	List<BindableArticle> mBindableArticleList;
	OnItemClick mOnItemClick;

	public ListArticleRecycleViewAdapter(List<BindableArticle> bindableArticleList,
	                                     OnItemClick onItemClick) {
		mBindableArticleList = bindableArticleList;
		mOnItemClick = onItemClick;
	}

	public void reload(List<BindableArticle> bindableArticleList) {
		mBindableArticleList = bindableArticleList;
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
		BindableArticle bindableArticle = mBindableArticleList.get(position);

		ArticleItemDataBinding articleItemDataBinding =
			((ArticleViewHolder)viewHolder).mArticleItemDataBinding;

		articleItemDataBinding.setArticle(bindableArticle);
		articleItemDataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mOnItemClick.onItemClickAtPosition(view, position);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mBindableArticleList.size();
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
