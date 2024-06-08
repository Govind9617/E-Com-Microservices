package com.grt.order.customerClient;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
