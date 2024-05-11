package com.pseudofunc.tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AbstractTest {
	
	protected WebDriver driver;
	
	@BeforeTest
	public void setDriver() throws MalformedURLException, URISyntaxException {
		// here we are going to access selenium-grid-enabled tag from maven surefire plugin in pom.xml
		// the code should be like System.getProperty("selenium-grid-enabled").equals("true") or false,
		// But System.getProperty always returns a String, so instead of that, we are going to use,
		// Boolean.getBoolean("selenium-grid-enabled")
		if(Boolean.getBoolean("selenium-grid-enabled")){
			this.driver = getRemoteDriver();
		}else {
			this.driver = getLocalChromeDriver();
		}
	}

	private WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
		// http://localhost:4444/wd/hub
		Capabilities capabilities;
		// here we are going to access browser tag from maven surefire plugin in pom.xml
		if(System.getProperty("browser").equalsIgnoreCase("chrome")){
			capabilities = new ChromeOptions();
		}else{
			capabilities = new FirefoxOptions();
		}
		return new RemoteWebDriver((new URI("http://localhost:4444/wd/hub")).toURL(), capabilities);
	}

	private WebDriver getLocalChromeDriver(){
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.setBinary("C:\\Program Files\\Google\\chrome-win64\\chrome.exe");

		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(chromeOptions);
	}
	
	@AfterTest
	public void quitDriver() {
		
		this.driver.quit();
		
	}

}
