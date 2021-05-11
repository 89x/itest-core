package com.zhangmen.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class ActionsUtil extends Browser_operation {

    //点击动作click
    public static void click(By by) {
        WebElementUtil.findElement(by).click();
    }

    //文本框输入
    public static void sendText(By by, String text) {
        WebElementUtil.findElement(by).sendKeys(text);
    }

    //清空文本框
    public static void clear(By by) {
        WebElementUtil.findElement(by).clear();
    }

    //获取文本框中内容
    public static String getText(By by) {
        String text = WebElementUtil.findElement(by).getText();
        return text;
    }

    //获取多个内容
    public static ArrayList getTexts(By by) {
        ArrayList arrayList = new ArrayList();
        List<WebElement> list = WebElementUtil.findElements(by);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).getText();
            arrayList.add(text);
        }
        return arrayList;
    }

    //鼠标双击
    public static void doubleClick(WebDriver driver, By by) {
        Actions actions = new Actions(driver);
        WebElement ement = WebElementUtil.findElement(by);
        actions.doubleClick(ement).perform();
    }

}

