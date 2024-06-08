package com.grt.product.exceptions;

import java.util.Map;

public record ErrorResponse(Map<String, String> error) {
}
