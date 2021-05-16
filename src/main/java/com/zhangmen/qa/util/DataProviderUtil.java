package com.zhangmen.qa.util;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 数据驱动，主DataProvider
 * barry
 */
public class DataProviderUtil {

    /**
     * 根据方法名进行获取，需要每一个方法名都不一样
     * @param method
     * @return
     * @throws IOException
     */

    @DataProvider(name = "testCsvData")
    public Object[][] testCsvData(Method method) throws IOException {
        return CsvReaderUtil.getTestData("src/main/resources/testdata/"+method.getName()+".csv");
    }

}
