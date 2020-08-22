package com.example.share.mapper;

import java.util.ArrayList;
import java.util.Map;

public interface ShareListMapper {

    void insertShareListInfo(Map shareListMap);

    ArrayList selectMarketDistributed();
}
