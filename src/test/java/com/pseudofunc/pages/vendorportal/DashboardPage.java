package com.pseudofunc.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pseudofunc.pages.AbstractPage;

public class DashboardPage extends AbstractPage{
	
	private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

	@FindBy(id="monthly-earning")
	private WebElement monthly_earning;
	
	@FindBy(id="annual-earning")
	private WebElement annual_earning;
	
	@FindBy(id="profit-margin")
	private WebElement profit_margin;
	
	@FindBy(id="available-inventory")
	private WebElement available_inventory;
	
	@FindBy(xpath="//input[@type='search']")
	private WebElement search;
	
	@FindBy(xpath="//select[@name='dataTable_length']")
	private WebElement numOfEntriesPerPage;
	
	@FindBy(id="dataTable_info")
	private WebElement searchResultCount;
	
	@FindBy(xpath="//a[@id='userDropdown']/img")
	private WebElement userProfileImage;
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	private WebElement logout_button;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.monthly_earning));
		return this.monthly_earning.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		
		return this.monthly_earning.getText(); 
		
	}
	
	public String getAnnualEarning() {
		
		return this.annual_earning.getText(); 
		
	}
	
	public String getProfitMargin() {
		
		return this.profit_margin.getText(); 
		
	}
	
	public String getAvailableInventory() {
		
		return this.available_inventory.getText(); 
		
	}
	
	public void searchOrderHistory(String keyWord) {
		
		this.search.sendKeys(keyWord);
		
	}
	
	/*
	 * sample string would be: Showing 1 to 25 of 92 entries (filtered from 99 total entries)
	 * 
	 * we need the number 92 which is apparently in the arr[5] position alaways.
	 * So we can split by space and return the fifth element.
	 */
	public int getNumberOfSearchResults() {
		String resultText = this.searchResultCount.getText();
		
		String arr[] = resultText.split(" ");
		
		int count = Integer.parseInt(arr[5]);
		log.info("Results Count: {}", count);
		return count;
	}
	
	public void logout() {
		
		this.userProfileImage.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.logout));
		this.logout.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.logout_button));
		this.logout_button.click();
		
	}
}
