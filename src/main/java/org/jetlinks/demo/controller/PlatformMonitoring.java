package org.jetlinks.demo.controller;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetlinks.demo.openapi.HttpRequest;
import org.jetlinks.demo.openapi.Response;
import org.jetlinks.demo.openapi.SimpleHttpRequest;
import org.jetlinks.demo.util.HeaderUtils;

import java.io.IOException;

/**
 * @author chenyao
 * @version 1.0
 * @Description //TODO $
 * @date 2022/3/10 17:36
 */
public class PlatformMonitoring {
    private static HttpClient httpClient = HttpClientBuilder.create().build();

    public Integer sendCommandTest() {
        String url = "http://223.240.117.24:8844/api/v1/device/send/command";
        HttpRequest request = new SimpleHttpRequest(url, httpClient);
        String body = "{\n" +
                "\t\"deviceId\": \"112233445560\",\n" +
                "\t\"sendType\": \"2\",\n" +
                "\t\"commandType\": \"kaihu\",\n" +
                "\t\"commandContent\": \"\"\n" +
                "}";
        request.headers(HeaderUtils.createHeadersOfJsonString(body));
        request.requestBody(body);

        System.out.println("Headers:===========>" + HeaderUtils.createHeadersOfJsonString(body));

        try {
            System.out.println("尝试");
            Response response = request.post();
            System.out.println("成功");
            System.out.println(JSON.parse(response.asBytes()));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("错误");
            return 1;
        }
        return 0;
    }
}
