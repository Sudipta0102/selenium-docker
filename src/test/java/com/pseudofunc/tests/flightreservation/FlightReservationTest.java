package com.pseudofunc.tests.flightreservation;

import org.testng.annotations.Test;


import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.pseudofunc.pages.flightreservation.FlightConfirmationPage;
import com.pseudofunc.pages.flightreservation.FlightSearchPage;
import com.pseudofunc.pages.flightreservation.FlightsSelectionPage;
import com.pseudofunc.pages.flightreservation.RegistrationConfirmationPage;
import com.pseudofunc.pages.flightreservation.RegistrationPage;
import com.pseudofunc.tests.AbstractTest;
import com.pseudofunc.tests.flightreservation.model.FlightReservationTestData;
import com.pseudofunc.util.JSONUtil;



/*
 * just a note: i was getting one issue here. Tesng: nosuchmethodexception 
 * 
 * solution :
 * Adding the TestNG Library into the project. Steps are:

    Right Click on the Project
    Build Path
    Configure Build Path
    Libraries (Tab)
    Go to "Add Library" and click the button.
    Add the 'TestNG' Library and click Ok/Next.
    TestNG Library will be added to your project.

then I ran and it worked fine.	
 */


public class FlightReservationTest extends AbstractTest{

	
	private FlightReservationTestData flightReservationTestData; 
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setParams(String testDataPath) {
		
		this.flightReservationTestData = JSONUtil.getTestData(testDataPath, FlightReservationTestData.class);
		
	}
		
	@Test
	public void userRegistrationTest() {
		
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
		Assert.assertTrue(registrationPage.isAt());
		
		registrationPage.enterUserDetails(flightReservationTestData.firstName(), flightReservationTestData.lastName());
		registrationPage.enterUserCredentials(flightReservationTestData.email(), flightReservationTestData.password());
		registrationPage.enterAddress(flightReservationTestData.street(), flightReservationTestData.city(), flightReservationTestData.zip());
		
		registrationPage.register();
		
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registrationConfirmationPage.isAt());
		Assert.assertEquals(registrationConfirmationPage.getFirstName(), flightReservationTestData.firstName());
		registrationConfirmationPage.goToFlightSearch();
				
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		
		FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
		Assert.assertTrue(flightSearchPage.isAt());
		
		flightSearchPage.selectPassengers(flightReservationTestData.passengersCount());
		flightSearchPage.searchFlights();
				
	}
	
	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectionTest() {
		
		FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
		Assert.assertTrue(flightsSelectionPage.isAt());
		
		flightsSelectionPage.selectFlights();
		flightsSelectionPage.confirmFlights();
	}
	
	@Test(dependsOnMethods = "flightSelectionTest" )
	public void flightReservationConfirmationTest() {
		
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmationPage.isAt());
		
		Assert.assertEquals(flightConfirmationPage.getPrice(), flightReservationTestData.expectedPrice());		
	}
}
