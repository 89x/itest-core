package com.zhangmen.qa.util;


import com.alibaba.fastjson.JSONArray;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


/**
 * json 相关格式化
 * barry
 */
public class JsonUtils {

    public static String getValueByJPath(JSONObject responseJson, String jpath) throws JSONException {

        Object obj = responseJson;

        for(String s : jpath.split("/")) {

            if(!s.isEmpty()) {

                if(!(s.contains("[") || s.contains("]"))) {

                    obj = ((JSONObject) obj).get(s);

                }else if(s.contains("[") || s.contains("]")) {

                    obj =((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));

                }
            }
        }
        return obj.toString();
    }


}
