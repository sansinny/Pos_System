
package com.example.Pos_System.service;

import com.example.Pos_System.models.Users;
import com.example.Pos_System.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Users validateUsers(String username, String password, String role) {
        return usersRepository.findByUsernameAndPasswordAndRole(username, password, role);
    }
    
}