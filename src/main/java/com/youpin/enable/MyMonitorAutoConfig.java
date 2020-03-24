package com.youpin.enable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyMonitorAutoConfig {

    @Value("${monitor.server.enabled:}")
    private String enabledConfig;

    @Value("${monitor.server.name:}")
    private String name;

    @Bean
    @ConditionalOnProperty(prefix = "monitor.server", name = "enabled", havingValue = "true")
    protected MyMonitor startServerMonitor() {
        return new MyMonitor(this.name);
    }
}
