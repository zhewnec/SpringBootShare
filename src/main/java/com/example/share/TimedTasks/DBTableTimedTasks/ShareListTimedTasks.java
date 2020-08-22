package com.example.share.TimedTasks.DBTableTimedTasks;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.share.TimedTasks.RequestData.HttpRequestData;
import com.example.share.TimedTasks.SqlMethodUtils;
import com.example.share.mapper.ShareListMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ShareListTimedTasks implements ITimedTasks{
    private static Logger log = Logger.getLogger(ShareListTimedTasks.class);
    @Autowired
    private ShareListMapper shareListMapper;
    @Autowired
    private HttpRequestData httpRequestData;


//    public void updateTasks(String today){
//
//        JSONObject getResultTodayTopList = requestParameters(today);
//        JSONObject jsonObjectGetData = (JSONObject) getResultTodayTopList.get("data");
//        JSONArray items = jsonObjectGetData.getJSONArray("items");
//
//        if (items.size()>0){
//            ArrayList formatDate = SqlMethodUtils.analysisJsonData(jsonObjectGetData);
//            resultUpdateDatabase(formatDate);
//        }else {
//            log.info("没有获取到数据，暂不更新");
//        }
//        log.info("game over");
//    }

    /**
     * 获取股票列表
     * 获取基础信息数据，包括股票代码、名称、上市日期、退市日期等
     */

    public JSONObject requestParameters(String today)  {

        JSONObject postData = new JSONObject();
        postData.put("api_name","stock_basic");
        postData.put("token",TOKEN);
        postData.put("fields","");
        postData.put("params", null);

         String response = null;
        try {
             response = httpRequestData.sendPost(URL, postData);
        }catch (Exception e){
            e.printStackTrace();
        }

        return (JSONObject) JSONObject.parse(response);
    }


    public void resultUpdateDatabase(ArrayList shareListMap){
        for (Object data : shareListMap) {
//            System.out.println(data);
            HashMap<String,String> insertData = (HashMap) data;
//            insertData.put("update_date",today);
            shareListMapper.insertShareListInfo(insertData);
        }
    }
}
