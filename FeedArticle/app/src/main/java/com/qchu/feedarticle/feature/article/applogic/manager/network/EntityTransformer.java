package com.qchu.feedarticle.feature.article.applogic.manager.network;

import com.qchu.feedarticle.common.DateDeserializer;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Image;
import com.qchu.feedarticle.feature.article.applogic.entity.Channel;
import com.qchu.feedarticle.feature.article.applogic.entity.ChannelConfig;
import com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.html.HtmlParser;
import com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.html.ParsedImage;
import com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.xml.ParsedItem;
import com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.xml.ParsedLink;
import com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.xml.ParsedRSS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class EntityTransformer {
	public static Channel siteFrom(ChannelConfig channelConfig, ParsedRSS parsedRSS) {
		String siteUrl = null;
		for(ParsedLink parsedLink: parsedRSS.getChannel().getLinks()) {
			if(parsedLink.getLink() != null) {
				siteUrl = parsedLink.getLink();
				break;
			}
		}

		return Channel.builder()
			.siteConfig(channelConfig)
			.id(siteUrl)
			.title(parsedRSS.getChannel().getTitle())
			.url(siteUrl)
			.articleList(articlesFromRSS(parsedRSS))
			.build();
	}

	static List<Article> articlesFromRSS(ParsedRSS parsedRSS) {
		List<Article> articles = new ArrayList<>();

		for(ParsedItem parsedItem: parsedRSS.getChannel().getItems()) {

			String content = parsedItem.getContent() != null ?
				parsedItem.getContent():
				parsedItem.getDescription();

			List<ParsedImage> parsedImageList = HtmlParser.getImagesFromHtml(content);

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
				.identifier(parsedItem.getLink())
				.title(parsedItem.getTitle())
				.summary(parsedItem.getDescription())
				.content(content)
				.webUrl(parsedItem.getLink())
				.publicationDate(DateDeserializer.get().deserialize(parsedItem.getPubDate()))
				.mainImage(images.isEmpty() ? null : images.get(0))
				.imageList(images)
				.build());
		}

		return articles;
	}
}
