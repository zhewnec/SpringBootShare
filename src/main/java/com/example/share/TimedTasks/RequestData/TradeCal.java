package com.example.share.TimedTasks.RequestData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TradeCal {
    private HttpRequestData httpRequestData = new HttpRequestData();

    public static void main(String[] args) {
        TradeCal tradeCal = new TradeCal();
        System.out.println(tradeCal.getTradeCal());
    }
    /**
     * 请求获取是否为交易日的数据
     * @return
     */
    private String requestTradeDate() throws IOException {
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String tradeDate = formatter.format(date);

        JSONObject params = new JSONObject();
        params.put("exchange", "");
        params.put("start_date", tradeDate);
        params.put("end_date", tradeDate);

        JSONObject postData = new JSONObject();
        postData.put("api_name","trade_cal");
        postData.put("token","6fe3dd88432acfdfc451195b10377882070526cce1a1436d1331566f");
        postData.put("fields","");
        postData.put("params", params);
        String strURL = "http://api.waditu.com";
        return httpRequestData.sendPost(strURL, postData);

    }

    private Boolean analysisJsonData(JSONObject jsonObject){
        JSONObject jsonObjectGetData = (JSONObject) jsonObject.get("data");
        JSONArray items = jsonObjectGetData.getJSONArray("items");
        Integer  day  = (Integer) items.getJSONArray(0).get(2);
        return day != 0;
    }
    /**
     *返回今天是否为交易日，false：不是，true：是
     */

    public Boolean getTradeCal(){
        try {
            String getTradeResult =  requestTradeDate();
            return  analysisJsonData((JSONObject) JSONObject.parse(getTradeResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
