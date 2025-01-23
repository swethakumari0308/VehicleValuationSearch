package com.vehicle.valuation.search.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Webdriver factory is used to load config properties and launch Chrome browser with default values.
 */
public class WebDriverFactory {

    public static WebDriver webDriver;
    public static Properties properties;

    public WebDriverFactory()  {
        properties = new Properties();
        try {
            ClassLoader classLoader = WebDriverFactory.class.getClassLoader();
            FileInputStream fileinputStream = new FileInputStream(new File(classLoader.getResource("config.properties").getFile()).getAbsolutePath());
            properties.load(fileinputStream);
        } catch (Exception e){
            e.getMessage();
        }

    }
    public static void initializeBrowser() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(properties.getProperty("website"));
    }

    public static void close(){
        webDriver.quit();
    }
}
