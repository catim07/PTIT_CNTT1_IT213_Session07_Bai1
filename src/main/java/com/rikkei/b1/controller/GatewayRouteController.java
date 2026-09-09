package com.rikkei.b1.controller;

import com.rikkei.b1.dto.ApiResponse;
import com.rikkei.b1.dto.RouteConfigDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gateway")
public class GatewayRouteController {

    @Value("${spring.cloud.gateway.routes[0].uri:lb://customer-service}")
    private String customerUri;

    @Value("${spring.cloud.gateway.routes[1].uri:lb://account-service}")
    private String accountUri;

    @Value("${spring.cloud.gateway.routes[2].uri:lb://transaction-service}")
    private String transactionUri;

    @GetMapping("/routes")
    public ApiResponse<List<RouteConfigDto>> getRoutes() {
        List<RouteConfigDto> routes = List.of(
            new RouteConfigDto("customer-service-route", customerUri, "/api/customers/**", "SPRING_CLOUD_LOADBALANCER"),
            new RouteConfigDto("account-service-route", accountUri, "/api/accounts/**", "SPRING_CLOUD_LOADBALANCER"),
            new RouteConfigDto("transaction-service-route", transactionUri, "/api/transactions/**", "SPRING_CLOUD_LOADBALANCER")
        );
        return ApiResponse.success("API Gateway routes configured successfully on port 8222", routes);
    }
}
