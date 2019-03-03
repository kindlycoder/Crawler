package Crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Selenium class which is responsible for retrieving events and the client side renderings 
 * @author Noei
 */
class Selenium {
	
	WebDriver driver = null;

	/**
	 * Setting up the driver which is chrome in this case 
	 * @author Noei
	 */
	public Selenium() {
		System.setProperty("webdriver.chrome.driver", "ExternalJars/sel/chromedriver.exe");
		driver = new ChromeDriver();
	}

	/**
	 * Closing the driver
	 * @author Noei
	 */
	private void Final() {
		driver.close();
	}

	/**
	 * This method gets the url and returns the complete DOM of the page
	 * @param url the requested url
	 * @return String
	 * @author Noei
	 */
	public String Get(String url) throws Exception {
		try {
			driver.get(url);
			return(driver.getPageSource().toString());
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			Final();
		}
		return "";
	}
}