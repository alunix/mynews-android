package com.qchu.feedarticle.feature.article.applogic.manager.repository.memory;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
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

	List<String> mFavoriteArticleIdList = new ArrayList<>();

	@Override
	public Article getArticleById(String articleId) {
		return mArticleMap.get(articleId);
	}

	@Override
	public List<Article> getArticleBySiteIds(List<String> siteIdList) {
		List<Article> articleList = new ArrayList<>();
		for(String siteId: siteIdList) {
			Site site = mSiteMap.get(siteId);
			articleList.addAll(site.articleList());
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
		mSiteMap.put(site.id(), site);
		for(Article article: site.articleList()) {
			mArticleMap.put(article.identifier(), article);
		}
	}

	@Override
	public List<Article> getFavoriteArticles() {
		List<Article> articleList = new ArrayList<>();
		for(String articleId: mFavoriteArticleIdList) {
			articleList.add(mArticleMap.get(articleId));
		}
		return articleList;
	}

	@Override
	public boolean isFavoriteArticle(String articleId) {
		return mFavoriteArticleIdList.contains(articleId);
	}

	@Override
	public ArticleInteractor.UpdateFavoriteActionResult updateArticleInFavorite(
		ArticleInteractor.UpdateFavoriteAction updateFavoriteAction, String articleId) {

		if(updateFavoriteAction == ArticleInteractor.UpdateFavoriteAction.REMOVE) {
			//Remove action
			if(mFavoriteArticleIdList.contains(articleId)) {
				return mFavoriteArticleIdList.remove(articleId) ?
					ArticleInteractor.UpdateFavoriteActionResult.REMOVE_SUCCESSFUL:
					ArticleInteractor.UpdateFavoriteActionResult.REMOVE_FAILED_REASON_OTHER;
			} else {
				return ArticleInteractor.UpdateFavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST;
			}
		} else {
			//Add action
			if(!mFavoriteArticleIdList.contains(articleId)) {
				return mFavoriteArticleIdList.add(articleId) ?
					ArticleInteractor.UpdateFavoriteActionResult.ADD_SUCCESSFUL:
					ArticleInteractor.UpdateFavoriteActionResult.ADD_FAILED_REASON_OTHER;
			} else {
				return ArticleInteractor.UpdateFavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY;
			}
		}
	}

	/*
	@Override
	public ArticleInteractor.UpdateFavoriteActionResult addFavoriteArticle(String articleId) {
		if(!mFavoriteArticleIdList.contains(articleId)) {
			return mFavoriteArticleIdList.add(articleId) ?
				ArticleInteractor.UpdateFavoriteActionResult.ADD_SUCCESSFUL:
				ArticleInteractor.UpdateFavoriteActionResult.ADD_FAILED_REASON_OTHER;
		} else {
			return ArticleInteractor.UpdateFavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY;
		}
	}

	@Override
	public ArticleInteractor.UpdateFavoriteActionResult removeFavoriteArticle(String articleId) {
		if(mFavoriteArticleIdList.contains(articleId)) {
			return mFavoriteArticleIdList.remove(articleId) ?
				ArticleInteractor.UpdateFavoriteActionResult.REMOVE_SUCCESSFUL:
				ArticleInteractor.UpdateFavoriteActionResult.REMOVE_FAILED_REASON_OTHER;
		} else {
			return ArticleInteractor.UpdateFavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST;
		}
	}
	*/
}
