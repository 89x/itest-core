package com.zhangmen.qa.selenium;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebElementUtil extends Browser_operation {

    private static WebDriver driver = null;
    private static Select select = null;
    private static Alert alert = null;
    private static WebElement element = null;
    private static List<WebElement> elementList = null;
    private static long timeOutInSeconds = 10;

    //findElement
    public static WebElement findElement(final By by) {
        WebElement webelement = null;
        try {
            webelement = new WebDriverWait(driver, 20).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver webDriver) {
                    return driver.findElement(by);
                }
            });
        } catch (Exception e) {
            System.out.println("元素：" + by + "查找超时！！！");
            e.printStackTrace();
        }
        return webelement;
    }

    //findElements
    public static List<WebElement> findElements(final By by) {
        List<WebElement> WebElement = null;
        try {
            WebElement = new WebDriverWait(driver, 20).until(new ExpectedCondition<List<WebElement>>() {
                @NullableDecl
                public List<WebElement> apply(WebDriver webDriver) {
                    return driver.findElements(by);
                }
            });
        } catch (Exception e) {
            System.out.println("元素：" + by + "查找超时！！！");
            e.printStackTrace();
        }
        return WebElement;
    }
    /**
     *     查找元素
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     */
    public WebElement findElement(String by, String byValue) {
        try {
            switch (by) {
                case "id":
                    element = driver.findElement(By.id(byValue));
                    break;
                case "name":
                    element = driver.findElement(By.name(byValue));
                    break;
                case "class":
                    element = driver.findElement(By.className(byValue));
                    break;
                case "tag":
                    element = driver.findElement(By.tagName(byValue));
                case "link":
                    element = driver.findElement(By.linkText(byValue));
                    break;
                case "partiallinktext":
                    element = driver.findElement(By.partialLinkText(byValue));
                case "css":
                    element = driver.findElement(By.cssSelector(byValue));
                    break;
                case "xpath":
                    element = driver.findElement(By.xpath(byValue));
                    break;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("没有找到元素：" + byValue);
        }
        return element;
    }
    /**
     *     查找一组元素
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     */
    public List<WebElement> findElements(String by, String byValue) {
        try {
            switch (by) {
                case "id":
                    elementList = driver.findElements(By.id(byValue));
                    break;
                case "name":
                    elementList = driver.findElements(By.name(byValue));
                    break;
                case "class":
                    elementList = driver.findElements(By.className(byValue));
                    break;
                case "tag":
                    elementList = driver.findElements(By.tagName(byValue));
                case "link":
                    elementList = driver.findElements(By.linkText(byValue));
                    break;
                case "partiallinktext":
                    elementList = driver.findElements(By.partialLinkText(byValue));
                case "css":
                    elementList = driver.findElements(By.cssSelector(byValue));
                    break;
                case "xpath":
                    elementList = driver.findElements(By.xpath(byValue));
                    break;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("没有找到元素：" + byValue);
        }
        return elementList;
    }
    /**
     *     获取单个元素
     */
    public WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
    public WebElement findElementByTag(String tag) {
        return driver.findElement(By.tagName(tag));
    }
    public WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }
    public WebElement findElementByClassName(String name) {
        return driver.findElement(By.className(name));
    }
    public WebElement findElementByText(String text) {
        return driver.findElement(By.linkText(text));
    }
    public WebElement findElementByPartialText(String text) {
        return driver.findElement(By.partialLinkText(text));
    }
    public WebElement findElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    /**
     *    获取多个元素
     */
    public List<WebElement> findElementsByClassName(String className) {
        return driver.findElements(By.className(className));
    }
    public List<WebElement> findElementsByText(String text) {
        return driver.findElements(By.linkText(text));
    }
    public List<WebElement> findElementsByPartialText(String text) {
        return driver.findElements(By.partialLinkText(text));
    }
    public List<WebElement> findElementsById(String id) {
        return driver.findElements(By.id(id));
    }
    public List<WebElement> findElementsByTag(String tag) {
        return driver.findElements(By.tagName(tag));
    }

    /**
     *    获取一组元素中的指定元素
     */
    public WebElement FindByElements(By by, int index) {
        WebElement element = null;
        if (this.elementsExists(by)) {
            element = driver.findElements(by).get(index);
        }
        return element;
    }

    /**
     *     查找元素并点击
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     */
    public boolean findElementClick(String by, String byValue) {
        try {
            switch (by) {
                case "id":
                    driver.findElement(By.id(byValue)).click();
                    return true;
                case "name":
                    driver.findElement(By.name(byValue)).click();
                    return true;
                case "class":
                    driver.findElement(By.className(byValue)).click();
                    return true;
                case "tag":
                    driver.findElement(By.tagName(byValue)).click();
                case "link":
                    driver.findElement(By.linkText(byValue)).click();
                    return true;
                case "partiallinktext":
                    driver.findElement(By.partialLinkText(byValue)).click();
                case "css":
                    driver.findElement(By.cssSelector(byValue)).click();
                    return true;
                case "xpath":
                    driver.findElement(By.xpath(byValue)).click();
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + by + "属性值为：" + byValue + "  的元素或者该元素无法点击****");
            return false;
        }
    }

    /**
     *    定位元素并点击
     */
    public void findElementByIdAndClick(String id) {
        driver.findElement(By.id(id)).click();
    }
    public void findElementByNameAndClick(String name) {
        driver.findElement(By.name(name)).click();
    }
    public void findElementByTextAndClick(String text) {
        driver.findElement(By.linkText(text)).click();
    }
    public void findElementByPartiaTextAndClick(String text) {
        driver.findElement(By.partialLinkText(text)).click();
    }
    public void findElementByXpathAndClick(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }
    public void findElementByClassNameAndClick(String name) {
        driver.findElement(By.className(name)).click();
    }

    /**
     *     查找元素并清除文本内容
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     */
    public boolean findElementClear(String by, String byValue) {
        try {
            switch (by) {
                case "id":
                    driver.findElement(By.id(byValue)).clear();
                    return true;
                case "name":
                    driver.findElement(By.name(byValue)).clear();
                    return true;
                case "class":
                    driver.findElement(By.className(byValue)).clear();
                    return true;
                case "tag":
                    driver.findElement(By.tagName(byValue)).clear();
                    return true;
                case "link":
                    driver.findElement(By.linkText(byValue)).clear();
                    return true;
                case "partiallinktext":
                    driver.findElement(By.partialLinkText(byValue)).clear();
                    return true;
                case "css":
                    driver.findElement(By.cssSelector(byValue)).clear();
                    return true;
                case "xpath":
                    driver.findElement(By.xpath(byValue)).clear();
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + by + "属性值为：" + byValue + "  的元素或者该元素没有输入值****");
            return false;
        }
    }
    /**
     *     查找元素并输入值
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     * @param key     填写要输入的值        例如：zhangsan
     */
    public boolean findElementSendKeys(String by, String byValue, String key) {
        try {
            switch (by) {
                case "id":
                    driver.findElement(By.id(byValue)).sendKeys(key);
                    return true;
                case "name":
                    driver.findElement(By.name(byValue)).sendKeys(key);
                    return true;
                case "class":
                    driver.findElement(By.className(byValue)).sendKeys(key);
                    return true;
                case "tag":
                    driver.findElement(By.tagName(byValue)).sendKeys(key);
                    return true;
                case "link":
                    driver.findElement(By.linkText(byValue)).sendKeys(key);
                    return true;
                case "partiallinktext":
                    driver.findElement(By.partialLinkText(byValue)).sendKeys(key);
                case "css":
                    driver.findElement(By.cssSelector(byValue)).sendKeys(key);
                    return true;
                case "xpath":
                    driver.findElement(By.xpath(byValue)).sendKeys(key);
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + by + "属性值为：" + byValue + "    的元素或者该元素无法输入****");
            return false;
        }
    }
    /**
     *     查找元素并输入值
     * @param by      传入一个类型        例如：name
     * @param byValue 传入一个类型值       例如：username
     * @param key     填写要输入的值        例如：zhangsan
     */
    public boolean findElementClearAndSendKeys(String by, String byValue, String key) {
        try {
            switch (by) {
                case "id":
                    findElementClear(by,byValue);
                    driver.findElement(By.id(byValue)).sendKeys(key);
                    return true;
                case "name":
                    findElementClear(by,byValue);
                    driver.findElement(By.name(byValue)).sendKeys(key);
                    return true;
                case "class":
                    findElementClear(by,byValue);
                    driver.findElement(By.className(byValue)).sendKeys(key);
                    return true;
                case "tag":
                    findElementClear(by,byValue);
                    driver.findElement(By.tagName(byValue)).sendKeys(key);
                    return true;
                case "link":
                    findElementClear(by,byValue);
                    driver.findElement(By.linkText(byValue)).sendKeys(key);
                    return true;
                case "partiallinktext":
                    findElementClear(by,byValue);
                    driver.findElement(By.partialLinkText(byValue)).sendKeys(key);
                case "css":
                    findElementClear(by,byValue);
                    driver.findElement(By.cssSelector(byValue)).sendKeys(key);
                    return true;
                case "xpath":
                    findElementClear(by,byValue);
                    driver.findElement(By.xpath(byValue)).sendKeys(key);
                    return true;
                default:
                    throw new RuntimeException("输入的定位类型未在程序中定义，类型为：" + byValue);
            }
        } catch (Exception e) {
            System.out.println("*****没有找到元素,类型为：:" + by + "属性值为：" + byValue + "    的元素或者该元素无法输入****");
            return false;
        }
    }

    /**
     *    定位元素并清空文本内容，输入相应的值
     */
    public void findElementByIdAndClearSendkeys(String id, String text) {
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(text);
    }
    public void findElementByIdAndClearSendkeys(String id, int num) {
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(num + "");
    }
    public void findElementByNameAndClearSendkeys(String name, String text) {
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys(text);
    }
    public void findElementByNameAndClearSendkeys(String name, int num) {
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys(num + "");
    }
    public void findElementByXpathAndClearSendkeys(String xpath, String text) {
        findElementByXpath(xpath).clear();
        findElementByXpath(xpath).sendKeys(text);
    }
    public void findElementByXpathAndClearSendkeys(String xpath, int num) {
        findElementByXpath(xpath).clear();
        findElementByXpath(xpath).sendKeys(num + "");
    }
    public void findElementByClassnameAndClearSendkeys(String classname, String text) {
        driver.findElement(By.className(classname)).clear();
        driver.findElement(By.className(classname)).sendKeys(text);
    }
    public void findElementByClassnameAndClearSendkeys(String classname, int num) {
        driver.findElement(By.className(classname)).clear();
        driver.findElement(By.className(classname)).sendKeys(num + "");
    }

    /**
     *    定位元素，并获取其文本内容
     */
    public String getTextByXpath(String xpath) {
        return findElementByXpath(xpath).getText();
    }
    public String getTextByClassName(String name) {
        return findElementByClassName(name).getText();
    }
    public String getTextById(String id) {
        return findElementById(id).getText();
    }
    public String getTextByName(String name) {
        return findElementByName(name).getText();
    }

    /**
     *     定位元素，并指定点击次数(连续点击)
     */
    public boolean clickById(String id, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                driver.findElement(By.id(id)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByXpath(String xpath, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                driver.findElement(By.xpath(xpath)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clickByCss(String css, int clickCount) {
        try {
            for (int i = 0; i < clickCount; i++) {
                driver.findElement(By.cssSelector(css)).click();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // 判断元素是否存在
    public boolean exists(By selector) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);// 设置查询组件等待时间
        try {
            driver.findElement(selector);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 设置查询组件等待时间
            return true;
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 设置查询组件等待时间
            return false;
        }
    }
    /**
     *     判断一个元素是否存在
     */
    public boolean isElementExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     *    判断一组元素是否存在
     */
    public boolean elementsExists(By by) {
        return (driver.findElements(by).size() > 0) ? true : false;
    }
    /**
     *     1、指定时间内等待直到页面包含文本字符串
     * @param text        期望出现的文本
     * @param seconds     超时时间
     * @return Boolean     检查给定文本是否存在于指定元素中, 超时则捕获抛出异常TimeoutException并返回false
     */
    public static Boolean waitUntilPageContainText(String text, long seconds) {
        try {
            return new WebDriverWait(driver, seconds)
                    .until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("body")), text));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *     2、默认时间等待直到页面包含文本字符串
     * @param text 期望出现的文本
     * @return Boolean 检查给定文本是否存在于指定元素中, 超时则捕获抛出异常TimeoutException并返回false
     */
    public static Boolean waitUntilPageContainText(String text) {
        try {
            return new WebDriverWait(driver, timeOutInSeconds)
                    .until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("body")), text));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     *    判断元素是否显示
     */
    public boolean getDisplayStatById(String id) {
        return driver.findElement(By.id(id)).isDisplayed();
    }
    public boolean getDisplayStatByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }
    public boolean getDisplayStatByCss(String css) {
        return driver.findElement(By.cssSelector(css)).isDisplayed();
    }
    /**
     *    判断元素是否可写
     */
    public boolean getEnableStatById(String id) {
        return driver.findElement(By.id(id)).isEnabled();
    }
    public boolean getEnableStatByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath)).isEnabled();
    }
    public boolean getEnableStatByCss(String css) {
        return driver.findElement(By.cssSelector(css)).isEnabled();
    }
    /**
     *    判断元素是否选中
     */
    public boolean getSelectStatById(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }
    public boolean getSelectStatByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath)).isSelected();
    }
    public boolean getSelectStatByCss(String css) {
        return driver.findElement(By.cssSelector(css)).isSelected();
    }

    /**
     *     获取当前焦点所在页面元素的属性值(name,value,id,src等等)
     */
    public String getFocusAttributeValue(String attribute) {
        String value = "";
        try {
            Thread.sleep(333);
        } catch (Exception e) {
            e.printStackTrace();
        }
        value = driver.switchTo().activeElement().getAttribute(attribute);
        System.out.println("The focus Element's " + attribute + "attribute value is>>" + value);
        return value;
    }

    // 等待元素可用再点击
    public void waitForEnabledByXpathAndClick(String xpath,int clickCount) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElementByXpath(xpath).isEnabled() && findElementByXpath(xpath).isDisplayed()) {
                clickByXpath(xpath,clickCount);
                key = false;
            } else {
                sleep(0);
            }
        }
    }
    // 自定义等待时间
    public static void sleep(int key) throws InterruptedException {
        switch (key) {
            case 0:
                Thread.sleep(500);
                break;
            case 1:
                Thread.sleep(2000);
                break;
            case 2:
                Thread.sleep(5000);
                break;
            default:
                System.out.println("错误");
                break;
        }
    }
    // 根据id获取下拉框，根据index选择选项
    public void findSelectByIdAndSelectByIndex(String id, int index) {
        Select select = new Select(findElementById(id));
        select.selectByIndex(index);
    }
    // 根据id获取下拉框，根据value选择选项
    public void findSelectByIdAndSelectByValue(String id, String value) {
        Select select = new Select(findElementById(id));
        select.selectByValue(value);
    }
    // 根据id获取下拉框，根据text选择选项
    public void findSelectByIdAndSelectByText(String id, String text) {
        Select select = new Select(findElementById(id));
        select.selectByVisibleText(text);
    }

    // 根据classname获取下拉框，根据text选择选项
    public void findSelectByClassNameAndSelectByText(String name, String text) {
        Select select = new Select(findElementByClassName(name));
        select.selectByVisibleText(text);
    }
    // 根据classname获取下拉框，根据Value选择选项
    public void findSelectByClassNameAndSelectByValue(String name, String value) {
        Select select = new Select(findElementByClassName(name));
        select.selectByValue(value);
    }
    // 根据classname获取下拉框，根据index选择选项
    public void findSelectByClassNameAndSelectByIndex(String name, int index) {
        Select select = new Select(findElementByClassName(name));
        select.selectByIndex(index);
    }

    // 根据name获取下拉框，根据text选择选项
    public void findSelectByNameAndSelectByText(String name, String text) {
        Select select = new Select(findElementByName(name));
        select.selectByVisibleText(text);
    }
    // 根据name获取下拉框，根据Value选择选项
    public void findSelectByNameAndSelectByValue(String name, String value) {
        Select select = new Select(findElementByName(name));
        select.selectByValue(value);
    }
    // 根据name获取下拉框，根据index选择选项
    public void findSelectByNameAndSelectByIndex(String name, int index) {
        Select select = new Select(findElementByName(name));
        select.selectByIndex(index);
    }

    /**
     *     定位select并选中对应text的option
     * @param locator
     * @param text
     */
    public static void selectByText(By locator, String text) {
        select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    /**
     *     定位select并选中对应index的option
     * @param locator
     * @param index
     */
    public static void selectByIndex(By locator, int index) {
        select = new Select(driver.findElement(locator));
        select.selectByIndex(index);
    }

    /**
     *    定位select并选中对应value值的option
     * @param locator  定位select的选择器
     * @param value       option 中的value值
     */
    public static void selectByValue(By locator, String value) {
        select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }
    // 判断是否有弹框
    public boolean isAlertPresent() {
        try {
            alert = driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }
    // 接受弹出框
    public void acceptAlert() {
        if (this.isAlertPresent()) {
            alert.accept();
        }
    }
    // 取消弹出框
    public void dimissAlert() {
        if (this.isAlertPresent()) {
            alert.dismiss();
        }
    }
    // 获取弹出内容
    public String getAlertText() {
        String text = null;
        if (this.isAlertPresent()) {
            text = alert.getText();
        } else {
            // todo:log;
        }
        return text;
    }
    // 弹出对话框输入文本字符串
    public void inputTextToAlert(String text) {
        if (this.isAlertPresent()) {
            alert.sendKeys(text);
        } else {
            // todo:log;
        }
    }
    /**
     *     切换到当前页面
     */
    public static void switchToCurrentPage() {
        String handle = driver.getWindowHandle();
        for (String tempHandle : driver.getWindowHandles()) {
            if (tempHandle.equals(handle)) {
                driver.close();
            } else {
                driver.switchTo().window(tempHandle);
            }
        }
    }
    /**
     *     切换到指定title的窗口
     */
    public void switchToWindow(String windowTtitle) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handler : windowHandles) {
            driver.switchTo().window(handler);
            String title = driver.getTitle();
            if (windowTtitle.equals(title)) {
                break;
            }
        }
    }

    /**
     *     切换至父级frame
     */
    public static void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    /**
     *     切换默认最外层frame或者窗口
     * @return 这个驱动程序聚焦在顶部窗口/第一个frame上
     */
    public static void switchToDefault() {
        driver.switchTo().defaultContent();
    }
    /**
     *     获取当前域所有的cookies
     * @return Set<Cookie> 当前的cookies集合
     */
    public static Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }
    // 输出cookies信息
    public void outputCookie() {
        Set<Cookie> cookie = driver.manage().getCookies();
        System.out.println(cookie);
    }
    //添加cookie信息
    public void addCookie(Map<String, String> args) {
        Set<String> keys = args.keySet();
        for (String key : keys) {
            driver.manage().addCookie(new Cookie(key, args.get(key)));
        }
    }
    /**
     *     用给定的name和value创建默认路径的Cookie并添加, 永久有效
     * @param name
     * @param value

     */
    public static void addCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    /**
     *     用给定的name和value创建指定路径的Cookie并添加, 永久有效
     * @param name  cookie名称
     * @param value cookie值
     * @param path  cookie路径
     */
    public static void addCookie(String name, String value, String path) {
        driver.manage().addCookie(new Cookie(name, value, path));
    }
    /**
     *     根据cookie名称删除cookie
     * @param name cookie的name值
     */
    public static void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }
    /**
     *     删除当前域的所有Cookie
     */
    public static void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     *     根据名称获取指定cookie
     * @param name cookie名称
     * @return Map<String, String>, 如果没有cookie则返回空, 返回的Map的key值如下:
     *         <ul>
     *         <li><tt>name</tt> <tt>cookie名称</tt>
     *         <li><tt>value</tt> <tt>cookie值</tt>
     *         <li><tt>path</tt> <tt>cookie路径</tt>
     *         <li><tt>domain</tt> <tt>cookie域</tt>
     *         <li><tt>expiry</tt> <tt>cookie有效期</tt>
     *         </ul>
     */
    public static Map<String, String> getCookieByName(String name) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        if (cookie != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", cookie.getName());
            map.put("value", cookie.getValue());
            map.put("path", cookie.getPath());
            map.put("domain", cookie.getDomain());
            map.put("expiry", cookie.getExpiry().toString());
            return map;
        }
        return null;
    }





}
