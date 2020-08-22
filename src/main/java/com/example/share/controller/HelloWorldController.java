package com.example.share.controller;
import com.example.share.bean.TickData;
import com.example.share.service.TickDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin
@RestController
public class HelloWorldController {

    @Autowired
    private TickDataService tickDataService;

    @ResponseBody
    @RequestMapping("/api/hello")
    public ArrayList index() {
        return this.tickDataService.selectAll();
    }

    @ResponseBody
    @GetMapping("/api/getbill")
    public HashMap<String, String> getBuill(@RequestParam(value = "countType") String countType){

        String transactDate = "2020-01-02";
//        String countType = "Amount";

        return countDayInfo(transactDate, countType);
    }

    private HashMap<String, String> countDayInfo(String transactDate, String countType){

        HashMap<String, String> countBill = new HashMap<>();
        ArrayList buySmall  = this.tickDataService.countBillSmall(transactDate, "买盘");
        ArrayList sellSmall  = this.tickDataService.countBillSmall(transactDate, "卖盘");
        countBill.put("buySmall",countBuill(buySmall, countType));
        countBill.put("sellSmall",countBuill(sellSmall, countType));
        ArrayList buyInside = this.tickDataService.countInsideBill(transactDate, "买盘");
        ArrayList sellInside = this.tickDataService.countInsideBill(transactDate, "卖盘");
        countBill.put("buyInside",countBuill(buyInside, countType));
        countBill.put("sellInside",countBuill(sellInside, countType));
        ArrayList buyBig = this.tickDataService.countBigBill(transactDate, "买盘");
        ArrayList sellBig = this.tickDataService.countBigBill(transactDate, "卖盘");
        countBill.put("buyBig",countBuill(buyBig, countType));
        countBill.put("sellBig",countBuill(sellBig, countType));
        ArrayList buyLarge = this.tickDataService.countLargeBill(transactDate, "买盘");
        ArrayList sellLarge = this.tickDataService.countLargeBill(transactDate, "卖盘");
        countBill.put("buyLarge",countBuill(buyLarge, countType));
        countBill.put("sellLarge",countBuill(sellLarge, countType));
        System.out.println("==============================================");
        System.out.println(countBill);
        return countBill;
    }
    private String countBuill(ArrayList dataBill , String countType){
        TickData tickData = (TickData) dataBill.get(0);
        if (tickData == null){
            return null;
        }
        switch (countType){
            case  "Amount":
                return countAmount(tickData);
            case "Volume":
                return countVolume(tickData);
            case "Average":
                return countAverage(tickData);
            default:
                return null;
        }

    }

    // 统计金额
    private String countAmount(TickData tickData){
        long amount = tickData.getAmount();
        return String.valueOf(amount/10000);
    }

    // 统计成交量
    private String countVolume(TickData tickData){
        return tickData.getVolume();
    }

    // 统计成交均价
    private String countAverage(TickData tickData){

        long amount = tickData.getAmount();
        String volume  = tickData.getVolume();
        double Average = (amount / (Double.parseDouble(volume) * 100));
        return String.format("%.2f",Average);
    }
}