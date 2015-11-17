package com.qchu.feedarticle.applogic.repository.memory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.interactor.ArticleStorage;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteAction;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteActionResult;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by quocdungchu on 28/09/15.
 */
public class MemoryArticleStorage implements ArticleStorage, FavoriteRepository {

	Map<String, Channel> siteMap = new HashMap<>();
	Map<String, Article> articleMap = new HashMap<>();

	List<String> favoriteArticleIdList = new ArrayList<>();

	@Override
	public Article articleByArticleId(String articleId) {
		return articleMap.get(articleId);
	}

	@Override
	public List<Article> articlesByChannelIds(List<String> channelIds) {
		List<Article> articleList = new ArrayList<>();
		for(String siteId: channelIds) {
			Channel channel = siteMap.get(siteId);
			articleList.addAll(channel.articleList());
		}
		return articleList;
	}

	@Override
	public List<Article> articlesByArticleIds(List<String> articleIds) {
		List<Article> articleList = new ArrayList<>();
		for(String articleId: articleIds) {
			articleList.add(articleMap.get(articleId));
		}
		return articleList;
	}

	@Override
	public void updateChannels(List<Channel> channels) {
		for(Channel channel : channels) {
			updateChannel(channel);
		}
	}

	@Override
	public void updateChannel(Channel channel) {
		siteMap.put(channel.id(), channel);
		for(Article article: channel.articleList()) {
			articleMap.put(article.identifier(), article);
		}
	}

	@Override
	public List<Article> getFavoriteArticles() {
		return Lists.transform(favoriteArticleIdList, new Function<String, Article>() {
			@Nullable @Override public Article apply(String articleId) {
				return articleMap.get(articleId);
			}
		});
	}

	@Override
	public List<String> getFavoriteArticleIds() {
		return favoriteArticleIdList;
	}

	@Override
	public boolean isFavoriteArticle(String articleId) {
		return favoriteArticleIdList.contains(articleId);
	}

	@Override
	public FavoriteActionResult updateArticleInFavorite(
		FavoriteAction updateFavoriteAction, String articleId) {

		if(updateFavoriteAction == FavoriteAction.REMOVE) {
			//Remove action
			if(favoriteArticleIdList.contains(articleId)) {
				return favoriteArticleIdList.remove(articleId) ?
					FavoriteActionResult.REMOVE_SUCCESSFUL:
					FavoriteActionResult.REMOVE_FAILED_REASON_OTHER;
			} else {
				return FavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST;
			}
		} else {
			//Add action
			if(!favoriteArticleIdList.contains(articleId)) {
				return favoriteArticleIdList.add(articleId) ?
					FavoriteActionResult.ADD_SUCCESSFUL:
					FavoriteActionResult.ADD_FAILED_REASON_OTHER;
			} else {
				return FavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY;
			}
		}
	}
}
