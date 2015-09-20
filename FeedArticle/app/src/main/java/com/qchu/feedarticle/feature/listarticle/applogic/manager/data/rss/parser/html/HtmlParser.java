package com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.html;

import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.rss.parser.xml.ParsedItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 19/09/15.
 */
public class HtmlParser {

	public static List<ParsedImage> getImagesFromHtml(String htmlString) {
		Document document = Jsoup.parse(htmlString);
		Elements elements = document.select("img[src]");

		List<ParsedImage> parsedImages = new ArrayList<>();

		for(Element element: elements) {
			ParsedImage parsedImage = ParsedImage.builder()
				.src(element.attr("src"))
				.width(floatValueFromString(element.attr("width")))
				.height(floatValueFromString(element.attr("height")))
				.build();

			parsedImages.add(parsedImage);
		}

		return parsedImages;
	}

	static float floatValueFromString(String string){
		return (string != null && !string.equals("")) ? Float.parseFloat(string): 0;
	}
}
