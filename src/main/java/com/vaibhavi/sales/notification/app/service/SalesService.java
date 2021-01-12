package com.vaibhavi.sales.notification.app.service;

import com.vaibhavi.sales.notification.app.domain.CompletedSaleRecords;
import com.vaibhavi.sales.notification.app.domain.SaleMessage;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    public void processMessage(SaleMessage saleMessage) {
        CompletedSaleRecords.addNewSaleRecord(saleMessage.getProductType(), saleMessage.getQuantity(), saleMessage.getValue());
    }
}
