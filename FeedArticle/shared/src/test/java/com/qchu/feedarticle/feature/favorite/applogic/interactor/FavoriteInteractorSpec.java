package com.qchu.feedarticle.feature.favorite.applogic.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

}
