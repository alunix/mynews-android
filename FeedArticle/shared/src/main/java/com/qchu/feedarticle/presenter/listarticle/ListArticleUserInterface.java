package com.qchu.feedarticle.presenter.listarticle;

import com.qchu.feedarticle.domain.article.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public interface ListArticleUserInterface {
	void bindArticles(List<Article> articleList);
}
