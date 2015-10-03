package com.qchu.feedarticle.feature.article.applogic.manager.repository.memory;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.interactor.RepositoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by quocdungchu on 28/09/15.
 */
public class MemoryRepositoryManager implements RepositoryAdapter {

	Map<String, Site> mSiteMap = new HashMap<>();
	Map<String, Article> mArticleMap = new HashMap<>();

	@Override
	public Article getArticleById(String articleId) {
		return mArticleMap.get(articleId);
	}

	@Override
	public List<Article> getArticleBySiteIds(List<String> siteIdList) {
		List<Article> articleList = new ArrayList<>();
		for(String siteId: siteIdList) {
			Site site = mSiteMap.get(siteId);
			articleList.addAll(site.getArticleList());
		}
		return articleList;
	}

	@Override
	public List<Article> getArticleByArticleIds(List<String> articleIdList) {
		List<Article> articleList = new ArrayList<>();
		for(String articleId: articleIdList) {
			articleList.add(mArticleMap.get(articleId));
		}
		return articleList;
	}

	@Override
	public void updateSiteList(List<Site> siteList) {
		for(Site site: siteList) {
			updateSite(site);
		}
	}

	@Override
	public void updateSite(Site site) {
		mSiteMap.put(site.getId(), site);
		for(Article article: site.getArticleList()) {
			mArticleMap.put(article.getId(), article);
		}
	}
}