package com.advyteam.sygha.web.controller;

import com.advyteam.sygha.entity.ExtractionHistory;
import com.advyteam.sygha.service.BatchDSNService;
import com.advyteam.sygha.service.ExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/batch")
public class BatchDSNController {

    @Autowired
    BatchDSNService batchDSNService;

    @Autowired
    ExtractionService extractionService;

    @GetMapping("launcher")
    public ResponseEntity launcherBatch() {
        try {
            batchDSNService.launchJob();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
        return ResponseEntity.accepted().body(new String("{\"success\":1}"));
    }

    @GetMapping("extractions")
    public ResponseEntity getAllExtraction(){
        List<ExtractionHistory> extractionHistories = extractionService.getAllExtraction();
        return ResponseEntity.accepted().body(extractionHistories);
    }
}
