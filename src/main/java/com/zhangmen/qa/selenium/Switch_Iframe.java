package com.zhangmen.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Switch_Iframe extends Browser_operation {

    //根据iframe id 定位
    public static void switchToFrameById(String iframeId) {
        driver.switchTo().frame(iframeId);
    }

    //根据iframe name 定位
    public static void switchToFrameByName(String iframeName) {
        driver.switchTo().frame(iframeName);
    }

    //根据iframe 下标 定位 0代表该页面的第一个<iframe>标签
    public static void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    //用定位器定位 By.xpth("")等
    public static void switchToIframeByElement(By by) {
        WebElement webElement = driver.findElement(by);
        driver.switchTo().frame(webElement);
    }


    //返回上一层iframe
    public static void switchToParentIframe() {
        driver.switchTo().parentFrame();
    }

    //返回最外层
    public static void switchToDefaultContentIframe() {
        driver.switchTo().defaultContent();
    }

}
