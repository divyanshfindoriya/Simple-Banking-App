package com.example.Simple.Banking.App.service;

import com.example.Simple.Banking.App.model.BankingDetails;
import com.example.Simple.Banking.App.repository.BankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankingService {
    @Autowired
    BankingRepository bankingRepository;
    public BankingDetails save(BankingDetails b) {
        return bankingRepository.save(b);
    }
    public List<BankingDetails> getAll() {
        return bankingRepository.findAll();
    }

    public BankingDetails getById(int id) {
        return bankingRepository.findById(id).get();
    }
    public BankingDetails deposit(int id, int amount) {
        BankingDetails account = getById(id);
        account.setBalance(account.getBalance() + amount);
        return bankingRepository.save(account);
    }
    public BankingDetails withdraw(int id, int amount) {
        BankingDetails account = getById(id);
        if (account.getBalance() < amount){
            throw new RuntimeException();
        }
        account.setBalance(account.getBalance() - amount);
        return bankingRepository.save(account);
    }
    public void transferMoney(String sourceAccountNumber, String targetAccountNumber, int amount) {
        BankingDetails sourceAccount = bankingRepository.findByNumber(sourceAccountNumber);
        BankingDetails targetAccount = bankingRepository.findByNumber(targetAccountNumber);
        if (sourceAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Invalid account numbers!");
        }
        if (sourceAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance!");
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
        bankingRepository.save(sourceAccount);
        bankingRepository.save(targetAccount);
    }
    public void delete(int id) {
        bankingRepository.deleteById(id);
    }
}
