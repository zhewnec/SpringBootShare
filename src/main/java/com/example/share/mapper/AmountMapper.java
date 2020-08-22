package com.example.share.mapper;

import java.util.ArrayList;

public interface AmountMapper {
    // 查询所有
    ArrayList selectAll();
    void  updateAll();
    void insert(String share_code,String transact_date,String buy,String sell,String small_buy,String inside_buy,String big_buy,String large_buy,String small_sell,String inside_sell,String big_sell,String large_sell);


    void insertTransactDate(String shareCode, String TransactDate);
    void updateBuyData(String buy,String smallBuy,String insideBuy,String bigBuy,String largeBuy, String shareCode, String transactDate);
    void updateSellData(String sell, String smallSell, String insideSell, String bigSell, String largeSell, String shareCode, String transactDate);
}
