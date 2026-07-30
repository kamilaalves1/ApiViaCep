package com.viacep.controller;

import com.viacep.dto.ApiResponse;
import com.viacep.dto.CoverageResponse;
import com.viacep.service.CoverageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coverage")
@RequiredArgsConstructor
public class CoverageController {

    private final CoverageService coverageService;

    @GetMapping("/by-uf")
    public ResponseEntity<ApiResponse<CoverageResponse>> getCoverageByUf() {
        CoverageResponse coverageData = coverageService.getCoverageByUf();
        return ResponseEntity.ok(ApiResponse.<CoverageResponse>builder()
                .success(true)
                .data(coverageData)
                .message("Cobertura obtida com sucesso")
                .build());
    }

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<CoverageResponse>> getCoverageStatistics(@RequestParam String uf) {
        CoverageResponse statistics = coverageService.getCoverageStatistics(uf);
        return ResponseEntity.ok(ApiResponse.<CoverageResponse>builder()
                .success(true)
                .data(statistics)
                .message("Estatísticas de cobertura obtidas com sucesso")
                .build());
    }

    @GetMapping("/heatmap")
    public ResponseEntity<ApiResponse<CoverageResponse>> getCoverageHeatmap(@RequestParam String type) {
        CoverageResponse heatmapData = coverageService.getCoverageHeatmap(type);
        return ResponseEntity.ok(ApiResponse.<CoverageResponse>builder()
                .success(true)
                .data(heatmapData)
                .message("Mapa de calor de cobertura obtido com sucesso")
                .build());
    }
}
