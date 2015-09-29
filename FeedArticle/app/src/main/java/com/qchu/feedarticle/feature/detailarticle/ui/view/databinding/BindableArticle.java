package com.qchu.feedarticle.feature.detailarticle.ui.view.databinding;

import lombok.Builder;
import lombok.ToString;

/**
 * Created by quocdungchu on 29/09/15.
 */
@Builder
@ToString(includeFieldNames=true)
public class BindableArticle {
	String title;
	String content;
	String publicationDate;

	public String getTitle(){
		return title;
	}

	public String getContent(){
		return content;
	}

	public String getPublicationDate(){
		return publicationDate;
	}
}
