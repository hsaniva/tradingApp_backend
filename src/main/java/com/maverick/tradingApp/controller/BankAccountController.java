package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.model.BankAccount;
import com.maverick.tradingApp.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling bank account related functionalities.
 */
@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class BankAccountController {

    private BankAccountService bankAccountService;

    /**
     * Returns bank account based on account number.
     * @param accountNumber
     * @return Bank account object.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BankAccount getBankAccount(@RequestParam Long accountNumber){
        return bankAccountService.getBankAccount(accountNumber);
    }

    /**
     * API for creating bank account.
     * @param bankAccount
     * @return Returns the Bank account number after creating the account.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBankAccount(@RequestBody BankAccount bankAccount){
        return bankAccountService.createBankAccount(bankAccount);
    }
}
