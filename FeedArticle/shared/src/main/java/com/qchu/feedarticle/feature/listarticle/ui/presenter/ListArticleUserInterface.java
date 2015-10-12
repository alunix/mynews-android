package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public interface ListArticleUserInterface {
	void bindArticles(List<Article> articleList);
}
