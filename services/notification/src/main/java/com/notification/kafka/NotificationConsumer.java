package com.notification.kafka;

import com.notification.email.EmailService;
import com.notification.kafka.order.OrderConfirmation;
import com.notification.kafka.payment.PaymentConfirmation;
import com.notification.notification.Notification;
import com.notification.notification.NotificationRepository;
import com.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
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
    private final EmailService service;

    @KafkaListener(topics = "payment-service")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming payment notification ::  %S", paymentConfirmation));
        notificationRepository.save(
                Notification.builder().
                        type(NotificationType.PAYMENT_CONFIRMATION).
                        notificationDate(LocalDateTime.now()).paymentConfirmation(paymentConfirmation).
                        build());

        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        service.sentPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-service")
    public void consumePaymentNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming order notification ::  %S", orderConfirmation));
        notificationRepository.save(
                Notification.builder().
                        type(NotificationType.ORDER_CONFIRMATION).
                        notificationDate(LocalDateTime.now()).orderConfirmation(orderConfirmation).
                        build());

        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        service.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.product()
        );
    }


}
