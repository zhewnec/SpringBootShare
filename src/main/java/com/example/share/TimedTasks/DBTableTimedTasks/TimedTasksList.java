package com.example.share.TimedTasks.DBTableTimedTasks;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.share.TimedTasks.RequestData.TradeCal;
import com.example.share.TimedTasks.SqlMethodUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimedTasksList {
    private static Logger log = Logger.getLogger(TimedTasksList.class);
    @Autowired
    TopListTimedTasks topListTimedTasks;
    @Autowired
    DailyBasic dailyBasic;
    @Autowired
    ShareListTimedTasks shareListTimedTasks;
    @Autowired
    private TradeCal tradeCal;
    private String today ;

    TimedTasksList(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        today = formatter.format(date);
    }

    @Scheduled(cron="0 18 20 ? * MON-FRI" )
    public void update(){
        if (!IsTodayTrade()){
            log.info("今天不是交易日。");
            return;
        }
//        topListTimedTasks.updateTasks( today);
//        dailyBasic.updateTasks(today);
//        shareListTimedTasks.updateTasks(today);

        updateTasks(topListTimedTasks);
        updateTasks(dailyBasic);
        updateTasks(shareListTimedTasks);

    }

    /**
     今天是否为交易日
     */
    private Boolean IsTodayTrade() {
        return tradeCal.getTradeCal();
    }


    private void updateTasks(ITimedTasks timedTasks){
        JSONObject getResultTodayTopList = timedTasks.requestParameters(today);
        log.info("TopListTimedTasks 请求结果信息为：" + getResultTodayTopList);

        JSONObject jsonObjectGetData = (JSONObject) getResultTodayTopList.get("data");
        JSONArray items = jsonObjectGetData.getJSONArray("items");
        if (items.size()>0){
            ArrayList formatDate = SqlMethodUtils.analysisJsonData(jsonObjectGetData);
            timedTasks.resultUpdateDatabase(formatDate);
        }else {
            log.info("没有获取到数据，暂不更新");
        }
    }
}
