package com.viacep.model;

import java.util.List;

public class ProductResponse {
    private Location location;
    private List<Product> products;
    private Store nearestStore;

    // Getters e Setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Store getNearestStore() {
        return nearestStore;
    }

    public void setNearestStore(Store nearestStore) {
        this.nearestStore = nearestStore;
    }
}
