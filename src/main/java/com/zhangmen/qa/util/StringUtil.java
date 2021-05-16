package com.zhangmen.qa.util;

import org.apache.commons.lang3.StringUtils;

/***
 * 字符串工具类
 * barry
 */
public class StringUtil {
    /**
     * 判断多个字符串都不为空
     * @param strings
     * @return
     */
    public static boolean isAllNotBlank(String... strings){
        int count = 0;
        for(int i=0;i<strings.length;i++){
            //遍历字符数组所有的参数，发现某个为 null 或者 "" ,则跳出
            if(StringUtils.isEmpty(strings[i])){
                return false;
            }
            count++;
        }
        if(count == strings.length){
            return true;
        }
        return false;
    }
}
