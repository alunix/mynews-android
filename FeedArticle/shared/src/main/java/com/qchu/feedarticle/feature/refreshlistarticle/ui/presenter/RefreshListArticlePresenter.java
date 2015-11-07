package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

import com.google.common.collect.Lists;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Channel;
import com.qchu.feedarticle.feature.article.applogic.entity.ChannelConfig;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.article.applogic.util.ArticleIdTransformFunction;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;

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
