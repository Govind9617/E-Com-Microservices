package com.notification.notification;

import com.notification.kafka.payment.PaymentConfirmation;
import com.notification.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
