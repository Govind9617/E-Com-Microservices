package com.grt.order.orderproducer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> KafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("Sending Order Confirmation");
        Message<OrderConfirmation> message= MessageBuilder.withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC,"order-topic").build();
        log.info("message to send from produce"+ message);
        KafkaTemplate.send(message);

    }

}
