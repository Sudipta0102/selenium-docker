package com.pseudofunc.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pseudofunc.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage{

	//private WebDriver driver;
	
	@FindBy(id="go-to-flights-search")
	private WebElement goToFlightSearchButton;
	
	@FindBy(xpath="//div[@id='registration-confirmation-section']/descendant::p/b")
	private WebElement firstNameConfirmation;
	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToFlightSearch() {
		this.goToFlightSearchButton.click();
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
		return this.goToFlightSearchButton.isDisplayed();
	}
	
	public String getFirstName() {
		return this.firstNameConfirmation.getText();
	}
	
}
