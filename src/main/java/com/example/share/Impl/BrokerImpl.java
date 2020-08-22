package com.example.share.Impl;

import com.example.share.mapper.BrokerMapper;
import com.example.share.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("BrokerService")
public class BrokerImpl implements BrokerService {
    @Autowired
    BrokerMapper brokerMapper;

    @Override
    public ArrayList selectBrokerAll() {
        return brokerMapper.selectBrokerAll();
    }
}
