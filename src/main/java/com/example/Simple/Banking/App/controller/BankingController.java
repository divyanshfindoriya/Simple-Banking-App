package com.example.Simple.Banking.App.controller;

import com.example.Simple.Banking.App.model.BankingDetails;
import com.example.Simple.Banking.App.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banking")
public class BankingController {
    @Autowired
    BankingService bankingService;
    @PostMapping(value = "save")
    public BankingDetails save(@RequestBody BankingDetails b){
        return bankingService.save(b);
    }
    @GetMapping(value = "getAll")
    public List<BankingDetails> getAll(){
        return bankingService.getAll();
    }

    @GetMapping(value = "get-by-id/{id}")
    public BankingDetails getById(@PathVariable int id){
        try {
            return bankingService.getById(id);
        } catch (RuntimeException exception){
            System.out.println("Account not found! : " + exception);
        }
        return null;
    }
    @PostMapping(value = "deposit/{id}")
    public BankingDetails deposit(@PathVariable int id, @RequestBody Map<String, Integer> hashMap){
        int amount = hashMap.get("amount");
        return bankingService.deposit(id, amount);
    }
    @PostMapping(value = "withdraw/{id}")
    public BankingDetails withdraw(@PathVariable int id,@RequestBody Map<String, Integer> hashMap){
        int amount = hashMap.get("amount");
        return bankingService.withdraw(id,amount);
    }
    @PostMapping("transfermoney")
    public ResponseEntity<String> transferMoney(
            @RequestParam String sourceAccountNumber,
            @RequestParam String targetAccountNumber,
            @RequestParam int amount) {
        bankingService.transferMoney(sourceAccountNumber, targetAccountNumber, amount);
        return ResponseEntity.ok("Money transferred successfully!");
    }
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id){
        bankingService.delete(id);
        return ("Account deleted successfully!");
    }
}
