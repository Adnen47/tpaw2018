package com.advyteam.sygha.service;

import com.advyteam.sygha.entity.ExtractionHistory;
import com.advyteam.sygha.repository.ExtractionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtractionService {

    @Autowired
    private ExtractionHistoryRepository extractionHistoryRepository;

    public List<ExtractionHistory> getAllExtraction(){
        return extractionHistoryRepository.findAll();
    }

}
