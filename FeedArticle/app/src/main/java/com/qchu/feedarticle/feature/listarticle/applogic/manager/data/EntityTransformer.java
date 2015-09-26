package com.qchu.feedarticle.feature.listarticle.applogic.manager.data;

import com.qchu.feedarticle.common.DateDeserializer;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Image;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Site;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.html.HtmlParser;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.html.ParsedImage;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedItem;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedLink;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedRSS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class EntityTransformer {
	public static Site siteFrom(SiteConfig siteConfig, ParsedRSS parsedRSS) {
		String siteUrl = null;
		for(ParsedLink parsedLink: parsedRSS.getChannel().getLinks()) {
			if(parsedLink.getLink() != null) {
				siteUrl = parsedLink.getLink();
				break;
			}
		}

		return Site.builder()
			.siteConfig(siteConfig)
			.id(siteUrl)
			.url(siteUrl)
			.articleList(articlesFromRSS(parsedRSS))
			.build();
	}

	static List<Article> articlesFromRSS(ParsedRSS parsedRSS) {
		List<Article> articles = new ArrayList<>();

		for(ParsedItem parsedItem: parsedRSS.getChannel().getItems()) {
			List<ParsedImage> parsedImageList = HtmlParser.getImagesFromHtml(parsedItem.getContent());

			List<Image> images = new ArrayList<>();
			for(ParsedImage parsedImage: parsedImageList) {
				if(parsedImage.isValid()) {
					images.add(Image.builder()
						.url(parsedImage.getSrc())
						.width(parsedImage.getWidth())
						.height(parsedImage.getHeight())
						.build());
				}
			}

			articles.add(Article.builder()
				.id(parsedItem.getLink())
				.title(parsedItem.getTitle())
				.description(parsedItem.getDescription())
				.content(parsedItem.getContent())
				.webUrl(parsedItem.getLink())
				.publicationDate(DateDeserializer.get().deserialize(parsedItem.getPubDate()))
				.mainImage(images.isEmpty() ? null : images.get(0))
				.imageList(images)
				.build());
		}

		return articles;
	}
}
