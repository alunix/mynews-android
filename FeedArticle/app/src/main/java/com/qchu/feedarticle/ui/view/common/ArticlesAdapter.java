package com.qchu.feedarticle.ui.view.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.ui.common.ViewHolder;
import com.qchu.feedarticle.ui.view.common.articles.databinding.ArticleItemDataBinding;
import com.qchu.feedarticle.ui.view.search.databinding.ResultItemDataBinding;

import java.util.List;

/**
 * Created by quocdungchu on 06/12/15.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ViewHolder<ArticleItemDataBinding>> {

  private final List<Article> articles;
  private final OnItemSelect onItemSelect;

  public ArticlesAdapter(List<Article> articles, OnItemSelect onItemSelect) {
    this.articles = articles;
    this.onItemSelect = onItemSelect;
  }

  @Override
  public ViewHolder<ArticleItemDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ArticleItemDataBinding dataBinding = ArticleItemDataBinding.inflate(
      LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder<>(dataBinding);
  }

  @Override
  public void onBindViewHolder(ViewHolder<ArticleItemDataBinding> holder, final int position) {
    final Article article = articles.get(position);
    holder.dataBinding.setTitle(article.title());
    holder.dataBinding.setImageUrl(article.mainImage().url());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(onItemSelect != null){
          onItemSelect.onSelect(article, position);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }

  public interface OnItemSelect {
    void onSelect(Article article, int position);
  }
}
