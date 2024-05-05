package com.pseudofunc.tests.vendorportal;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pseudofunc.pages.vendorportal.DashboardPage;
import com.pseudofunc.pages.vendorportal.LogInPage;
import com.pseudofunc.tests.AbstractTest;
import com.pseudofunc.tests.vendorportal.model.VendorPortalTestData;
import com.pseudofunc.util.JSONUtil;



public class VendorPortalTest extends AbstractTest{
	
	
	private LogInPage loginPage;
	private DashboardPage dashboardPage;
	private VendorPortalTestData vendorPortalTestData; 
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setPages(String testDataPath) {
			
		this.loginPage = new LogInPage(driver);
		this.dashboardPage = new DashboardPage(driver);
		this.vendorPortalTestData = JSONUtil.getTestData(testDataPath, VendorPortalTestData.class);
		
	}
	
	
	@Test
	public void loginTest() {
		
		//loginPage = new LogInPage(driver);
		loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");

		Assert.assertTrue(loginPage.isAt());
		
		loginPage.userCredentials(vendorPortalTestData.username(), vendorPortalTestData.password());
		
		loginPage.login();
		
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		
		//dashboardPage = new DashboardPage(driver);
		Assert.assertTrue(dashboardPage.isAt());

		//finance metrics
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), vendorPortalTestData.monthly_earning());
		Assert.assertEquals(dashboardPage.getAnnualEarning(), vendorPortalTestData.annual_earning());
		Assert.assertEquals(dashboardPage.getProfitMargin(), vendorPortalTestData.profit_margin());
		Assert.assertEquals(dashboardPage.getAvailableInventory(), vendorPortalTestData.available_inventory());
		
		//search
		dashboardPage.searchOrderHistory(vendorPortalTestData.search_keyword());
		Assert.assertEquals(dashboardPage.getNumberOfSearchResults(), vendorPortalTestData.search_count_result());
			
	}
	
	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		//logout
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}

}
