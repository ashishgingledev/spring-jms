package com.vaibhavi.sales.notification.app.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CompletedSaleRecords {

    private static ConcurrentHashMap<String, List<SalesRecord>> productSalesRecord = new ConcurrentHashMap<>();

    public static void addNewSaleRecord(String productType, long quantity, BigDecimal value) {
        SalesRecord rec = new SalesRecord(productType, quantity, value);
        if(productSalesRecord.containsKey(productType)) {
            productSalesRecord.get(productType).add(rec);
        } else {
            List<SalesRecord> salesRecordList = new ArrayList<>();
            salesRecordList.add(rec);
            productSalesRecord.put(productType, salesRecordList);
        }
    }
    
    public static void printTotalSalesRecordTillNow() {
        //This is only 10 message
        System.out.format("%32s%32s%32s\n", "ProductType", "TotalQuantity", "TotalSale");
        for (Map.Entry<String, List<SalesRecord>> productEntrySet : productSalesRecord.entrySet()) {
            BigDecimal totalVal = new BigDecimal(0);
            Long quantity = 0L;
            for (SalesRecord salesRecord : productEntrySet.getValue()) {

                totalVal = totalVal.add(salesRecord.getValue());
                quantity = Long.sum(quantity, salesRecord.getProductQuantity());
            }
            System.out.format("%32s%32d%32f\n", productEntrySet.getKey(), quantity, totalVal);
        }
    }



}
