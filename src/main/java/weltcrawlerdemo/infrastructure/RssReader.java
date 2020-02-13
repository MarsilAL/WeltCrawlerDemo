package weltcrawlerdemo.infrastructure;

import java.io.*;
import java.net.*;
import java.util.*;

import weltcrawlerdemo.domain.Article;

public class RssReader implements IRssReader {

	/** parses rss feed and returns is as a list of articles */
	public List<Article> fetchArticles(final String urlAddress, int maxSize) {    // fetchArticles() MMC=7
		try {
			// holds all the articles we fetched from rss
			List<Article> articles = new ArrayList<Article>();

			// fetch the rss fedd
			final URL rssUrl = new URL(urlAddress);
			final BufferedReader in;
			
			in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));

			String line;
			while ((line = in.readLine()) != null) {
				String title = "";

				if (line.contains("<title>")) {
					final int firstPos = line.indexOf("<title>");
					title = line.substring(firstPos);
					title = title.replace("<title>", "");
					final int lastPos = title.indexOf("</title>");
					title = title.substring(0, lastPos);

					// construct the article	
					Article article = new Article(title, "");

					// add the article to the final result, which will be returns
					articles.add(article);

					// stop when we reached the maxSize
					if (articles.size() == maxSize) {
						break;
					}
				}
				
			}
			in.close();
			
			// extract the relevant data (title)
			return articles;

		} catch (final MalformedURLException ue) {
			System.out.println("Malformed URL");
		} catch (final IOException ioe) {
			System.out.println(ioe);
		} catch (Exception e ){
			System.out.println(e);
		}
		return null;
	}
}
