package org.ph.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

public class DriverFactory {

	//Responsible for creation and deactivating of driver.
	private static WebDriver driver;
	private static WebDriver instanciateBrowser() throws FileNotFoundException, IOException{
		
		Properties pro = new Properties();
		pro.load(new FileInputStream(new File("config.properties")));
		if (pro.getProperty("browserType").equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", "gecodrive.exe");
			driver = new FirefoxDriver();
		} else if (pro.getProperty("browserType").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
	public static WebDriver getDriver() throws FileNotFoundException, IOException{
		
		WebDriver driver = instanciateBrowser();
		return driver;
	}	
	public void setDriver(WebDriver setDriver){
		
		driver=setDriver;
	}
	public static void deactivateDriver(){
		
		driver.close();
		driver = null;
	}
}
