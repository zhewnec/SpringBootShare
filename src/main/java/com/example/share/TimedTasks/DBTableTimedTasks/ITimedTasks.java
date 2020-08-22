package com.example.share.TimedTasks.DBTableTimedTasks;


import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;

public interface ITimedTasks {
    final public String URL = "http://api.waditu.com";
    final public String TOKEN = "6fe3dd88432acfdfc451195b10377882070526cce1a1436d1331566f";

    // 要更新的任务
//    void updateTasks(String today);
    // 请求参数
    JSONObject requestParameters(String today);
    // 把结果更新到数据库中
    void resultUpdateDatabase(ArrayList data);

}
