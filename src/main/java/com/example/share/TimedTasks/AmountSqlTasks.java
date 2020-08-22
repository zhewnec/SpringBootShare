package com.example.share.TimedTasks;

import com.example.share.bean.TickData;
import com.example.share.mapper.AmountMapper;
import com.example.share.mapper.TickDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashMap;

public class AmountSqlTasks {
    @Autowired
    private TickDataMapper tickDataMapper;

    @Autowired
    private AmountMapper amountMapper;
    @Autowired
    private SqlMethodUtils sqlMethodUtils;
//    private  SqlMethodUtils sqlMethodUtils = SqlMethodUtils.getSqlMethodUtils();
    // 待优化
    private ArrayList<String> transactDates; // =  sqlMethodUtils.getTransactDate();

    private String shareCode = "600776";
    private String small = "0";
    private String inside = "0";
    private String big = "0";
    private String large = "0";
    private  double sumAmount ;




    public void startAmountUpdateTasks() {
        if (transactDates == null){
            transactDates =  sqlMethodUtils.getTransactDate();
        }
        for (int i = 0; i <transactDates.size() ; i++) {
            String date = transactDates.get(i);

            updateBuyAndSellData(date,"买盘");
            updateBuyAndSellData(date,"卖盘");
        }
    }



    private void insertAmountData(HashMap dataMap){
        HashMap smallMap = (HashMap) dataMap.get("small");
        HashMap bigMap = (HashMap) dataMap.get("big");
        HashMap largeMap = (HashMap) dataMap.get("large");
        HashMap insideMap = (HashMap) dataMap.get("inside");
        small = (String) smallMap.get("amount");
        if (insideMap != null){
            inside = (String) insideMap.get("amount");
        }
        if (bigMap != null){
            big = (String) bigMap.get("amount");
        }
        if (largeMap != null){
            large = (String) largeMap.get("amount");
        }
        sumAmount = Double.parseDouble(small) +  Double.parseDouble(inside) + Double.parseDouble(big) +  Double.parseDouble(large);
    }

    // 先插入股票码和交易日期，用来作为唯一标识
    private void  insertTransactDate(String shareCode ,String date){
        amountMapper.insertTransactDate(shareCode, date);
    }
    // 插入 指定一天指定股票买的数据
    private  void  updateBuyData(String buy, String small_buy, String inside_buy, String big_buy, String large_buy, String shareCode, String transactDate){
        amountMapper.updateBuyData( buy, small_buy, inside_buy, big_buy, large_buy,  shareCode,  transactDate);
    }

    // 插入 指定一天指定股票卖的数据
    private  void updateSellData(String sell, String smallSell, String insideSell, String bigSell, String largeSell, String shareCode, String transactDate){
        amountMapper.updateSellData(sell, smallSell,insideSell,bigSell,largeSell,shareCode,transactDate);
    }

    private  void updateBuyAndSellData(String date,String type){

        HashMap buyMap = sqlMethodUtils.getBuill(date, type);

        insertAmountData(buyMap);
        if (type.equals("买盘")){
            updateBuyData(String.valueOf(sumAmount), small, inside, big, large,  shareCode,date);
        }else {
            updateSellData(String.valueOf(sumAmount), small, inside, big, large,  shareCode,date);
        }

    }
}
