package org.ph.openmrs;

import org.junit.Assert;
import org.ph.openmrs.pages.LoginPage;
import org.ph.openmrs.pages.ServiceTypePage;
import org.ph.utility.BaseClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ServiceTypeTests extends BaseClass {

	@Parameters({ "userName", "passWord" })
	@Test(description = "Add Service Type", priority = 1, groups = { "Sanity", "Integration" })
	public void manageAddServiceType(String userName, String passWord) throws InterruptedException {

		LoginPage login = new LoginPage(driver);
		login.login(userName, passWord);
		// Get instance of page and call method
		ServiceTypePage servicePage = new ServiceTypePage(driver);
		boolean result = servicePage.addServiceType("Pediatric Oncology");
		Assert.assertTrue(result);
	}
	
	@Parameters({ "userName", "passWord" })
	@Test(description = "Edit Service Type", priority = 2, groups = { "Sanity", "Integration" })
	public void manageEditServiceType(String userName, String passWord) throws InterruptedException {

		LoginPage login = new LoginPage(driver);
		login.login(userName, passWord);
		// Get instance of page and call method
		ServiceTypePage servicePage = new ServiceTypePage(driver);
		boolean result = servicePage.editServiceType("Pediatric Oncology");
		Assert.assertTrue(result);
	}
	
	@Parameters({ "userName", "passWord" })
	@Test(description = "Delete Service Type", priority = 3, groups = { "Sanity", "Integration" })
	public void managedeleteServiceType(String userName, String passWord) throws InterruptedException {

		LoginPage login = new LoginPage(driver);
		login.login(userName, passWord);
		// Get instance of page and call method
		ServiceTypePage servicePage = new ServiceTypePage(driver);
		boolean result = servicePage.deleteServiceType("Pediatric Oncology-Update");
		Assert.assertTrue(result);
		
		result = servicePage.getServiceType("Pediatric Oncology-Update");
		Assert.assertFalse(result);
	}
}
