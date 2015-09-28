package com.qchu.feedarticle.feature.detailarticle.ui.presenter;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public interface DetailArticleUserInterface {
	void bindArticles(List<Article> articleList);
	void selectIndex(int selectedIndex, boolean animated);
}
