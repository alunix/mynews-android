package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface RepositoryAdapter {
	Article getArticleById(String articleId);
	List<Article> getArticleBySiteIdList(List<String> siteIdList);
	void updateSiteList(List<Site> siteList);
}
