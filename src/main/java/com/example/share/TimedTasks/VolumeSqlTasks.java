package com.example.share.TimedTasks;

import com.example.share.bean.TickData;
import com.example.share.mapper.AmountMapper;
import com.example.share.mapper.TickDataMapper;
import com.example.share.mapper.VolumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class VolumeSqlTasks {
    @Autowired
    private TickDataMapper tickDataMapper;
    @Autowired
    private VolumeMapper volumeMapper;
    @Autowired
    private  SqlMethodUtils sqlMethodUtils;

    private ArrayList<String> transactDates;
    private String shareCode = "600776";
    private String small = "0";
    private String inside = "0";
    private String big = "0";
    private String large = "0";
    private  double sumVolume ;
    private static VolumeSqlTasks volumeSqlTasks;

    @PostConstruct
    private void init(){
        volumeSqlTasks = this;
        volumeSqlTasks.sqlMethodUtils = this.sqlMethodUtils;
//        transactDates = sqlMethodUtils.getTransactDate();
        System.out.println(VolumeSqlTasks.class.toString());
    }

     public  void startVolumeUpdateTasks() {
        if (transactDates == null){
            transactDates = sqlMethodUtils.getTransactDate();
        }

         for (String date : transactDates) {
             insertTransactDate(shareCode, date);
//             System.out.println(date);
            updateBuyAndSellData(date,"买盘");
            updateBuyAndSellData(date,"卖盘");
         }
    }

    private void updateInsertValues(HashMap dataMap){
        HashMap smallMap = (HashMap) dataMap.get("small");
        HashMap bigMap = (HashMap) dataMap.get("big");
        HashMap largeMap = (HashMap) dataMap.get("large");
        HashMap insideMap = (HashMap) dataMap.get("inside");
        small = (String) smallMap.get("volume");
        if (insideMap != null){
            inside = (String) insideMap.get("volume");
        }
        if (bigMap != null){
            big = (String) bigMap.get("volume");
        }
        if (largeMap != null){
            large = (String) largeMap.get("volume");
        }
        sumVolume = Double.parseDouble(small) +  Double.parseDouble(inside) + Double.parseDouble(big) +  Double.parseDouble(large);
    }

    // 先插入股票码和交易日期，用来作为唯一标识
    private void  insertTransactDate(String shareCode ,String date){
        volumeMapper.insertTransactDate(shareCode, date);
    }

    // 插入 指定一天指定股票买的数据
    private  void  updateBuyData(String buy, String small_buy, String inside_buy, String big_buy, String large_buy, String shareCode, String transactDate){
        volumeMapper.updateBuyData( buy, small_buy, inside_buy, big_buy, large_buy,  shareCode,  transactDate);
    }

    // 插入 指定一天指定股票卖的数据
    private  void updateSellData(String sell, String smallSell, String insideSell, String bigSell, String largeSell, String shareCode, String transactDate){
        volumeMapper.updateSellData(sell, smallSell,insideSell,bigSell,largeSell,shareCode,transactDate);
    }

    private  void updateBuyAndSellData(String date,String type){
        HashMap buyMap = sqlMethodUtils.getBuill(date,type);
        updateInsertValues(buyMap);
        if (type.equals("买盘")){
            updateBuyData(String.valueOf(sumVolume), small, inside, big, large,  shareCode,date);
        }else {
            updateSellData(String.valueOf(sumVolume), small, inside, big, large,  shareCode,date);
        }

    }
}
