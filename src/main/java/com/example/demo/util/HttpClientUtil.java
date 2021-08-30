package com.example.demo.util;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Map;

/**
 * @ClassName HttpClientUtil
 * @Description
 * @Author ykw
 * @Date 2021/7/5 10:55
 */
@Slf4j
public class HttpClientUtil {
    private static  final String ENCODING = "UTF-8";

    /**
     * 发送post请求
     * @param url 地址
     * @param jsonstr 请求体参数
     * @return 请求返回结果
     * @throws Exception
     */
    public static String doPost(String url, String jsonstr) throws Exception {
        log.info("【httpClientUtil请求发送地址】:{}", url);
        // 请求返回结果
        String resultJson = "";
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost();
        try {
            //设置请求地址
            httpPost.setURI(new URI(url));

            //设置请求头
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonstr, ENCODING));

            //发送请求
            HttpResponse response = client.execute(httpPost);
            //获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == status) {
                resultJson = EntityUtils.toString(response.getEntity(), ENCODING);
                log.info("【HttpClientUtil请求返回】:{}",resultJson);
            } else {
                log.error("响应失败，状态码:{}",status);
                throw new Exception("发送http请求失败");
            }
        } catch (Exception e) {
            log.error("发送post请求失败",e.getMessage());
            throw e;
        } finally {
            httpPost.releaseConnection();
        }
        return resultJson;
    }

    /**
     * 发送自定义请求头post请求
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求体参数
     * @param sendEncoding 发送编码
     * @param resultEncoding 接受编码
     * @return 请求返回结果
     * @throws Exception
     */
    public static String sendPostWithStrEncod(String url, Map<String, String> headers, String params, String sendEncoding, String resultEncoding) throws Exception {
        log.info("进入post请求方法=================");
        log.info("请求入参url:{}", url);
        log.info("请求入参headers:{}", headers);
        log.info("请求入参params:{}", params);
        log.info("请求入参sendEncoding:{}", sendEncoding);
        log.info("请求入参:resultEncoding:{}", resultEncoding);
        //请求返回结果
        String resultJson = null;
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost();

        try {
            httpPost.setURI(new URI(url));
            httpPost.setEntity(new StringEntity(params, sendEncoding));
            if (CollUtil.isNotEmpty(headers)) {
                Header[] header = new BasicHeader[headers.size()];
                int i = 0;
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    header[i] = new BasicHeader(e.getKey(), e.getValue());
                    i ++;
                }
                httpPost.setHeaders(header);
            }
            HttpResponse response = client.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resultJson = EntityUtils.toString(response.getEntity(), resultEncoding);
            } else {
                log.error("响应失败，状态码:{}",status);
                throw new Exception("发送http请求失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            httpPost.releaseConnection();
        }
        return resultJson;
    }

    /**
     * 发送get请求
     * @param url 请求地址
     * @param headers 请求头
     * @return 请求返回结果
     * @throws Exception
     */
    public static String sendGet(String url, Map<String, String> headers) throws Exception {
        log.info("发送get请求，入参url:{},headers:{}", url, headers);
        String result = "";
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            if (CollUtil.isNotEmpty(headers)) {
                Header[] header = new BasicHeader[headers.size()];
                int i = 0;
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    header[i] = new BasicHeader(e.getKey(), e.getValue());
                    i ++;
                }
                httpGet.setHeaders(header);
            }
            HttpResponse response = client.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == status) {
                result = EntityUtils.toString(response.getEntity(), ENCODING);
            } else {
                log.error("响应失败，状态码:{}",status);
                throw new Exception("发送http请求失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String s = doPost("http://127.0.0.1:8089/test/post", "");
        System.out.println(s);
    }
}
