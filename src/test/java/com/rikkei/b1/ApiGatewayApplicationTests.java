package com.rikkei.b1;

import com.rikkei.b1.controller.GatewayRouteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GatewayRouteController.class)
class ApiGatewayApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRoutes() throws Exception {
        mockMvc.perform(get("/api/gateway/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].uri").value("lb://customer-service"))
                .andExpect(jsonPath("$.data[1].uri").value("lb://account-service"))
                .andExpect(jsonPath("$.data[2].uri").value("lb://transaction-service"));
    }
}
