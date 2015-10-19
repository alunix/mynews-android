package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface ArticleRepository {
	Article getArticleById(String articleId);
	List<Article> getArticleBySiteIds(List<String> siteIdList);
	List<Article> getArticleByArticleIds(List<String> articleIdList);
	void updateSiteList(List<Site> siteList);
	void updateSite(Site site);
}
