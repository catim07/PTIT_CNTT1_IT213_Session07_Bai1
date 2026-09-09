package com.rikkei.b1.dto;

public record RouteConfigDto(
    String routeId,
    String uri,
    String pathPattern,
    String loadBalancerType
) {}
