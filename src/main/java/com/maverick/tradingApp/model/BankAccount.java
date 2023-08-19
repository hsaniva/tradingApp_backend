package com.maverick.tradingApp.model;
/**
 * @author Avinash G, Priyanshu T, Karthik R
 */

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class for storing Bank account related functionalities.
 */
@Entity
@Table(name = "bank_account_table")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    private Long balance;

    /**
     * Withdraw function
     * @param amount to be withdrawn
     * @return boolean if the amount was deducted successfully.
     */
    public boolean withdraw(Long amount){
        if(this.balance >= amount){
            this.balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Deposit function
     * @param amount to be deposited
     */
    public void deposit(Long amount){
        this.balance += amount;
    }
}
