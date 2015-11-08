package com.qchu.feedarticle.domain.favorite.interactor;

import com.qchu.feedarticle.domain.article.entity.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 17/10/15.
 */
@Singleton
public class DefaultFavoriteInteractor implements FavoriteInteractor {

	final com.qchu.feedarticle.domain.favorite.interactor.FavoriteRepository favoriteRepository;

	@Inject
	public DefaultFavoriteInteractor(com.qchu.feedarticle.domain.favorite.interactor.FavoriteRepository favoriteRepository){
		this.favoriteRepository = favoriteRepository;
	}

	@Override
	public List<Article> getFavoriteArticlesInRepository() {
		return this.favoriteRepository.getFavoriteArticles();
	}

	@Override
	public List<String> getFavoriteArticleIdsInRepository(){
		return this.favoriteRepository.getFavoriteArticleIds();
	}

	@Override
	public boolean isFavoriteArticleInRepository(String articleId) {
		return this.favoriteRepository.isFavoriteArticle(articleId);
	}

	@Override
	public FavoriteActionResult updateArticleInFavoriteRepository(
		FavoriteAction updateFavoriteAction, String articleId) {
		return this.favoriteRepository.updateArticleInFavorite(updateFavoriteAction, articleId);
	}
}
