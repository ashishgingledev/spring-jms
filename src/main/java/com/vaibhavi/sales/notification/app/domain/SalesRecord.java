package com.vaibhavi.sales.notification.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class SalesRecord {
    private String productType;
    private long productQuantity;
    private BigDecimal value;

    public SalesRecord(String productType, long productQuantity, BigDecimal value) {
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.value = value;
    }
}
