package com.example.Pos_System.repository;


import com.example.Pos_System.models.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Integer>{

    @Override
    public List<Users> findAll();
    Users findByUsernameAndPasswordAndRole(String username, String password,String role);
    
}