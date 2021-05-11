package com.zhangmen.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Mouse_Operation extends Browser_operation {

    // 鼠标悬浮指定元素并点击
    public static void moveToElementBy(By by) {
        Actions actions = new Actions(driver);
        WebElement webElement = WebElementUtil.findElement(by);
        actions.moveToElement(webElement).perform();
    }


    // 鼠标右键点击
    public static void RightClickWebElement(By by) {
        Actions actions = new Actions(driver);
        WebElement webElement = WebElementUtil.findElement(by);
        actions.contextClick(webElement).perform();
    }

    // 鼠标双击
    public static void DoubleClickWebElement(By by) {
        Actions actions = new Actions(driver);
        WebElement webElement = WebElementUtil.findElement(by);
        actions.doubleClick(webElement).perform();
    }
}
