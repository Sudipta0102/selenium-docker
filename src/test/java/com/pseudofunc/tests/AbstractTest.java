package com.pseudofunc.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
	
	protected WebDriver driver;
	
	@BeforeTest
	public void setDriver() {
		
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.setBinary("C:\\Program Files\\Google\\chrome-win64\\chrome.exe");
		
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver(chromeOptions);
		
	}
	
	@AfterTest
	public void quitDriver() {
		
		this.driver.quit();
		
	}

}
