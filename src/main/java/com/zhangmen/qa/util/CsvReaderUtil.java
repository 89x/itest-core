package com.zhangmen.qa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/***
 * csv 读取类
 * barry
 */
public class CsvReaderUtil {
    public static Object[][] getTestData(String fileName) throws IOException {

        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        /**
         * 设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
         */

        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        /**
         * 目录要src开始
         * 忽略读取CSV文件的标题行（第一行）
         */

        file.readLine();

        /**
         * 遍历读取文件中除第一行外的其他所有行内容 并存储在名为records的ArrayList中
         * 每一个recods中存储的对象为一个String数组
         */
        while ((record = file.readLine()) != null) {
            String fileds[] = record.split(",");
            records.add(fileds);
        }
        file.close();

        /**
         *   定义函数返回值，即Object[][]
         *   将存储测试数据的list转换为一个Object的二维数组
         */

        Object[][] result = new Object[(records.size())][];
        for (int i = 0; i < records.size(); i++) {
            result[i] = records.get(i);
        }
        return result;
    }


}