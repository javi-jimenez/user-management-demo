package com.example.userdemo.repository;

import com.example.userdemo.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, String> {
}