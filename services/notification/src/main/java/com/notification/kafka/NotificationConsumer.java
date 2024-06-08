package com.notification.kafka;

import com.notification.kafka.order.OrderConfirmation;
import com.notification.kafka.payment.PaymentConfirmation;
import com.notification.notification.Notification;
import com.notification.notification.NotificationRepository;
import com.notification.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "payment-service")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation){
        log.info(String.format("Consuming payment notification ::  %S",paymentConfirmation));
        notificationRepository.save(
                Notification.builder().
                        type(NotificationType.PAYMENT_CONFIRMATION).
                        notificationDate(LocalDateTime.now()).paymentConfirmation(paymentConfirmation).
                        build());
    }

    @KafkaListener(topics = "order-service")
    public void consumePaymentNotification(OrderConfirmation orderConfirmation){
        log.info(String.format("Consuming order notification ::  %S",orderConfirmation));
        notificationRepository.save(
                Notification.builder().
                        type(NotificationType.ORDER_CONFIRMATION).
                        notificationDate(LocalDateTime.now()).orderConfirmation(orderConfirmation).
                        build());
    }



}
