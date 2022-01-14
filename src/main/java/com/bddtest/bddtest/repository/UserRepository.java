package com.bddtest.bddtest.repository;

import com.bddtest.bddtest.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();


    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
