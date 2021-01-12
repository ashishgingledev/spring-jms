package com.vaibhavi.sales.notification.app;

import com.vaibhavi.sales.notification.app.domain.SaleMessage;
import com.vaibhavi.sales.notification.app.service.SalesMessageListner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class SalesNotificationApplication {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${sales.listenerQueue}")
    private String queue;


    public static void main(String[] args) {
        SpringApplication.run(SalesNotificationApplication.class);

    }

    @PostConstruct
    public void inti() {


        for (int i = 0; i < 12; i++) {
            jmsTemplate.send(queue, new MessageCreator() {

                @Override
                public Message createMessage(Session session) throws JMSException {
                    SaleMessage saleMessage = new SaleMessage();
                    saleMessage.setProductType("APPLE");
                    saleMessage.setQuantity(1);
                    saleMessage.setValue(new BigDecimal(10));
                    return session.createObjectMessage(saleMessage);
                }
            });
        }
    }

}
