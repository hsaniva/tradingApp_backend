package com.maverick.tradingApp.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Avinash G, Priyanshu T, Karthik R
 */
@Entity
@Table(name = "tradeAppUser")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;

    @OneToOne
    @JoinColumn(name = "accountNumber", nullable = false)
    private BankAccount bankAccount;

}