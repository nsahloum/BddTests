package com.bddtest.bddtest.service;

import com.bddtest.bddtest.domain.User;
import com.bddtest.bddtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void saveUser(User user){
        userRepository.addUser(user);
    }
}
