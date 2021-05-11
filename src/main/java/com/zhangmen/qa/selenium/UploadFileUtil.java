package com.zhangmen.qa.selenium;

import org.openqa.selenium.By;

public class UploadFileUtil extends Browser_operation {

    /**
     * 上传文件
     * filePath  文件路径
     */
    public static void uploadFile(By locator, String filePath) {
        driver.findElement(locator).sendKeys(filePath);
    }
}
