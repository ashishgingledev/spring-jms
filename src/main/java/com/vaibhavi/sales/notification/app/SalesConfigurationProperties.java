package com.vaibhavi.sales.notification.app;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sales")
/*This lombok annoatation will generate Getter/Setter/ToString*/
@Data
public class SalesConfigurationProperties {

    private int reportInterval;
    private int pauseInterval;
    private String listenerQueue;
}
