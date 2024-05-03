package com.pseudofunc.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pseudofunc.pages.AbstractPage;

public class LogInPage extends AbstractPage{

	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(className="custom-control-label")
	private WebElement rememberMe_checkbox;
	
	@FindBy(id="login")
	private WebElement login_button;
	
	
	public LogInPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {		
		this.wait.until(ExpectedConditions.visibilityOf(this.login_button));		
		return this.login_button.isDisplayed();
	}
	
	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void userCredentials(String username, String password) {
		
		this.username.sendKeys(username);
		this.password.sendKeys(password);
	}

	public void rememberMe() {
		this.rememberMe_checkbox.click();
	}
	
	public void login() {
		this.login_button.click();
	}
	
}
