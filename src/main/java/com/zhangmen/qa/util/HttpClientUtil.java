package com.zhangmen.qa.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * barry.2021-03-06  version  0.01
 */

public class HttpClientUtil {
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    /***
     *  编码集
     */
    private final static String CHAR_SET = "UTF-8";
    /***
     *  Post表单请求形式请求头
     */
    private final static String CONTENT_TYPE_POST_FORM = "application/x-www-form-urlencoded";
    /***
     *  Post Json请求头
     */
    private final static String CONTENT_TYPE_JSON = "application/json";
    /***
     *  连接管理器
     */
    private static PoolingHttpClientConnectionManager poolManager;
    /***
     *  请求配置
     */
    private static RequestConfig requestConfig;

    static {
        // 静态代码块,初始化HtppClinet连接池配置信息,同时支持http和https
        try {
            logger.info("初始化连接池-------->>>>开始");
            // 使用SSL连接Https
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLContext sslContext = builder.build();
            // 创建SSL连接工厂
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();
            // 初始化连接管理器
            poolManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 设置最大连接数
            poolManager.setMaxTotal(1000);
            // 设置最大路由
            poolManager.setDefaultMaxPerRoute(300);
            // 从连接池获取连接超时时间
            int coonectionRequestTimeOut = 5000;
            // 客户端和服务器建立连接超时时间
            int connectTimeout = 5000;
            // 客户端从服务器建立连接超时时间
            int socketTimeout = 5000;
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(coonectionRequestTimeOut)
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(socketTimeout).build();
            logger.info("初始化连接池-------->>>>结束");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化连接池-------->>>>失败");
        }
    }


    public static String doGet(String url, Map<String, String> params) {
        String result = "";
        // 获取http客户端
//         CloseableHttpClient httpClient = getCloseableHttpClient();
        // 获取http客户端从连接池中
        CloseableHttpClient httpClient = getCloseableHttpClientFromPool();
        // 响应模型
        CloseableHttpResponse httpResponse = null;
        try {
            // 创建URI 拼接请求参数
            URIBuilder uriBuilder = new URIBuilder(url);
            // uri拼接参数
            if (null != params) {
                Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> next = it.next();
                    uriBuilder.addParameter(next.getKey(), next.getValue());
                }

            }
            URI uri = uriBuilder.build();
            // 创建Get请求
            HttpGet httpGet = new HttpGet(uri);
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 获取响应实体
                HttpEntity httpEntity = httpResponse.getEntity();
                if (null != httpEntity) {
                    result = EntityUtils.toString(httpEntity, CHAR_SET);
                    System.out.println("响应内容:" + result);
                    return result;
                }

            }
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            System.out.println("响应码:" + statusCode);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != httpResponse) {
                    httpResponse.close();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static CloseableHttpClient getCloseableHttpClient() {
        return HttpClientBuilder.create().build();
    }

    /**
     * 从http连接池中获取连接
     */
    private static CloseableHttpClient getCloseableHttpClientFromPool() {
        //
        ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy = new ServiceUnavailableRetryStrategy() {

            public boolean retryRequest(HttpResponse httpResponse, int executionCount, HttpContext httpContext) {
                if (executionCount < 3) {
                    System.out.println("ServiceUnavailableRetryStrategy");
                    return true;
                } else {
                    return false;
                }
            }

            // 重试时间间隔
            public long getRetryInterval() {
                return 3000L;
            }
        };


        // 设置连接池管理
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolManager)
                // 设置请求配置策略
                .setDefaultRequestConfig(requestConfig)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler()).build();
        return httpClient;

    }


    /**
     * Post请求,表单形式
     */
    public static String doPost(String url, Map<String, String> params) {
        String result = "";
        // 获取http客户端
        CloseableHttpClient httpClient = getCloseableHttpClient();
        // 响应模型
        CloseableHttpResponse httpResponse = null;
        try {
            // Post提交封装参数列表
            ArrayList<NameValuePair> postParamsList = new ArrayList<NameValuePair>();
            if (null != params) {
                Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> next = it.next();
                    postParamsList.add(new BasicNameValuePair(next.getKey(), next.getValue()));
                }
            }
            // 创建Uri
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(postParamsList, CHAR_SET);
            // 设置表达请求类型
            urlEncodedFormEntity.setContentType(CONTENT_TYPE_POST_FORM);
            HttpPost httpPost = new HttpPost(url);
            // 设置请求体
            httpPost.setEntity(urlEncodedFormEntity);
            // 执行post请求
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), CHAR_SET);
                System.out.println("Post form reponse {}" + result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                CloseResource(httpClient, httpResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static void CloseResource(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) throws IOException {
        if (null != httpResponse) {
            httpResponse.close();
        }
        if (null != httpClient) {
            httpClient.close();
        }
    }

    /***
     *  Post请求,Json形式
     */

    public static String doPostJson(String url, String jsonStr) {
        String result = "";
        CloseableHttpClient httpClient = getCloseableHttpClient();
        CloseableHttpResponse httpResponse = null;
        try {
            // 创建Post
            HttpPost httpPost = new HttpPost(url);

            // 封装请求参数
            StringEntity stringEntity = new StringEntity(jsonStr, CHAR_SET);
            // 设置请求参数封装形式
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            int code = httpResponse.getStatusLine().getStatusCode();
            logger.info(code);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), CHAR_SET);
                logger.info(result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                CloseResource(httpClient, httpResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
