package Crawler;

public class Main {

	public static void main(String arg[]) throws Exception {
		/**
		 * The url of the issues that we are going to crawl
		 */
		String[] urls = {"https://issues.apache.org/jira/browse/CAMEL-10597"};
		
		/**
		 * Crawling the urls
		 */
		Crawl crawl = new Crawl();
		crawl.Do(urls);
		
	}
}
