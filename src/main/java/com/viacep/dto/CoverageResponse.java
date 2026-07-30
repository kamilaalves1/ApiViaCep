package com.viacep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoverageResponse {
    private List<CoverageData> coverage;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CoverageData {
    private String uf;
    private double coverage;
    private int totalCities;
    private int coveredCities;
    private int totalCEPs;
    private String status;
}
