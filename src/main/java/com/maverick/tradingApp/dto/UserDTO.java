package com.maverick.tradingApp.dto;
/**
 * @authors Avinash G, Priyanshu T, Karthik R
 */

import com.maverick.tradingApp.model.BankAccount;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * User DTO class
 */
@Data
@Builder
@Getter
@Setter
public class UserDTO {
    private String userId; // Auto generated User Id
    private String name;
    private BankAccount bankAccount;
}
