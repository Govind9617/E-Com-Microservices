package com.grt.customer.controller;

import com.grt.customer.entity.Address;

public record CustomerResponse(String id,
                               String firstName,
                               String lastName,

                               String email,
                               Address address) {
}
