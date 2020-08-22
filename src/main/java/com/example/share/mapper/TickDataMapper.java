package com.example.share.mapper;
import com.example.share.bean.TickData;

import java.util.ArrayList;


public interface TickDataMapper {
    // 查询所有
    ArrayList selectAll();
    // 小单
    ArrayList selectSmall(String date, String type);
    // 小单成交均价
    double smallAverageTransactionPrice(String date, String type);
    // 查询小单
    ArrayList countSmallBill(String date, String type,int num);
    // 查询特大单
    ArrayList countLargeBill(String date, String type,int num);
    // 查询中单和大单
    ArrayList countBill(String date, String type,int greaterThan,int lessThan);
    // 查询日期，去重
    ArrayList getTransactDate();


}
