package com.trendyol.shipment.exception;

public class EmptyProductsException extends RuntimeException {
    private static final String message = "Products cannot be empty";
    public EmptyProductsException() {
        super(message);
    }
}
