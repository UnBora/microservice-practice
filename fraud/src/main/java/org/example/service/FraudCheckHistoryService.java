package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.FraudCheckHistory;
import org.example.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .createAt(LocalTime.from(LocalDateTime.now()))
                        .customerId(customerId)
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
