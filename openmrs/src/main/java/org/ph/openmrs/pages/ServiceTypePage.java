package org.ph.openmrs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServiceTypePage {
	WebDriver driver;
	boolean result=false;
	
	public ServiceTypePage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean addServiceType(String serviceType){
		
		if(getServiceType(serviceType)){
			System.out.println("Service Type with this name is already present.");
		}
		else{
			driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/button")).click();
			WebElement element = driver.findElement(By.id("name-field"));
			element.clear();
			element.sendKeys("Paediatric Oncology");
			element = driver.findElement(By.id("duration-field"));
			element.clear();
			element.sendKeys("30");
			driver.findElement(By.id("save-button")).submit();
			result = true;
		}
		return result;
		
	}
	
	public boolean getServiceType(String servieType){
		
		List<WebElement> pageList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable_paginate']/span/a"));
		System.out.println("No of pages: "+pageList.size());
		for(int i = 0; i<=pageList.size(); i++){
			
			pageList.get(i).click();
			List<WebElement> serviceList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			System.out.println("No of services found"+serviceList.size());
			for(int j = 0; j<serviceList.size(); i++){
				
				if(serviceList.get(j).getText().equalsIgnoreCase(servieType)){
					result=true;
					break;
				}
			}
		}
		
		return result;
	}
	
	public boolean editServiceType(String serviceType){
		boolean serviceTypePresent = getServiceType(serviceType);
		if(serviceTypePresent){
			driver.findElement(By.id(".//*[@id='appointmentschedulingui-edit-"+serviceType)).click();
			driver.findElement(By.id("description-field")).sendKeys("Update Service Type");
			driver.findElement(By.id("save-button")).click();
			result=true;
		}
		else{
			System.out.println("Serive Type is not found");
		}
		return result;
	}
	
	public boolean deleteServiceType(String serviceType){
		
		if(getServiceType(serviceType)){
			
			String text = driver.findElement(By.id("appointmentTypesTable_info")).getText();
			int start = text.lastIndexOf("of");
			int end = text.lastIndexOf("entries");
			String count = text.substring(start+3, end-1);
			driver.findElement(By.id("appointmentschedulingui-delete-"+serviceType)).click();
			driver.findElement(By.xpath("(.//*[@id='delete-appointment-type-dialog']/div[2]/button[1])["+count+"]")).click();
			result = true;
		}
		else{
			System.out.println("Serive Type is not found");
		}
		return result;
	}
}
