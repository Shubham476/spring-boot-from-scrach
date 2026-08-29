package com.example.ecommerce.apiGateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. PRE-FILTER LOGIC: Executes on the way in
        String path = exchange.getRequest().getPath().toString();
        log.info("Incoming request path: {}", path);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 2. POST-FILTER LOGIC: Executes on the way out
            int statusCode = Objects.requireNonNull(exchange.getResponse().getStatusCode()).value();
            log.info("Outgoing response status: {}", statusCode);
        }));
    }

    @Override
    public int getOrder() {
        return -1; // High priority order execution
    }
}
