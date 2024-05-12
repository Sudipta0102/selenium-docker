package com.pseudofunc.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.pseudofunc.listener.TestListener;
import com.pseudofunc.util.Config;
import com.pseudofunc.util.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public class AbstractTest {

	private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
	protected WebDriver driver;

	@BeforeSuite
	public void setupConfig(){
		// it will initialize or load properties file once per suite
		Config.initialize();
	}
	
	@BeforeTest
	public void setDriver(ITestContext context) throws MalformedURLException, URISyntaxException {
		// here we are going to access selenium-grid-enabled tag from maven surefire plugin in pom.xml
		// the code should be like System.getProperty("selenium-grid-enabled").equals("true") or false,
		// But System.getProperty always returns a String, so instead of that, we are going to use,
		// Boolean.getBoolean("selenium-grid-enabled")
//		if(Boolean.getBoolean("selenium-grid-enabled")){
//			this.driver = getRemoteDriver();
//		}else {
//			this.driver = getLocalChromeDriver();
//		}

		this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?getRemoteDriver():getLocalChromeDriver();
		context.setAttribute(Constants.DRIVER, this.driver);
	}

	private WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
		// http://localhost:4444/wd/hub
//		Capabilities capabilities;
//		// here we are going to access browser tag from maven surefire plugin in pom.xml
//		if(System.getProperty("browser").equalsIgnoreCase("chrome")){
//			capabilities = new ChromeOptions();
//		}else{
//			capabilities = new FirefoxOptions();
//		}
		Capabilities capabilities = new ChromeOptions();
		if(Constants.BROWSER_FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
			capabilities = new FirefoxOptions();

		// now to change the hard-coded URL
		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		// default.properties file has the value as http://%s:4444/wd/hub
		// in the following line, %s will be replaced by the value of hubHost.
		String url = String.format(urlFormat, hubHost);

		// print
		log.info("grid-url : {}", url);

		//return new RemoteWebDriver((new URI("http://localhost:4444/wd/hub")).toURL(), capabilities);
		return new RemoteWebDriver((new URI(url)).toURL(), capabilities);
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

//	@AfterMethod
//	public void sleep(){
//		// this is to watch the live video recording when running with grid, otherwise
//		// the execution will be over in a flash.
//		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//	}
}
