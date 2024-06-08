package com.grt.customer.controller;

import com.grt.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        String id,

        @NotNull (message = "first name required") String firstName,
        @NotNull (message = "last name required") String lastName,
        @NotNull (message = "email not null  required")
        @Email(message = "email required")
        String email,
         Address address) {
}
