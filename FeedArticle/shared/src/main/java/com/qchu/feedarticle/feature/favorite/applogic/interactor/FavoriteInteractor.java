package com.qchu.feedarticle.feature.favorite.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 17/10/15.
 */
@Singleton
public class FavoriteInteractor {

	final FavoriteRepository favoriteRepository;

	public static FavoriteInteractor create(FavoriteRepository favoriteRepository) {
		FavoriteInteractor favoriteInteractor = new FavoriteInteractor(favoriteRepository);
		return favoriteInteractor;
	}

	@Inject
	FavoriteInteractor(FavoriteRepository favoriteRepository){
		this.favoriteRepository = favoriteRepository;
	}

	public List<Article> getFavoriteArticlesInRepository() {
		return this.favoriteRepository.getFavoriteArticles();
	}

	public List<String> getFavoriteArticleIdsInRepository(){
		return this.favoriteRepository.getFavoriteArticleIds();
	}

	public boolean isFavoriteArticleInRepository(String articleId) {
		return this.favoriteRepository.isFavoriteArticle(articleId);
	}

	public FavoriteActionResult updateArticleInFavoriteRepository(
		FavoriteAction updateFavoriteAction, String articleId) {
		return this.favoriteRepository.updateArticleInFavorite(updateFavoriteAction, articleId);
	}
}
