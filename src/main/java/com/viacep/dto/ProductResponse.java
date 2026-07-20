package com.viacep.dto;

import com.viacep.model.Location;
import com.viacep.model.Product;
import com.viacep.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Location location;
    private List<Product> products;
    private Store nearestStore;
}
