package com.example.share.TimedTasks.RequestData;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class HttpRequestData {


    private static final String strURL = "http://api.waditu.com";

    public static void main(String[] args) throws IOException {

        HttpRequestData httpRequestData = new HttpRequestData();
        int[] nums = {1,12,3,4,10,22,11,10,9,3};
        for (int i = 0; i <nums.length-1 ; i++) {
            for (int j = 0; j <nums.length-i-1 ; j++) {
                if (nums[j]>nums[j+1]){
                    int tmp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        for (int i :nums
             ) {
            System.out.print(i+", ");
        }

//        InputStream fileStream = getTickData("20200723","600776");
//        ReadCsv readCsv = new ReadCsv();
//        readCsv.read(fileStream);
//       System.out.printf(String.valueOf(httpRequestData.daily_basic()));
//       System.out.println(httpRequestData.topList());


//        JSONObject jsonObjectGetData = (JSONObject) jsonObject.get("data");
//        System.out.println(jsonObjectGetData);

//        for(Map.Entry<String, Object> entry : jsonObjectGetData.entrySet()) {
//
////            System.out.println(entry.getKey()+": "+ entry.getValue());
//        }
    }

    /**
     * 获取指定股票 日期的交易明细
     *
     * @param transactDate 交易日期
     * @param code         股票代码
     * @return 返回 InputStream 字节流可直接读取
     */
    private static InputStream getTickData(String transactDate, String code) throws IOException {
        String URL = "http://quotes.money.163.com/cjmx/2020/" + transactDate + "/" + code + ".xls";
        URL url = new URL(URL);
        return url.openStream();
    }


    public String sendPost(String strURL, JSONObject postData) throws IOException {

        URL url = new URL(strURL);// 创建连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("POST"); // 设置请求方式
        connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        connection.connect();
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8); // utf-8编码
        out.write(postData.toString());
//        out.append(JSONUtil.object2JsonString(params));
        out.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        StringBuilder response = new StringBuilder();
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
            response.append(lines);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        return response.toString();
    }

}
