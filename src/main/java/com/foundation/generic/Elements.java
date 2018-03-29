package com.foundation.generic;

import com.foundation.core.DriverConfig;
import com.foundation.utils.Utils;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Elements extends DriverConfig {

    public static WebElement findElement(String pageElement)
    {
        String pageName = pageElement.split("\\.")[0];
        String elementName = pageElement.split("\\.")[1];
        JSONObject js = new JSONObject();
        js = Utils.readJson(pageName);
        ArrayList<String> temp = Utils.elementLocators(js, elementName);
        By by = Utils.selector(temp.get(0),temp.get(1));
        List<WebElement> elements = DriverConfig.driver.findElements(by);
        WebDriverWait wait = new WebDriverWait(driver,20);
        if (elements.isEmpty())
        {
            System.out.println("Element not found");
        }
        return elements.get(0);

    }








}
