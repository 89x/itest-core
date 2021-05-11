package com.zhangmen.qa.util;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public class DataProviderUtil {

    /**
     * csv 文件名字建议用类名+方法名来编辑，后期会改为自动获取
     * @param method
     * @return
     * @throws IOException
     */

    @DataProvider(name = "testCsvData")
    public Object[][] testCsvData(Method method) throws IOException {
        return CsvReaderUtil.getTestData("src/main/resources/testdata/1.csv");
    }

}
