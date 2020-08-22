package com.example.share.Impl;

import com.example.share.bean.TickData;
import com.example.share.mapper.TickDataMapper;
import com.example.share.service.TickDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service("TickDataService")
public class TickDataImpl implements TickDataService {
    @Autowired
    private TickDataMapper tickDataMapper;


    @Override
    public ArrayList selectAll() {
        return tickDataMapper.selectAll();
    }

//    @Override
//    public  ArrayList selectSmall(){
//        return tickDataMapper.selectSmall("2020-01-02","买盘");
//    }

    // 小单
    @Override
    public ArrayList countBillSmall(String date, String type) {
        //
        return  tickDataMapper.countSmallBill(date,type, 200);
    }


    // 中单
    public ArrayList countInsideBill(String date, String type) {
        return  tickDataMapper.countBill(date,type, 200, 1000);
    }
    // 大单
    public ArrayList countBigBill(String date,String type) {
        return  tickDataMapper.countBill( date,type,200, 1000);
    }
    // 特大单
    public ArrayList countLargeBill(String date, String type) {
        return  tickDataMapper.countLargeBill( date,type, 5000);
    }

}
