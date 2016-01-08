package com.qchu.rss.parsed.html;

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
		List<ParsedImage> parsedImages = new ArrayList<>();

		if(htmlString != null) {
			Document document = Jsoup.parse(htmlString);
			Elements elements = document.select("img[src]");

			for(Element element: elements) {

        ParsedImage parsedImage = ParsedImage.of(element.attr("src"),
          floatValueFromString(element.attr("width")),
          floatValueFromString(element.attr("height")));
				parsedImages.add(parsedImage);
			}
		}
		
		return parsedImages;
	}

	private static float floatValueFromString(String string){
		return (string != null && !string.equals("")) ? Float.parseFloat(string): 0;
	}
}
