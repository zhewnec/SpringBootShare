package com.example.share.controller;

import com.example.share.mapper.ShareListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin
@RestController
public class ShareListController {
    @Autowired
    ShareListMapper shareListMapper;

    @ResponseBody
    @RequestMapping("/api/sharelist")
    public ArrayList GetBroker(){
        ArrayList selectBrokerData = shareListMapper.selectMarketDistributed();
        System.out.println(selectBrokerData.toString());
        return (ArrayList) selectBrokerData;
    }
}
