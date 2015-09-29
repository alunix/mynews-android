package com.qchu.feedarticle.feature.listarticle.ui.view.databinding;

import java.util.Date;

import lombok.Builder;
import lombok.ToString;

/**
 * Created by quocdungchu on 26/09/15.
 */
@Builder
@ToString(includeFieldNames=true)
public class BindableArticle {
	String title;
	String publicationDate;
	String imageURL;
	float imageWidth;
	float imageHeight;

	public String getTitle(){
		return title;
	}

	public String getPublicationDate(){
		return publicationDate;
	}

	public String getImageURL(){
		return imageURL;
	}

	public float getImageWidth(){
		return imageWidth;
	}

	public float getImageHeight(){
		return imageHeight;
	}
}
