package com.HID.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	
	////////////WebElements////////////////////
	@FindBy(how=How.NAME, using = "username")
    private WebElement fldUserName;
	
	@FindBy(how=How.ID, using = "mat-input-1")
    private WebElement fldPassword;
	
	@FindBy(how=How.XPATH, using = "//span[text()=' NEXT ']")
    private WebElement btnNext;
	
	@FindBy(how=How.XPATH, using = "//span[text()=' SIGN IN ']")
    private WebElement btnSignIn;
	
	///////////Reusables/////////////////////

	public void enterUsername(String username) {
		fldUserName.sendKeys(username);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btnNext.click();
	}

	public void enterPassword(String password) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fldPassword.sendKeys(password);
		btnSignIn.click();
	}
}
