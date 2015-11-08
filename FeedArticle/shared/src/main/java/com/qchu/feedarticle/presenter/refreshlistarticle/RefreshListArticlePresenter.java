package com.qchu.feedarticle.presenter.refreshlistarticle;

import com.google.common.collect.Lists;
import com.qchu.feedarticle.domain.article.entity.Article;
import com.qchu.feedarticle.domain.article.entity.Channel;
import com.qchu.feedarticle.domain.article.entity.ChannelConfig;
import com.qchu.feedarticle.domain.article.interactor.ArticleInteractor;
import com.qchu.feedarticle.domain.article.util.ArticleIdTransformFunction;
import com.qchu.feedarticle.presenter.listarticle.ListArticlePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class RefreshListArticlePresenter extends ListArticlePresenter
	implements RefreshListArticleUserEventHandler {

	final ArticleInteractor articleInteractor;
	final RefreshListArticleUserInterface refreshListArticleUserInterface;
	final RefreshListArticleWireframeInterface refreshListArticleWireframeInterface;

	public RefreshListArticlePresenter(
		ArticleInteractor articleInteractor,
		RefreshListArticleUserInterface refreshListArticleUserInterface,
		RefreshListArticleWireframeInterface refreshListArticleWireframeInterface) {

		super(refreshListArticleUserInterface, refreshListArticleWireframeInterface);

		this.articleInteractor = articleInteractor;
		this.refreshListArticleUserInterface = refreshListArticleUserInterface;
		this.refreshListArticleWireframeInterface = refreshListArticleWireframeInterface;
	}

	@Override
	public void onCreate() {
		refreshContent();
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface) {
		refreshContent();
	}

	private void refreshContent(){
		List<ChannelConfig> channelConfigList = new ArrayList<>();

		channelConfigList.add(ChannelConfig.builder()
			.url("http://feeds.feedburner.com/RayWenderlich")
			.build());

		channelConfigList.add(ChannelConfig.builder()
			.url("http://thethaovanhoa.vn/trang-chu.rss")
			.build());

		this.articleInteractor.refreshArticles(channelConfigList, new ArticleInteractor.RefreshArticleListListener() {
			@Override
			public void onBegin(ArticleInteractor articleInteractor) {
				articleIdList = new ArrayList<>();
				refreshListArticleUserInterface.beginSwipeRefreshingLayout(RefreshListArticlePresenter.this);
			}

			@Override
			public void onNextSite(ArticleInteractor articleInteractor,
			                       Channel channel, List<Article> allArticleSortedList) {
				articleIdList = Lists.newArrayList(Lists.transform(allArticleSortedList,
					new ArticleIdTransformFunction()));
				refreshListArticleUserInterface.bindArticles(allArticleSortedList);
			}

			@Override
			public void onComplete(ArticleInteractor articleInteractor,
			                       List<Article> allArticleSortedList) {

				articleIdList = Lists.newArrayList(Lists.transform(allArticleSortedList,
					new ArticleIdTransformFunction()));
				refreshListArticleUserInterface.endSwipeRefreshingLayout(RefreshListArticlePresenter.this);
			}
		});
	}

}
