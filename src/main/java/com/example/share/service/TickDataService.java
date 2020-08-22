package com.example.share.service;

import java.util.ArrayList;

public interface TickDataService {
    ArrayList selectAll();
    ArrayList countBillSmall(String date, String type);
    ArrayList countLargeBill(String date, String type);
    ArrayList countBigBill(String date, String type);
    ArrayList countInsideBill(String date, String type);

}
