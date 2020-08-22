package com.example.share.TimedTasks;


import com.alibaba.fastjson.JSONObject;
import com.example.share.TimedTasks.DBTableTimedTasks.TopListTimedTasks;
import com.example.share.TimedTasks.RequestData.HttpRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private TopListTimedTasks topListTimedTasks;

    // "0 15 17 ? * MON-FRI" 每个周一、周二、周三、周四、周五的17:15触发
//    @Scheduled(cron="0 15 17 ? * MON-FRI")
    public void scheduledTask() {
//        topListTimedTasks.updateTopListDate();

//        JSONObject jsonObjectGetData = (JSONObject) jsonObject.get("data");
//        System.out.println(jsonObjectGetData);
//        httpRequestData.analysisJsonData(jsonObject);

//        AmountSqlTasks amountSqlTasks = new AmountSqlTasks();
//        amountSqlTasks.startAmountUpdateTasks();
//        VolumeSqlTasks volumeSqlTasks = new VolumeSqlTasks();
//        volumeSqlTasks.startVolumeUpdateTasks();


    }
}

