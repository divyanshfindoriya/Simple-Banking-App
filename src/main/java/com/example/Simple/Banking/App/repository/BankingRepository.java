package com.example.Simple.Banking.App.repository;

import com.example.Simple.Banking.App.model.BankingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankingRepository extends JpaRepository <BankingDetails , Integer> {
    BankingDetails findByNumber(String sourceAccountNumber);
}
