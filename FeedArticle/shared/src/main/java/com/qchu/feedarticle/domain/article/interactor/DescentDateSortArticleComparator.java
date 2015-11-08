package com.qchu.feedarticle.domain.article.interactor;

import com.qchu.feedarticle.domain.article.entity.Article;

import java.util.Comparator;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class DescentDateSortArticleComparator implements Comparator<Article> {
	@Override
	public int compare(Article lhs, Article rhs) {
		if(lhs.publicationDate() == null)
			return 1;
		else if(rhs.publicationDate() == null)
			return -1;
		else
			return rhs.publicationDate().compareTo(lhs.publicationDate());
	}
}
