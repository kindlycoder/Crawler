package Crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Crawl class which is responsible for Crawling
 * @author Noei
 */
class Crawl {
	
	public void Do(String[] urls) throws IOException
	{
		FileOutputStream issues = null;
		FileOutputStream cmnts = null;
		try
		{
			/**
			 * This array is responsible for saving metrics issues
			 */
			ArrayList<String> Atts = new ArrayList<String>();
			
			/**
			 * This parameter is responsible for assigning specific id for each issue
			 */
			int id = 1;
			
			/**
			 * Creating the csv file for issues
			 */
			issues = new FileOutputStream("issues.csv");  
			PrintStream issues_csv = new PrintStream(issues);  
			
			/**
			 * Creating the csv file for comments
			 */
			cmnts = new FileOutputStream("comments.csv");  
			PrintStream comments_csv = new PrintStream(cmnts);  
			
			/**
			 * Writing column names
			 */
			issues_csv.println("Id, Assignee, Reporter, Votes, Watchers, Created, Updated, Resolved, Type, Status, Priority, Resolution, Affects Version, Fix Version, Component, Labels, Patch Info, Estimated Complexity, Description");
	
			
			/**
			 * Writing column names for comments csv
			 */
			comments_csv.println("content, issue_id");
			
			/**
			 * Iterate the issues
			 */
			for (String url : urls) {
				try {
					/**
					 * get the complete DOM using Selenium
					 */
					Selenium ms = new Selenium();
					String html = ms.Get(url);
					
					/**
					 * Passing the complete DOM to Jsoup parser
					 */
					Document document = Jsoup.parse(html);
					
					/**
					 * Saving metric values into Atts arraylist
					 */
					Atts.add(Integer.toString(id));
					
					/**
					 * Looping into people and dates
					 */
					for (Element row : document.select(".item-details dl")) {
						Atts.add(row.select("dd").text());
					}
					
					/**
					 * Looping into the details section
					 */
					for (Element row : document.select(".mod-content").select(".item")) {
						Atts.add(row.select(".value").text());
					}
					
					/**
					 * Saving and normalizing descriptions
					 */
					String description = document.select("#description-val").text();
					description = description.replace("\r", " ");
					description = description.replace("\n", " ");
					description = description.replace("\t", " ");
					Atts.add(description);
					
					/**
					 * Looping into comments
					 */
					for (Element row : document.select(".issue-data-block")) {
						comments_csv.println("\""+row.select(".action-details").text()+"\","+"\""+id+"\",");
					}
					
					/**
					 * Printing the Atts array parameters
					 */
					for(int i=0;i<Atts.size()-1;i++) {
						issues_csv.print("\""+Atts.get(i)+"\",");
			        }
			        issues_csv.println("\""+Atts.get(Atts.size()-1)+"\"");
			        
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				id++;
			}
		}
		catch (Exception e) {
			System.err.println(e);
		}
		finally {
	        /**
	         * Closing the csv files
	         */
	        cmnts.close();
			issues.close();
		}
		
	}
}