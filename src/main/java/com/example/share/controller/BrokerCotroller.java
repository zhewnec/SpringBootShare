package com.example.share.controller;

import com.example.share.mapper.BrokerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class BrokerCotroller {
    @Autowired
    BrokerMapper brokerMapper;

    @ResponseBody
    @RequestMapping("/api/broker")
    public ArrayList GetBroker(){
        ArrayList selectBrokerData = brokerMapper.selectBrokerAll();
        System.out.println(selectBrokerData);
        return selectBrokerData;
    }
}
