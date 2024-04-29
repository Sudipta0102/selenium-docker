package com.pseudofunc.pages.flightreservation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pseudofunc.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage{

	@FindBy(id="oneway")
	private WebElement oneway_radiobutton;
	
	@FindBy(id="twoway")
	private WebElement roubrTrip_radiobutton;
	
	@FindBy(id="passengers")
	private WebElement passengers_dropdown;
	
	@FindBy(id="depart-from")
	private WebElement departingFrom_dropdown;
	
	@FindBy(id="arrive-in")
	private WebElement arrivein_dropdown;
	
	@FindBy(id="service-class1")
	private WebElement economy_radiobutton;
	
	@FindBy(id="service-class2")
	private WebElement firstclass_radiobutton;
	
	@FindBy(id="service-class3")
	private WebElement businessclass_radiobutton;
	
	@FindBy(id="search-flights")
	private WebElement searchFlights_button;
	
	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.searchFlights_button));
		return this.searchFlights_button.isDisplayed();
	}

	public void selectPassengers() {
		
	}
	
}
