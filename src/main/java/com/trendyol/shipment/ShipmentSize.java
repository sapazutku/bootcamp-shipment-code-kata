package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    private final int ordinal = this.ordinal();

    public ShipmentSize getUpperSize() {
        if (ordinal == values().length - 1){
            return this;
        }
        return values()[ordinal + 1];
    }
}





