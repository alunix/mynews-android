package com.qchu.feedarticle.feature.favorite.applogic.interactor;

import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteRepository;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.DefaultFavoriteInteractor;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteAction;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteActionResult;
import com.qchu.feedarticle.applogic.domain.favorite.interactor.FavoriteInteractor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by quocdungchu on 30/10/15.
 */
public class FavoriteInteractorSpec {

	FavoriteRepository favoriteRepository;
	FavoriteInteractor favoriteInteractor;

	@Before public void setup(){
		this.favoriteRepository = mock(FavoriteRepository.class);
		this.favoriteInteractor = new DefaultFavoriteInteractor(this.favoriteRepository);
	}
	
	@Test public void addSuccess(){
		given(this.favoriteRepository
			.updateArticleInFavorite(FavoriteAction.ADD, "article1"))
			.willReturn(FavoriteActionResult.ADD_SUCCESSFUL);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.ADD, "article1"))
			.thenReturn(FavoriteActionResult.ADD_SUCCESSFUL);
	}

	@Test public void addFailed_because_exist_already(){
		given(this.favoriteRepository
			.updateArticleInFavorite(FavoriteAction.ADD, "article2"))
			.willReturn(FavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.ADD, "article2"))
			.thenReturn(FavoriteActionResult.ADD_FAILED_REASON_EXIST_ALREADY);
	}

	@Test public void addFailed_because_other_reason(){
		given(this.favoriteRepository
			.updateArticleInFavorite(FavoriteAction.ADD, "article3"))
			.willReturn(FavoriteActionResult.ADD_FAILED_REASON_OTHER);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.ADD, "article3"))
			.thenReturn(FavoriteActionResult.ADD_FAILED_REASON_OTHER);
	}

	@Test public void removeSuccess(){
		given(this.favoriteRepository
			.updateArticleInFavorite(FavoriteAction.REMOVE, "article1"))
			.willReturn(FavoriteActionResult.REMOVE_SUCCESSFUL);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.REMOVE, "article1"))
			.thenReturn(FavoriteActionResult.REMOVE_SUCCESSFUL);
	}

	@Test public void removeFailed_because_not_exist(){
		given(this.favoriteRepository
			.updateArticleInFavorite(FavoriteAction.REMOVE, "article2"))
			.willReturn(FavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST);

		when(this.favoriteInteractor.updateArticleInFavoriteRepository(
			FavoriteAction.REMOVE, "article2"))
			.thenReturn(FavoriteActionResult.REMOVE_FAILED_REASON_NOT_EXIST);
	}

}
