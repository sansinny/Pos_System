
package com.example.Pos_System.service;

import com.example.Pos_System.models.Users;


public interface UserService {
    Users save(Users users);
    Users validateUsers(String username, String password, String role);
}
