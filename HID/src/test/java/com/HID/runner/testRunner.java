package com.HID.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.HID.reports.ExtentReportListener;
import com.HID.utils.DriverManager;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.plugin.ConcurrentEventListener;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
    glue = "com.HID.tests",
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class testRunner {
	@BeforeClass
    public static void setUp() {
        DriverManager.initializeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        DriverManager.quitDriver();
    }
}
