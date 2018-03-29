package com.foundation.core;

import com.foundation.core.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.DriverManager;

public class Hooks {

    static WebDriver driver = DriverConfig.getDriver("browser");

    @Before
    public static void setUp() {
        driver.get(System.getenv("website"));
        driver.manage().window().maximize();
    }

    @After
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

}
