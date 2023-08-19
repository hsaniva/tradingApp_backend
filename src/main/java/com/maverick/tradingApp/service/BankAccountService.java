package com.maverick.tradingApp.service;

import com.maverick.tradingApp.model.BankAccount;
import com.maverick.tradingApp.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class for Bank account.
 */
@Service
@AllArgsConstructor
@Slf4j
public class BankAccountService {

    private BankAccountRepository bankAccountRepository;

    /**
     * Creates bank account
     * @param bankAccount
     * @return account number
     */
    public Long createBankAccount(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount).getAccountNumber();
    }

    /**
     * Returns bank account object
     * @param bankAccountNumber
     * @return bank account object
     */
    public BankAccount getBankAccount(Long bankAccountNumber){
        return bankAccountRepository.findById(bankAccountNumber).get();
    }
}
