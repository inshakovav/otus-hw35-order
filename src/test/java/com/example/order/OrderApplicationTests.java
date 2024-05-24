package com.example.order;

import com.example.order.dto.OrderCreatedMessage;
import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class OrderApplicationTests {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private KafkaConsumer consumer;

    // To disable second @KafkaListener
//    @MockBean
//    KafkaConsumerService kafkaConsumerService;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

//    @Disabled("Only for manual start")
    @Test
    void createOrder() throws Exception {
        // before

        // act
        mockMvc.perform(
                post("http://localhost:8000/order")
                        .content("{ \"accountId\":\"2\", \"price\":\"1.1\", \"bookingAt\":\"2024-04-27T02:55:28.183+00:00\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // verify
        OrderEntity lastOrder = orderRepository.findFirstByOrderByIdDesc();
        log.info("Last record={}", lastOrder);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        OrderCreatedMessage receivedMessage = consumer.getReceivedMessage();
        assertEquals(receivedMessage.getAccountId(), lastOrder.getAccountId());
        assertEquals(receivedMessage.getOrderId(), lastOrder.getId());

        BigDecimal orderPrice = receivedMessage.getOrderPrice();
        BigDecimal orderPriceRounded = orderPrice.setScale(2, RoundingMode.DOWN);
        BigDecimal expectedOrderPrice = new BigDecimal(1.1).setScale(2, RoundingMode.DOWN);
        assertEquals(expectedOrderPrice, orderPriceRounded);
    }
}
