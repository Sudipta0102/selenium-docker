package com.pseudofunc.tests.vendorportal;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pseudofunc.pages.vendorportal.DashboardPage;
import com.pseudofunc.pages.vendorportal.LogInPage;
import com.pseudofunc.tests.AbstractTest;



public class VendorPortalTest extends AbstractTest{
	
	
	private LogInPage loginPage;
	private DashboardPage dashboardPage;
	
	@BeforeTest
	public void setPages() {
			
		this.loginPage = new LogInPage(driver);
		this.dashboardPage = new DashboardPage(driver);
				
	}
	
	
	@Test
	public void loginTest() {
		
		//loginPage = new LogInPage(driver);
		loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");

		Assert.assertTrue(loginPage.isAt());
		
		loginPage.userCredentials("sam", "sam");
		
		loginPage.login();
		
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		
		//dashboardPage = new DashboardPage(driver);
		Assert.assertTrue(dashboardPage.isAt());

		//finance metrics
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), "$40,000");
		Assert.assertEquals(dashboardPage.getAnnualEarning(), "$215,000");
		Assert.assertEquals(dashboardPage.getProfitMargin(), "50%");
		Assert.assertEquals(dashboardPage.getAvailableInventory(), "18");
		
		//search
		dashboardPage.searchOrderHistory("adams");
		Assert.assertEquals(dashboardPage.getNumberOfSearchResults(), 8);
			
	}
	
	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		//logout
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}

}
