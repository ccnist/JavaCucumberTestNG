package com.foundation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverConfig {

    public static WebDriver driver = null;

    public static WebDriver getDriver(String type) {
//        AppiumDriverLocalService appiumService = AppiumDriverLocalService
//                .buildService(new AppiumServiceBuilder().usingAnyFreePort()
//                        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
//                        .withAppiumJS(new File("C:/Program Files/Appium/node_modules/appium/bin/appium.js")));
//        appiumService = new AppiumServiceBuilder().usingPort(4000).build();
//        appiumService.start();
//        String appiumServiceUrl = appiumService.getUrl().toString();
//        System.out.println("Appium Service Address : - " + appiumServiceUrl);
//        DesiredCapabilities capabilities=DesiredCapabilities.android();
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"NaaPhone");
//        capabilities.setCapability(MobileCapabilityType.VERSION,"5.0.1");
       if (driver == null)
        {
            if(type.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "D://Softwares//chromedriver.exe");
                driver = new ChromeDriver();
            }else if(type.equals("mobile")) {
            //    driver = new AndroidDriver(capabilities);
            }
        }
        return driver;
    }









}
