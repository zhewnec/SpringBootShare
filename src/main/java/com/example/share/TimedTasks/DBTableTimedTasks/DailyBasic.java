package com.example.share.TimedTasks.DBTableTimedTasks;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.share.TimedTasks.RequestData.HttpRequestData;
import com.example.share.TimedTasks.SqlMethodUtils;
import com.example.share.mapper.DailyBasicMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class DailyBasic implements ITimedTasks {
    private static Logger log = Logger.getLogger(DailyBasic.class);
    @Autowired
    DailyBasicMapper dailyBasicMapper;
    @Autowired
    HttpRequestData httpRequestData;



//    public void  updateTasks(String today){
//        log.info("DailyBasic 任务开始执行");
//
//        JSONObject jsonObject =  requestParameters(today);
//        log.info("DailyBasic 请求结果信息为：" + jsonObject);
//        JSONObject jsonObjectGetData = (JSONObject) jsonObject.get("data");
//        JSONArray items = jsonObjectGetData.getJSONArray("items");
//        if (items.size()>0){
//            ArrayList formatDate = SqlMethodUtils.analysisJsonData(jsonObjectGetData);
//            resultUpdateDatabase(formatDate);
//        }else {
//            log.info("没有获取到数据，暂不更新");
//        }
//    }
    /**
     * 获取今日上市公司的流动股本，流通市值
     * @return
     * @throws IOException
     */
    public JSONObject requestParameters(String today)  {

        JSONObject params = new JSONObject();
        params.put("ts_code","");
        params.put("trade_date", today);
        JSONObject postData = new JSONObject();
        postData.put("api_name","daily_basic");
        postData.put("token",TOKEN);
        postData.put("fields","ts_code,trade_date,close,turnover_rate,turnover_rate_f,volume_ratio,pe,pe_ttm,pb,ps," +
                "ps_ttm,dv_ratio,dv_ttm,total_share,float_share,free_share,total_mv,circ_mv");
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
            dailyBasicMapper.updateTodayDateInfo((Map) data);
        }

    }
}
