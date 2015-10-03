package com.qchu.feedarticle.feature.detailarticle.ui.view.databinding;

import org.parceler.Parcel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by quocdungchu on 29/09/15.
 */

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(includeFieldNames=true)
@Parcel
public class BindableArticle{
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
