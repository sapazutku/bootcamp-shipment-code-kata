package com.trendyol.shipment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        ShipmentCalculator shipmentCalculator = new ShipmentCalculator(this);
        return shipmentCalculator.calculateShipment();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
