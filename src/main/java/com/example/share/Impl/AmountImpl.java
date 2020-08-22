package com.example.share.Impl;

import com.example.share.mapper.AmountMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AmountImpl   {
    @Autowired
    private AmountMapper amountMapper;


    public void insert(String share_code,String transact_date,String buy,String sell,String small_buy,String inside_buy,String big_buy,String large_buy,String small_sell,String inside_sell,String big_sell,String large_sell){
        amountMapper.insert( share_code, transact_date, buy, sell, small_buy, inside_buy, big_buy, large_buy, small_sell, inside_sell, big_sell, large_sell);
    }
}
