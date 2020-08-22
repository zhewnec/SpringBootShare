package com.example.share.mapper;

import java.util.Map;

public interface TopListMapper {

    void insertTodayTopLIstInfo(Map TopListMap);
    boolean IsTodayUpdate(String today);
}
