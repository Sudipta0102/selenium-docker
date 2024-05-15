package com.pseudofunc.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * 1. This is generic for every page class that will extend this AbstractPage class. 
 *  
 * 		a. all the pages contain one or more private field. So if one creates the instance of any page classes,
 * by default everything will be null. To interact with these private fields (mostly webelement), a constructor needs to be created
 * for every page class through which we are going to accept the driver.
 * 		i.e. public pgeConstructor(WebDriver driver){} 
 *   	
 *   	b. Then, we are going to need that driver information throughout the framework. FOr that, we also need one
 * private WebDriver instance per page 
 * 		i.e. private WebDriver driver;
 * with which we can store the driver reference.
 * 		i.e this.driver = driver;
 * 
 *    	c. Now comes the wait. At the time of initializing the driver, one other WebDriverWait is initialized for the
 * every page. This is basically needed for the delay to load respective pages.
 * 		i.e this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
 * 
 *   	d. Lastly, 
 *   	PageFactory.initElements(driver, this);
 * By this, we are saying using driver(1st arg), initialize all the private elements the page has. Basically, it will 
 * create a proxy object for every element in the class and then at runtime, they will be initialized and used.  	
 * 
 *  2. this abstract page will be extended by every page class.
 *  
 *  	a. we need this to get rid of code duplication in terms of constructor. 
 *  	
 *  	b. this will be used in every page class in this way.
 *  	public class AnyPageClass extends AbstractPage{
 *  		
 *  		// this id the constructor. 
 *  		public AnyPageClass(WebDriver driver){
 *				super(driver);
 *			}  
 *  
 *  	}
 *  
 *  
 */

public abstract class AbstractPage {

	protected final WebDriver driver;
	protected final WebDriverWait wait;
	
	public AbstractPage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
		
	}
	
	// this abstract has to be implemented by all the page classes. This is to make sure we have arrived at the page
	// before any test flow. Tests have to designed like this.
	public abstract boolean isAt();
	
}
