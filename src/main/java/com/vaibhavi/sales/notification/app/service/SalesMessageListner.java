package com.vaibhavi.sales.notification.app.service;

import com.vaibhavi.sales.notification.app.SalesConfigurationProperties;
import com.vaibhavi.sales.notification.app.domain.CompletedSaleRecords;
import com.vaibhavi.sales.notification.app.domain.SaleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesMessageListner {

    private final SalesConfigurationProperties salesConfigurationProperties;
    private final SalesService salesService;
    private static long messageCount = 0;

    @JmsListener(destination = "${sales.listenerQueue}")
    public void receieveSalesMessage(ActiveMQObjectMessage message) throws JMSException {


        SaleMessage saleMessage = null;
        saleMessage = (SaleMessage) message.getObject();


        messageCount++;
        log.info("Received Message {}", messageCount);


        salesService.processMessage(saleMessage);
        if (messageCount % salesConfigurationProperties.getReportInterval() == 0) {
            log.info("next {} recied, here's report till now..", salesConfigurationProperties.getReportInterval());
            CompletedSaleRecords.printTotalSalesRecordTillNow();
        }

        if (messageCount % salesConfigurationProperties.getPauseInterval() == 0) {
            //Report
        }


    }
}
