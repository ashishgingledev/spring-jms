package com.vaibhavi.sales.notification.app.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SaleMessage implements Serializable {
    /*Message Type
    * 1. Single_Sale : – contains the details of 1 sale E.g apple at 10p
    * 2. Multiple_Sale :  contains the details of a sale and the number of occurrences of
         that sale. E.g 20 sales of apples at 10p each
    * 3. Adjustment_Sale : contains the details of a sale and an adjustment operation to be
         applied to all stored sales of this product type.
    */
    private String messageType;

    private String productType;

    /*Required only if messageType = Multiple_Sale, For Single_Sale its by default 1*/
    private long quantity = 1;

    private BigDecimal value;

    /*Required only if messageType = Adjustment_Sale*/
    private String operationType;
}
