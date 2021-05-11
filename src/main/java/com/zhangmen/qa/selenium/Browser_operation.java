package com.zhangmen.qa.selenium;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser_operation {
    public static WebDriver driver;
    //打开浏览器
    public static WebDriver openBrowser(String browser) {
        if (browser.equals("chrome")) {
            driver =new ChromeDriver();
        } else {
            System.out.println("你的浏览器出错了" + browser);
        }
        return driver;
    }

    // 刷新浏览器
    public static void refresh() {
        driver.navigate().refresh();
    }

    // 浏览器前进
    public static void forward() {
        driver.navigate().forward();
    }

    // 浏览器后退
    public static void back() {
        driver.navigate().back();
    }

    //关闭浏览器  关闭窗口不关闭后台进程
    public static void close() {
        driver.close();
    }

    //关闭浏览器  关闭窗口 关闭后台进程
    public static void quit() {
        driver.quit();
    }

    //浏览器最大化
    public static void maximize() {
        driver.manage().window().maximize();
    }

    //设置浏览器大小
    public static void manage(int x, int y) {
        Dimension dimension = new Dimension(x, y);
        driver.manage().window().setSize(dimension);
    }

    //获取当前页面URL
    public static String getUrl() {
        return driver.getCurrentUrl();

    }

    //获取当前页面Title
    public static String getTitle() {
        return driver.getTitle();
    }
}
