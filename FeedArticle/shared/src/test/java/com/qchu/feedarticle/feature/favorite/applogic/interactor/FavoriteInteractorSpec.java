package com.qchu.feedarticle.feature.favorite.applogic.interactor;

import com.qchu.feedarticle.domain.favorite.interactor.DefaultFavoriteInteractor;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult;
import com.qchu.feedarticle.domain.favorite.interactor.FavoriteInteractor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by quocdungchu on 30/10/15.
 */
public class FavoriteInteractorSpec {

	com.qchu.feedarticle.domain.favorite.interactor.FavoriteRepository favoriteRepository;
	FavoriteInteractor favoriteInteractor;

	@Before public void setup(){
		this.favoriteRepository = mock(com.qchu.feedarticle.domain.favorite.interactor.FavoriteRepository.class);
		this.favoriteInteractor = new DefaultFavoriteInteractor(this.favoriteRepository);
	}
	
	@Test public void addSuccess(){
		given(this.favoriteRepository
			.updateArticleInFavorite(com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.ADD, "article1"))
			.willReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.ADD_SUCCESSFUL);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.ADD, "article1"))
			.thenReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.ADD_SUCCESSFUL);
	}

	@Test public void addFailed_because_exist_already(){
		given(this.favoriteRepository
			.updateArticleInFavorite(com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.ADD, "article2"))
			.willReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.ADD, "article2"))
			.thenReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY);
	}

	@Test public void addFailed_because_other_reason(){
		given(this.favoriteRepository
			.updateArticleInFavorite(com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.ADD, "article3"))
			.willReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.ADD_FAILED_REASON_OTHER);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.ADD, "article3"))
			.thenReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.ADD_FAILED_REASON_OTHER);
	}

	@Test public void removeSuccess(){
		given(this.favoriteRepository
			.updateArticleInFavorite(com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.REMOVE, "article1"))
			.willReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.REMOVE_SUCCESSFUL);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.REMOVE, "article1"))
			.thenReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.REMOVE_SUCCESSFUL);
	}

	@Test public void removeFailed_because_not_exist(){
		given(this.favoriteRepository
			.updateArticleInFavorite(com.qchu.feedarticle.domain.favorite.interactor.FavoriteAction.REMOVE, "article2"))
			.willReturn(com.qchu.feedarticle.domain.favorite.interactor.FavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.REMOVE, "article2"))
			.thenReturn(FavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST);
	}

}
