package com.HID.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static Properties properties;

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initializeWebDriver() {
        if (driver.get() == null) {
            try {
                properties = new Properties();
                FileInputStream inputStream = new FileInputStream("C:\\Users\\dines\\OneDrive\\Desktop\\ECLIPSE_FEB_2023\\HID\\src\\main\\java\\com\\HID\\utils\\config.properties");
                properties.load(inputStream);

                String browser = properties.getProperty("browser");
                if (browser.equalsIgnoreCase("chrome")) {
                	WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                } else if (browser.equalsIgnoreCase("firefox")) {
                	WebDriverManager.firefoxdriver().setup();
                	driver.set(new FirefoxDriver());
                } else if (browser.equalsIgnoreCase("ie")) {
                	WebDriverManager.iedriver().setup();
                	driver.set(new InternetExplorerDriver());
                } else {
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
                getDriver().manage().window().maximize();
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
        	driver.get().quit();
        	driver.remove();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
    
    public static String getUrl() {
        return properties.getProperty("url");
    }
}
