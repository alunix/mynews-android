package com.qchu.feedarticle.feature.article.applogic.manager.repository.memory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleRepository;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteAction;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteActionResult;
import com.qchu.feedarticle.feature.favorite.applogic.interactor.FavoriteRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by quocdungchu on 28/09/15.
 */
public class MemoryArticleRepositoryManager implements ArticleRepository, FavoriteRepository {

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
		return Lists.transform(mFavoriteArticleIdList, new Function<String, Article>() {
			@Nullable @Override public Article apply(String articleId) {
				return mArticleMap.get(articleId);
			}
		});
	}

	@Override
	public List<String> getFavoriteArticleIds() {
		return mFavoriteArticleIdList;
	}

	@Override
	public boolean isFavoriteArticle(String articleId) {
		return mFavoriteArticleIdList.contains(articleId);
	}

	@Override
	public FavoriteActionResult updateArticleInFavorite(
		FavoriteAction updateFavoriteAction, String articleId) {

		if(updateFavoriteAction == FavoriteAction.REMOVE) {
			//Remove action
			if(mFavoriteArticleIdList.contains(articleId)) {
				return mFavoriteArticleIdList.remove(articleId) ?
					FavoriteActionResult.REMOVE_SUCCESSFUL:
					FavoriteActionResult.REMOVE_FAILED_REASON_OTHER;
			} else {
				return FavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST;
			}
		} else {
			//Add action
			if(!mFavoriteArticleIdList.contains(articleId)) {
				return mFavoriteArticleIdList.add(articleId) ?
					FavoriteActionResult.ADD_SUCCESSFUL:
					FavoriteActionResult.ADD_FAILED_REASON_OTHER;
			} else {
				return FavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY;
			}
		}
	}
}
