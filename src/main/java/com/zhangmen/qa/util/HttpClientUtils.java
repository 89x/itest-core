package com.zhangmen.qa.util;


import com.zhangmen.qa.builder.HCB;
import com.zhangmen.qa.common.HttpConfig;
import com.zhangmen.qa.common.HttpMethods;
import com.zhangmen.qa.common.HttpResult;
import com.zhangmen.qa.common.Utils;
import com.zhangmen.qa.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtils {



    //默认采用的http协议的HttpClient对象
    private static  HttpClient client4HTTP;


    //默认采用的https协议的HttpClient对象
    private static HttpClient client4HTTPS;

    static{
        try {
            client4HTTP = HCB.custom().build();
            client4HTTPS = HCB.custom().ssl().build();
        } catch (HttpProcessException e) {
            Utils.errorException("创建https协议的HttpClient对象出错：{}", e);
        }
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param config		请求参数配置
     * @return	返回结果
     * @throws HttpProcessException	http处理异常
     */
    public static String get(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.GET));
    }

    /**
     * 以Post方式，请求资源或服务
     *
     * @param config		请求参数配置
     * @return				返回处理结果
     * @throws HttpProcessException	http处理异常
     */
    public static String  post(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.POST));
    }

    /**
     * 请求资源或服务
     *
     * @param config		请求参数配置
     * @return				返回处理结果
     * @throws HttpProcessException	http处理异常
     */
    public static String send(HttpConfig config) throws HttpProcessException {
        return fmt2String(execute(config), config.outenc());
    }
    /**
     * 转化为字符串
     *
     * @param resp			响应对象
     * @param encoding		编码
     * @return				返回处理结果
     * @throws HttpProcessException	http处理异常
     */
    private static String fmt2String(HttpResponse resp, String encoding) throws HttpProcessException {
        String body = "";
        try {
            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), encoding);
            }else{//有可能是head请求
                body =resp.getStatusLine().toString();
            }
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new HttpProcessException(e);
        }finally{
            close(resp);
        }
        return body;
    }
    /**
     * 请求资源或服务
     *
     * @param config		请求参数配置
     * @return				返回HttpResponse对象
     * @throws HttpProcessException	http处理异常
     */
    private static HttpResponse execute(HttpConfig config) throws HttpProcessException {
        create(config);//获取链接
        HttpResponse resp = null;

        try {
            //创建请求对象
            HttpRequestBase request = getRequest(config.url(), config.method());

            //设置超时
            request.setConfig(config.requestConfig());

            //设置header信息
            request.setHeaders(config.headers());

            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if(HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())){
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();

                if(request.getClass()== HttpGet.class) {
                    //检测url中是否存在参数
                    //注：只有get请求，才自动截取url中的参数，post等其他方式，不再截取
                    config.url(Utils.checkHasParas(config.url(), nvps, config.inenc()));
                }

                //装填参数
                HttpEntity entity = Utils.map2HttpEntity(nvps, config.map(), config.inenc());

                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase)request).setEntity(entity);

                Utils.info("请求地址："+config.url());
                if(nvps.size()>0){
                    Utils.info("请求参数："+nvps.toString());
                }
                if(config.json()!=null){
                    Utils.info("请求参数："+config.json());
                }
            }else{
                int idx = config.url().indexOf("?");
                Utils.info("请求地址："+config.url().substring(0, (idx>0 ? idx : config.url().length())));
                if(idx>0){
                    Utils.info("请求参数："+config.url().substring(idx+1));
                }
            }
            //执行请求操作，并拿到结果（同步阻塞）
            resp = (config.context()==null)?config.client().execute(request) : config.client().execute(request, config.context()) ;

            if(config.isReturnRespHeaders()){
                //获取所有response的header信息
                config.headers(resp.getAllHeaders());
            }

            //获取结果实体
            return resp;

        } catch (IOException e) {
            throw new HttpProcessException(e);
        }
    }
    /**
     * 尝试关闭response
     *
     * @param resp				HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if(resp == null) return;
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if(CloseableHttpResponse.class.isAssignableFrom(resp.getClass())){
                ((CloseableHttpResponse)resp).close();
            }
        } catch (IOException e) {
            Utils.exception(e);
        }
    }
    /**
     * 根据请求方法名，获取request对象
     *
     * @param url			资源地址
     * @param method		请求方式
     * @return				返回Http处理request基类
     */
    private static HttpRequestBase getRequest(String url, HttpMethods method) {
        HttpRequestBase request = null;
        switch (method.getCode()) {
            case 0:// HttpGet
                request = new HttpGet(url);
                break;
            case 1:// HttpPost
                request = new HttpPost(url);
                break;
        }
        return request;
    }
    /**
     * 判定是否开启连接池、及url是http还是https <br>
     * 		如果已开启连接池，则自动调用build方法，从连接池中获取client对象<br>
     * 		否则，直接返回相应的默认client对象<br>
     *
     * @param config		请求参数配置
     * @throws HttpProcessException	http处理异常
     */
    private static void create(HttpConfig config) throws HttpProcessException  {
        if(config.client()==null){//如果为空，设为默认client对象
            if(config.url().toLowerCase().startsWith("https://")){
                config.client(client4HTTPS);
            }else{
                config.client(client4HTTP);
            }
        }
    }
    /**
     * 请求资源或服务，返回HttpResult对象
     *
     * @param config		请求参数配置
     * @return				返回HttpResult处理结果
     * @throws HttpProcessException	http处理异常
     */
    public static HttpResult sendResult(HttpConfig config) throws HttpProcessException {
        Header[] reqHeaders = config.headers();
        //执行结果
        HttpResponse resp =  execute(config);

        HttpResult result = new HttpResult(resp);
        result.setResult(fmt2String(resp, config.outenc()));
        result.setReqHeaders(reqHeaders);
        //获取所有响应头
        Header[] header = resp.getAllHeaders();
        //遍历输出所有
        for (Header ss:header){
            System.out.println(ss);
        }

        return result;
    }




}
