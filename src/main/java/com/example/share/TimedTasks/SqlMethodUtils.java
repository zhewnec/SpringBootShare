package com.example.share.TimedTasks;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.share.bean.TickData;
import com.example.share.mapper.TickDataMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class SqlMethodUtils {

    private static Logger log = Logger.getLogger(SqlMethodUtils.class);

    @Autowired
    private  TickDataMapper tickDataMapper ;
    private SqlMethodUtils(){}


    public HashMap<String, HashMap<String, String>> getBuill(String date, String type){
        HashMap<String, HashMap<String, String>> countBill = new HashMap<>();
        ArrayList smallBill = tickDataMapper.countSmallBill(date,type, 200);
        ArrayList inside = tickDataMapper.countBill(date,type, 200,1000);
        ArrayList big = tickDataMapper.countBill(date,type, 1000, 5000);
        ArrayList large = tickDataMapper.countLargeBill(date,type, 5000);
        countBill.put("small",countBill(smallBill));
        countBill.put("inside",countBill(inside));
        countBill.put("big",countBill(big));
        countBill.put("large",countBill(large));
        return countBill;
    }

    private HashMap<String, String> countBill(ArrayList smallBill) {
        HashMap<String,String> count = new HashMap<>();
        TickData tickData = (TickData) smallBill.get(0);
        if (tickData == null){
            return null;
        }
        String volume  = tickData.getVolume();
        long amount = tickData.getAmount();
        // 1手等于100股
        double Average = (amount / (Double.parseDouble(volume) * 100));
        count.put("volume",volume);
        count.put("amount",(amount/1000)+"");
        count.put("Average", String.format("%.2f",Average));
        return count;
    }
    public ArrayList<String> getTransactDate(){
        ArrayList<String> transactDates = new ArrayList<String>();
        ArrayList getTransactDate =  tickDataMapper.getTransactDate();
        for (int i = 0; i < getTransactDate.size() ; i++) {
            TickData tickData = (TickData) getTransactDate.get(i);
            transactDates.add(tickData.getTransactDate());
        }
        return transactDates;
    }
    // 格式化 http请求后的数据，转为可以直接插入数据库的Map类型
    public static ArrayList analysisJsonData(JSONObject jsonObject){
        log.info(jsonObject);
        ArrayList repareData = new ArrayList();
        JSONArray fields = jsonObject.getJSONArray("fields");
        JSONArray items = jsonObject.getJSONArray("items");

        for (int i = 0; i <items.size() ; i++) {
            Map<String, String> objMap = new HashMap<>();
            JSONArray shareDayInfo = (JSONArray) items.get(i);
            for (int j = 0; j <shareDayInfo.size() ; j++) {
                try {
                    objMap.put(fields.get(j).toString(),shareDayInfo.get(j).toString());
                }catch (Exception e){
                    objMap.put(fields.get(j).toString(),null);
                }
            }
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("columnMap", objMap);
            repareData.add(columnMap);
        }
        return repareData;

    }
}
