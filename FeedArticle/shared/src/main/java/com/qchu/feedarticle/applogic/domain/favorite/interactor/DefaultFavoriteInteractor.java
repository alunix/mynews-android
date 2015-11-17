package com.qchu.feedarticle.applogic.domain.favorite.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 17/10/15.
 */
@Singleton
public class DefaultFavoriteInteractor implements FavoriteInteractor {

	final FavoriteRepository favoriteRepository;

	@Inject
	public DefaultFavoriteInteractor(FavoriteRepository favoriteRepository){
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
	public com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteActionResult updateArticleInFavoriteRepository(
		FavoriteAction updateFavoriteAction, String articleId) {
		return this.favoriteRepository.updateArticleInFavorite(updateFavoriteAction, articleId);
	}
}
