package com.grt.order.orderproducer;

import com.grt.order.customerClient.CustomerResponse;
import com.grt.order.entity.PaymentMethod;
import com.grt.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
