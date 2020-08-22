package com.example.share.TimedTasks.DBTableTimedTasks;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.share.TimedTasks.RequestData.HttpRequestData;
import com.example.share.TimedTasks.SqlMethodUtils;
import com.example.share.mapper.TopListMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;

@Component
public class TopListTimedTasks implements ITimedTasks {
    private static Logger log = Logger.getLogger(TopListTimedTasks.class);

    @Autowired
    private TopListMapper topListMapper;
    @Autowired
    private HttpRequestData httpRequestData;

//    private String today;

    /**
     * 更新每个交易日的龙虎榜数据
     */
//    public void updateTasks(String today){
//        JSONObject getResultTodayTopList = requestParameters(today);
//        log.info("TopListTimedTasks 请求结果信息为：" + getResultTodayTopList);
//
//        JSONObject jsonObjectGetData = (JSONObject) getResultTodayTopList.get("data");
//        JSONArray items = jsonObjectGetData.getJSONArray("items");
//        if (items.size()>0){
//            ArrayList formatDate = SqlMethodUtils.analysisJsonData(jsonObjectGetData);
//            resultUpdateDatabase(formatDate);
//        }else {
//            log.info("没有获取到数据，暂不更新");
//        }
//    }

    /**
     * 获取龙虎榜上榜数据列表
     */
    public JSONObject requestParameters(String tradeDate) {

        JSONObject params = new JSONObject();
        params.put("trade_date", tradeDate);
        JSONObject postData = new JSONObject();
        postData.put("api_name","top_list");
        postData.put("token",TOKEN);
        postData.put("fields","");
        postData.put("params", params);
        String response = null;
        try {
            response = httpRequestData.sendPost(URL, postData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (JSONObject) JSONObject.parse(response);
    }

    public void resultUpdateDatabase(ArrayList formatDate){
        for (Object data : formatDate) {
            System.out.println(data);
            topListMapper.insertTodayTopLIstInfo((Map) data);
        }
    }

//    private Boolean selectTodayUpdate(){
//
//        return topListMapper.IsTodayUpdate(today);
//    }

}
