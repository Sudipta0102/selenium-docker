package com.pseudofunc.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pseudofunc.pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage {
	
	// logger
	private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
	
	@FindBy(xpath="//form[@class='row g-3']/descendant::div[@class='row'][1]/div[2]")
	private WebElement flightConfirmationNumber;
	
	@FindBy(xpath="//form[@class='row g-3']/descendant::div[@class='row'][2]/div[2]")
	private WebElement tax;
	
	@FindBy(xpath="//form[@class='row g-3']/descendant::div[@class='row'][3]/div[2]")
	private WebElement totalPrice;
	
	@FindBy(xpath="//h2[contains(text(),'Flights Confirmation')]")
	private WebElement flightConfirmationPageHeader;
		
	public FlightConfirmationPage(WebDriver driver) {
		super(driver);		
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationPageHeader));
		return this.flightConfirmationPageHeader.isDisplayed();
	}

	public String getPrice() {
		
		String flightConfirmationNumber = this.flightConfirmationNumber.getText();
		String price = this.totalPrice.getText();
		
		log.info("Flight Confirmation# : {}", flightConfirmationNumber);
		log.info("total pprice: {}", price);
		
		return price;
	}
	
}
