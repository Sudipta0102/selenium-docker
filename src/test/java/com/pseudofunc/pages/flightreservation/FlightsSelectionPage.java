package com.pseudofunc.pages.flightreservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pseudofunc.pages.AbstractPage;

public class FlightsSelectionPage extends AbstractPage{
	
	@FindBy(name="departure-flight")
	private List<WebElement> departureFlights;

	@FindBy(name="arrival-flight")
	private List<WebElement> arrivalFlights;
	
	@FindBy(id="confirm-flights")
	private WebElement confirmFlights_button;
	
	public FlightsSelectionPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlights_button));
		return this.confirmFlights_button.isDisplayed();
	}

	// this is not ideal. However, testing the whole functionality is my sole purpose of this, so selecting
	// random radio button to get on with the test flow.
	public void selectFlights(){
		
		int randomnum = ThreadLocalRandom.current().nextInt(0, this.departureFlights.size());
		this.departureFlights.get(randomnum).click();
		this.arrivalFlights.get(randomnum).click();
	}
	
	public void confirmFlights() {
		this.confirmFlights_button.click();
	}
	
	
}
