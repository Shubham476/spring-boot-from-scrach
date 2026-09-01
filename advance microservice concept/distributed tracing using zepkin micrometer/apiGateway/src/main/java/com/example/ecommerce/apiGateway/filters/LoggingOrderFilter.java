package com.example.ecommerce.apiGateway.filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingOrderFilter extends AbstractGatewayFilterFactory<LoggingOrderFilter.Config> {   //Extends AbstractGatewayFilterFactory to create a custom filter for API Gateway.

    public LoggingOrderFilter() {   //constructor
        super(Config.class);  //Calls the parent constructor, passing the Config class.
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Order filter pre: {}",exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }

}