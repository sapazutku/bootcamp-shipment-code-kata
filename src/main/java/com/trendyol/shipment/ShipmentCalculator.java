package com.trendyol.shipment;

import com.trendyol.shipment.exception.EmptyProductsException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShipmentCalculator {

    private static final int SIZE_THRESHOLD = 3;

    private final List<Product> products;

    public ShipmentCalculator(Basket basket) {
        this.products = basket.getProducts();
    }

    public boolean isProductsNull() {
        return products == null;
    }

    protected ShipmentSize getMostFrequentShipment(){
        return products.stream()
                .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(ShipmentSize.SMALL);
    }

    protected ShipmentSize getLargestSizeShipment(){
        return products.stream()
                .map(Product::getSize)
                .max(ShipmentSize::compareTo).orElse(ShipmentSize.SMALL);
    }

    protected ShipmentSize calculateShipment() {
        if (products == null || products.isEmpty()) {
            throw new EmptyProductsException();
        }

        Map<ShipmentSize, Long> shipmentCountMap = products.stream()
                .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()));

        ShipmentSize mostFrequentShipment = getMostFrequentShipment();
        ShipmentSize largestSizeShipment = getLargestSizeShipment();

        if (shipmentCountMap.getOrDefault(mostFrequentShipment, 0L) >= SIZE_THRESHOLD) {
            return mostFrequentShipment.getUpperSize();
        }

        return largestSizeShipment;
    }
}
