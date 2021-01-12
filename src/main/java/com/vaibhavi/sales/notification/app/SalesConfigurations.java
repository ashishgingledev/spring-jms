package com.vaibhavi.sales.notification.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

@Configuration
@EnableConfigurationProperties(SalesConfigurationProperties.class)
@RequiredArgsConstructor // All final intance variable will be part of generated Constrocotr
@Slf4j
@EnableJms
@EnableAutoConfiguration
public class SalesConfigurations {

    private final SalesConfigurationProperties salesConfigurationProperties;

    @Bean
    /**
     * This requires properties to be defined with prefix = "spring.activemq" in application.yml
     */
    public ActiveMQProperties activeMQProperties() {
        return new ActiveMQProperties();
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
        log.info("Configuring the active mq connection factory..with {}", activeMQProperties().getBrokerUrl());
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(activeMQProperties().getBrokerUrl());

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @PostConstruct
    public void init() {
        log.info("SalesConfigurationProperties is intialised {}", salesConfigurationProperties);
    }

}
