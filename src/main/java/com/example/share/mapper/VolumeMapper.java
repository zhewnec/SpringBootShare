package com.example.share.mapper;

public interface VolumeMapper {
    void insertTransactDate(String shareCode, String TransactDate);
    void updateBuyData(String buy,String smallBuy,String insideBuy,String bigBuy,String largeBuy, String shareCode, String transactDate);
    void updateSellData(String sell, String smallSell, String insideSell, String bigSell, String largeSell, String shareCode, String transactDate);
}
