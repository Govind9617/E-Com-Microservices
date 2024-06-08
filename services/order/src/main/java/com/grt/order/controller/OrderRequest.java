package com.grt.order.controller;

import com.grt.order.entity.PaymentMethod;
import com.grt.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "pay amount must be positive")
        BigDecimal amount,
        @NotNull(message = "pay amount must mention")
        PaymentMethod paymentMethod,
        @NotNull(message = "cutomer must mention")
        @NotBlank(message = "cutomer must mention")
        @NotEmpty(message = "cutomer must mention")
        String customerId,
        @NotEmpty(message = "product must be atleast one")
        List<PurchaseRequest> products
){

}
