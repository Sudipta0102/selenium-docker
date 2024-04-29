package com.pseudofunc.pages.flightreservation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pseudofunc.pages.AbstractPage;

// adding wait later.

public class RegistrationPage extends AbstractPage{
	
	//private WebDriver driver; don't need that here anymore before of abstractpage.
	
	@FindBy(id="firstName")
	private WebElement firstName;
	
	@FindBy(id="lastName")
	private WebElement lastName;
	
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(name="street")
	private WebElement street;
	
	@FindBy(name="city")
	private WebElement city;
	
	@FindBy(name="zip")
	private WebElement zip;
	
	@FindBy(id="register-btn")
	private WebElement resgister_button;
	
	@FindBy(id="inputState")
	private List<WebElement> state;

	public RegistrationPage(WebDriver driver) {
		
		super(driver); 
		
	}
	
	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void enterUserDetails(String fName, String lName) {
		this.firstName.sendKeys(fName);
		this.lastName.sendKeys(lName);
	}
	
	public void enterUserCredentials(String email, String password) {
		this.email.sendKeys(email);
		this.password.sendKeys(password);
	}
	
	public void enterAddress(String street, String city, String zip) {
		this.street.sendKeys(street);
		this.city.sendKeys(city);
		this.zip.sendKeys(zip);
	}
	
	public void register() {
		this.resgister_button.click();
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.resgister_button));
		return this.resgister_button.isDisplayed();		
	}
}
