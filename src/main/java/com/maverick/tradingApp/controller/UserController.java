package com.maverick.tradingApp.controller;

import com.maverick.tradingApp.dto.UserDTO;
import com.maverick.tradingApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Avinash G, Priyanshu T, Karthik R
 * Controller class for User related functionalities
 */
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * Returns all the users
     * @return list of users.
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    /**
     * Creates user
     * @param userDTO User info
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
    }

    @GetMapping("/portfolio")
    @ResponseStatus(value = HttpStatus.OK)
    public void getUserPortfolio(){

    }
}
