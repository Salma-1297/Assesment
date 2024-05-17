package com.HID.tests;

import org.openqa.selenium.WebDriver;

import com.HID.pages.LoginPage;
import com.HID.utils.DriverManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class stepDefinitions {
	private WebDriver driver;
    private LoginPage loginPage;
    
    public stepDefinitions() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
    }
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get(DriverManager.getUrl());
    }
    
    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

}
