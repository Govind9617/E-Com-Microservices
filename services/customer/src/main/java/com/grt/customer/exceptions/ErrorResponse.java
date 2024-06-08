package com.grt.customer.exceptions;

import java.util.Map;

public record ErrorResponse(Map<String, String> error) {
}
