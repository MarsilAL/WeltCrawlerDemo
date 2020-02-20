package weltcrawlerdemo.infrastructure;

import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import weltcrawlerdemo.domain.Article;
import weltcrawlerdemo.domain.ArticleUseCase;

public class RssReader implements IRssReader {


	public List<Article> fetchArticles(final String urlAddress, int maxSize) {    
		try {

			List<Article> articles = new ArrayList<Article>();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(urlAddress);
            doc.getDocumentElement().normalize();



			for(int i=0; i<maxSize; i++) {
				
				//get title
				final Node nodeTitle = doc.getElementsByTagName("title").item(i+2);
				String title = nodeTitle.getTextContent();

				final Node nodeCategory= doc.getElementsByTagName("category").item(i+1);
				String subcategory = nodeCategory.getTextContent();
				
                final Node nodeGuid = doc.getElementsByTagName("guid").item(i);
                String guidAsString = nodeGuid.getTextContent();
				int guid = Integer.parseInt(guidAsString);
				
                final Node nodePubDate = doc.getElementsByTagName("pubDate").item(i+1);
				String pubdate = nodePubDate.getTextContent();
				
                final Node nodeDescription = doc.getElementsByTagName("description").item(i+1);
				String description = nodeDescription.getTextContent();
				
				final Node nodeLink = doc.getElementsByTagName("link").item(i+1);
                String link = nodeLink.getTextContent();

				String category = ArticleUseCase.getCategoryForUrl(urlAddress);


				Article article = new Article(guid, subcategory, category, title, pubdate, description, link);

				articles.add(article);
				
			}
		

			return articles;

		} catch (Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<Article>();
    }
}
