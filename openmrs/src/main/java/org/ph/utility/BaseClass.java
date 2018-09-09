package org.ph.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.qameta.allure.Attachment;

public class BaseClass {
	
	protected WebDriver driver;
	@BeforeTest(alwaysRun=true)
	public void beforeTest() throws FileNotFoundException, IOException, InterruptedException{
		
		Properties pro = new Properties();
		pro.load(new FileInputStream(new File("config.properties")));
		driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(pro.getProperty("url"));

	}
	@AfterTest(alwaysRun=true)
	public void afterTest(){
		
		driver.findElement(By.cssSelector(".logout>a")).click();
		DriverFactory.deactivateDriver();
	}
	/**
	 * screenShot method is invoked whenever the Testcase is Failed.
	 * @param name
	 * @param driver
	 * @return
	 */
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public byte[] saveScreenshot(String name, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
 
	public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
		iHookCallBack.runTestMethod(iTestResult);
		if (iTestResult.getThrowable() != null) {
			this.saveScreenshot(iTestResult.getName(), driver);
		}
	}
}
