package com.maverick.tradingApp.service;
/**
 * @author Avinash G, Karthik R, Priyanshu T
 */

import com.maverick.tradingApp.dto.UserDTO;
import com.maverick.tradingApp.helperFunctions.ObjectConversionHelper;
import com.maverick.tradingApp.model.User;
import com.maverick.tradingApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;

    /**
     * returns list of all users.
     * @return list of all users.
     */
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(ObjectConversionHelper::BOToDTO).toList();
    }

    /**
     * Creates user
     * @param userDTO
     */
    public void createUser(UserDTO userDTO) {
        userRepository.save(ObjectConversionHelper.DTOToBO(userDTO));
    }

    public User getUserById(String userID){
        return userRepository.findById(userID).get();
    }
}
