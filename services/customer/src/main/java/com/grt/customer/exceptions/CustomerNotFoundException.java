package com.grt.customer.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {

    private final String message;


    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
